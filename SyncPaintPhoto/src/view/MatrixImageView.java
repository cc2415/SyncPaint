package view;

import global.MyGlobal;

import java.io.IOException;
import java.util.ArrayList;

import save.SaveBitmapToJPG;
import smart.Judge;
import smart.Rectangle;
import util.RGBToString;

import com.czc.clientsocket.MainActivity;
import com.czc.clientsocket.R;

import connectServer.ConnectServer;

import Point.DrawPointPath;
import Point.UpPointPath;
import action.DrawImage;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.PointF;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * @ClassName: MatrixImageView
 * @Description: 带放大、缩小、移动效果的ImageView
 * @author LinJ
 * 
 */
public class MatrixImageView extends ImageView {
	
	DrawImage di;
	Bitmap tBitmap;

	UpPointPath upp = new UpPointPath();
	DrawPointPath dpp = new DrawPointPath();
	RGBToString rgbToString = new RGBToString();

	public static int nowdraw = 0;
	Bitmap baseBitmap = Bitmap.createBitmap(MyGlobal.SCREE_WIDTH,
			MyGlobal.SCREE_HEIGHT, Config.ARGB_8888);// 创建出一个空的图片
	
	
	Canvas canvas = new Canvas(baseBitmap);
	Paint pen = new Paint();
	EditText pensize;
	/**
	 * 用来获取mainactivity的上下文
	 * 
	 */
	private Context contexts;
	/**
	 * 获得MainActivity对象的
	 */
	MainActivity act;

	int width = 0, height = 0, number = 0;
	PointF movePointf;

	private final static String TAG = "MatrixImageView";
	private GestureDetector mGestureDetector;
	/** 模板Matrix，用以初始化 */
	private Matrix mMatrix = new Matrix();
	/** 图片长度 */
	private float mImageWidth;
	/** 图片高度 */
	private float mImageHeight;

	// 在xml文件中创建会调用
	public MatrixImageView(Context context, AttributeSet attrs) {
		super(context, attrs);

		this.contexts = context;
		act = (MainActivity) this.contexts;
		//111
		if (!MyGlobal.te) {
			canvas.drawColor(Color.parseColor(MyGlobal.CANVAS_BACKGROUND));// 设置画布的背景颜色		
		}
		// 设置矩形监听
		MatrixTouchListener mListener = new MatrixTouchListener();
		setOnTouchListener(mListener);
		mGestureDetector = new GestureDetector(getContext(),
				new GestureListener(mListener));
		// 背景设置为balck
//		setBackgroundColor(Color.BLACK);
		// 将缩放类型设置为FIT_CENTER，表示把图片按比例扩大/缩小到View的宽度，居中显示
		setScaleType(ScaleType.FIT_CENTER);
	}

	public MatrixImageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public MatrixImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setImageBitmap(Bitmap bm) {
		// TODO Auto-generated method stub
		super.setImageBitmap(bm);
		// 设置完图片后，获取该图片的坐标变换矩阵
		mMatrix.set(getImageMatrix());
		float[] values = new float[9];
		mMatrix.getValues(values);
		// 图片宽度为屏幕宽度除缩放倍数
		mImageWidth = getWidth() / values[Matrix.MSCALE_X];
		mImageHeight = (getHeight() - values[Matrix.MTRANS_Y] * 2)
				/ values[Matrix.MSCALE_Y];
	}

	public class MatrixTouchListener implements OnTouchListener {
		/** 拖拉照片模式 */
		private static final int MODE_DRAG = 1;
		/** 放大缩小照片模式 */
		private static final int MODE_ZOOM = 2;
		/** 不支持Matrix */
		private static final int MODE_UNABLE = 3;
		/** 最大缩放级别 */
		float mMaxScale = 6;
		/** 双击时的缩放级别 */
		float mDobleClickScale = 2;
		private int mMode = 0;//
		/** 缩放开始时的手指间距 */
		private float mStartDis;
		/** 当前Matrix */
		private Matrix mCurrentMatrix = new Matrix();

		/** 用于记录开始时候的坐标位置 */
		private PointF startPoint = new PointF();

		int startX, startY;

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			switch (event.getActionMasked()) {
			case MotionEvent.ACTION_DOWN:
				// 设置拖动模式
				mMode = MODE_DRAG;
				startPoint.set(event.getX(), event.getY());
				isMatrixEnable();
				//1111111111111111
				
				if(MyGlobal.te){
					System.out.println(MyGlobal.CHOSE_PIC);
					canvas.drawBitmap(MyGlobal.CHOSE_PIC, new Matrix(), pen);
					
					MyGlobal.te=false;
				}
				
				// 原来的/////////////////////////////////////////////////////////
				startX = (int) event.getX();
				startY = (int) event.getY();
				MyGlobal.left = startX;
				MyGlobal.top = startY;
//				System.out.println(MyGlobal.left + "    " + MyGlobal.top);
				if (act.back) {// 判断是否按下了撤销按钮
					baseBitmap = act.afterCancelBitmap;
					// System.out.println(baseBitmap + "===down222");
					canvas = new Canvas(baseBitmap);
					act.back = false;
					// System.out.println("到这了");
				}
				act.bt_back.setEnabled(true);
				act.initDrawer(baseBitmap, canvas, pen, MyGlobal.color, 2);
				
				// 让服务器知道要开始绘画了
				new ConnectServer(MyGlobal.START_DRAW, act).start();

				// ////////////////////////////////////////////////////////////////////

				// ///////////////新的
				// di = new DrawImage();
				// startX = (int) event.getX();
				// startY = (int) event.getY();
				// di.DrawImageDown(act, event, baseBitmap, canvas, pen, startX,
				// startY, showPopuWindow);

				break;
			case MotionEvent.ACTION_UP:
				if (MyGlobal.TYPE_SMART) {// 识别模式
					dpp.setuppointpath(upp.pathStringArrayList);// 添加按下到松开的笔画
					MyGlobal.SMART_TEMP.add(upp.pathStringArrayList);//最后一笔，用来比较大小
					upp.pathStringArrayList = new ArrayList<String>();
					// //////////1、识别矩形//////////////
					Judge judge=new Judge(dpp);
					MyGlobal.IS_RECTANGLE=judge.checkRectangle();
					boolean hand_out=false;
					if(MyGlobal.IS_RECTANGLE){//如果是矩形
						Rectangle r=new Rectangle(contexts, act, canvas, pen, dpp, upp);
						r.finshRect();
						hand_out=true;
					}
					
					//////通用////////////////////////////////////////////
					if(!hand_out){
						new ConnectServer(MyGlobal.HAND_UP, act).start();//先让服务器记录一笔
					}
					MyGlobal.SMART_TEMP=new ArrayList<ArrayList<String>>();
					
				} else {//普通模式
					// 记录一笔
					dpp.setuppointpath(upp.pathStringArrayList);
					upp.pathStringArrayList = new ArrayList<String>();// 清空记录
					// 发送松开请求
					new ConnectServer(MyGlobal.HAND_UP, act).start();
					System.out.println("x:"+event.getX()+"  y:"+event.getY());
					try {//实时保存
						 if(MyGlobal.Saved){
						SaveBitmapToJPG fdBitmapToJPG=new SaveBitmapToJPG(contexts,baseBitmap,MyGlobal.SAVE_NAME);
						 }
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			case MotionEvent.ACTION_CANCEL:
				reSetMatrix();
				break;
			case MotionEvent.ACTION_MOVE:

				if (mMode == MODE_ZOOM) {
//					setZoomMatrix(event);
				}
				// System.out.println("MatrixImageView移动中");

				// else if (mMode==MODE_DRAG) {
				// setDragMatrix(event);
				// }

				// 下面是测试
				if (mMode == MODE_DRAG && (act.type % 2 != 0)) {
					setDragMatrix(event);
				} else {
					if (MyGlobal.TYPE_SMART) {// 识别模式
						myDraw(event);
					} else {// 普通模式
							// 新的
							// 没问题///////////////////////////////////////////////////////////
						myDraw(event);
					}
				}
				break;
			case MotionEvent.ACTION_POINTER_DOWN:
				if (mMode == MODE_UNABLE)
					return true;
				mMode = MODE_ZOOM;
				mStartDis = distance(event);
				break;
			default:
				break;
			}
			return mGestureDetector.onTouchEvent(event);
		}

		/**
		 * wode
		 * 
		 * @param event
		 */
		public void setDragMatrix(MotionEvent event) {
			if (isZoomChanged()) {
				float dx = event.getX() - startPoint.x; // 得到x轴的移动距离
				float dy = event.getY() - startPoint.y; // 得到x轴的移动距离
				// 避免和双击冲突,大于10f才算是拖动
				if (Math.sqrt(dx * dx + dy * dy) > 10f) {
					startPoint.set(event.getX(), event.getY());
					// 在当前基础上移动
					mCurrentMatrix.set(getImageMatrix());
					float[] values = new float[9];
					mCurrentMatrix.getValues(values);
					// dx=checkDxBound(values,dx);
					// dy=checkDyBound(values,dy);
					mCurrentMatrix.postTranslate(dx, dy);
					setImageMatrix(mCurrentMatrix);
				}
			}
		}

		// //////////////////////////////////
		public void myDraw(MotionEvent event) {
			if (act.TYPE_CLEAN == 1) {// 清除按钮被按下了
				baseBitmap = null;
				baseBitmap = Bitmap.createBitmap(MyGlobal.SCREE_WIDTH,
						MyGlobal.SCREE_HEIGHT, Config.ARGB_8888);// 创建出一个空的图片
				canvas = new Canvas(baseBitmap);
				act.afterCancelBitmap = baseBitmap;
				act.TYPE_CLEAN = 0;
			}
			if (act.back) {
				baseBitmap = act.afterCancelBitmap;
				act.back = false;
			}
//			System.out.println("matri :  width:"+MyGlobal.SCREE_WIDTH+"  height:"+MyGlobal.SCREE_HEIGHT);
			int stopX = (int) event.getX();
			int stopY = (int) event.getY();
			di = new DrawImage();
			baseBitmap = di.DrawImageBitmap(act, baseBitmap, canvas,
					MyGlobal.color, pen, event, startX, startY, stopX, stopY,
					upp);
			setImageBitmap(baseBitmap);
			startX = (int) event.getX();
			startY = (int) event.getY();
		}

		// ///////////////////////////////////
		/**
		 * 判断缩放级别是否是改变过
		 * 
		 * @return true表示非初始值,false表示初始值
		 */
		private boolean isZoomChanged() {
			float[] values = new float[9];
			getImageMatrix().getValues(values);
			// 获取当前X轴缩放级别
			float scale = values[Matrix.MSCALE_X];
			// 获取模板的X轴缩放级别，两者做比较
			mMatrix.getValues(values);
			return scale != values[Matrix.MSCALE_X];
		}

		/**
		 * 和当前矩阵对比，检验dy，使图像移动后不会超出ImageView边界
		 * 
		 * @param values
		 * @param dy
		 * @return
		 */
		private float checkDyBound(float[] values, float dy) {
			float height = getHeight();
			if (mImageHeight * values[Matrix.MSCALE_Y] < height)
				return 0;
			if (values[Matrix.MTRANS_Y] + dy > 0)
				dy = -values[Matrix.MTRANS_Y];
			else if (values[Matrix.MTRANS_Y] + dy < -(mImageHeight
					* values[Matrix.MSCALE_Y] - height))
				dy = -(mImageHeight * values[Matrix.MSCALE_Y] - height)
						- values[Matrix.MTRANS_Y];
			return dy;
		}

		/**
		 * 和当前矩阵对比，检验dx，使图像移动后不会超出ImageView边界
		 * 
		 * @param values
		 * @param dx
		 * @return
		 */
		private float checkDxBound(float[] values, float dx) {
			float width = getWidth();
			if (mImageWidth * values[Matrix.MSCALE_X] < width)
				return 0;
			if (values[Matrix.MTRANS_X] + dx > 0)
				dx = -values[Matrix.MTRANS_X];
			else if (values[Matrix.MTRANS_X] + dx < -(mImageWidth
					* values[Matrix.MSCALE_X] - width))
				dx = -(mImageWidth * values[Matrix.MSCALE_X] - width)
						- values[Matrix.MTRANS_X];
			return dx;
		}

		/**
		 * 设置缩放Matrix
		 * 
		 * @param event
		 */
		private void setZoomMatrix(MotionEvent event) {
			// 只有同时触屏两个点的时候才执行
			if (event.getPointerCount() < 2)
				return;
			float endDis = distance(event);// 结束距离
			if (endDis > 10f) { // 两个手指并拢在一起的时候像素大于10
				float scale = endDis / mStartDis;// 得到缩放倍数
				mStartDis = endDis;// 重置距离
				mCurrentMatrix.set(getImageMatrix());// 初始化Matrix
				float[] values = new float[9];
				mCurrentMatrix.getValues(values);

				scale = checkMaxScale(scale, values);
				setImageMatrix(mCurrentMatrix);
			}
		}

		/**
		 * 检验scale，使图像缩放后不会超出最大倍数
		 * 
		 * @param scale
		 * @param values
		 * @return
		 */
		private float checkMaxScale(float scale, float[] values) {
			if (scale * values[Matrix.MSCALE_X] > mMaxScale)
				scale = mMaxScale / values[Matrix.MSCALE_X];
			mCurrentMatrix.postScale(scale, scale, getWidth() / 2,
					getHeight() / 2);
			return scale;
		}

		/**
		 * 重置Matrix
		 */
		private void reSetMatrix() {
			if (checkRest()) {
				mCurrentMatrix.set(mMatrix);
				setImageMatrix(mCurrentMatrix);
			}
		}

		/**
		 * 判断是否需要重置
		 * 
		 * @return 当前缩放级别小于模板缩放级别时，重置
		 */
		private boolean checkRest() {
			// TODO Auto-generated method stub
			float[] values = new float[9];
			getImageMatrix().getValues(values);
			// 获取当前X轴缩放级别
			float scale = values[Matrix.MSCALE_X];
			// 获取模板的X轴缩放级别，两者做比较
			mMatrix.getValues(values);
			return scale < values[Matrix.MSCALE_X];
		}

		/**
		 * 判断是否支持Matrix
		 */
		private void isMatrixEnable() {
			// 当加载出错时，不可缩放
			if (getScaleType() != ScaleType.CENTER) {
				setScaleType(ScaleType.MATRIX);
			} else {
				mMode = MODE_UNABLE;// 设置为不支持手势
			}
		}

		/**
		 * 计算两个手指间的距离
		 * 
		 * @param event
		 * @return
		 */
		private float distance(MotionEvent event) {
			float dx = event.getX(1) - event.getX(0);
			float dy = event.getY(1) - event.getY(0);
			/** 使用勾股定理返回两点之间的距离 */
			return (float) Math.sqrt(dx * dx + dy * dy);
		}

		/**
		 * 双击时触发
		 */
		public void onDoubleClick() {
			float scale = isZoomChanged() ? 1 : mDobleClickScale;
			mCurrentMatrix.set(mMatrix);// 初始化Matrix
			mCurrentMatrix.postScale(scale, scale, getWidth() / 2,
					getHeight() / 2);
			setImageMatrix(mCurrentMatrix);
		}
	}

	// 手势监听
	private class GestureListener extends SimpleOnGestureListener {
		private final MatrixTouchListener listener;

		public GestureListener(MatrixTouchListener listener) {
			this.listener = listener;
		}

		@Override
		public boolean onDown(MotionEvent e) {
			// 捕获Down事件
			return true;
		}

		@Override
		public boolean onDoubleTap(MotionEvent e) {
			// 触发双击事件
//			listener.onDoubleClick();
			return true;
		}

		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			// TODO Auto-generated method stub
			return super.onSingleTapUp(e);
		}

		@Override
		public void onLongPress(MotionEvent e) {
			// TODO Auto-generated method stub
			super.onLongPress(e);
		}

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			return super.onScroll(e1, e2, distanceX, distanceY);
		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			// TODO Auto-generated method stub

			return super.onFling(e1, e2, velocityX, velocityY);
		}

		@Override
		public void onShowPress(MotionEvent e) {
			// TODO Auto-generated method stub
			super.onShowPress(e);
		}

		@Override
		public boolean onDoubleTapEvent(MotionEvent e) {
			// TODO Auto-generated method stub
			return super.onDoubleTapEvent(e);
		}

		@Override
		public boolean onSingleTapConfirmed(MotionEvent e) {
			// TODO Auto-generated method stub
			return super.onSingleTapConfirmed(e);
		}
	}
}
