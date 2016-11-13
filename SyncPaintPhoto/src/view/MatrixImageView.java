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
 * @Description: ���Ŵ���С���ƶ�Ч����ImageView
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
			MyGlobal.SCREE_HEIGHT, Config.ARGB_8888);// ������һ���յ�ͼƬ
	
	
	Canvas canvas = new Canvas(baseBitmap);
	Paint pen = new Paint();
	EditText pensize;
	/**
	 * ������ȡmainactivity��������
	 * 
	 */
	private Context contexts;
	/**
	 * ���MainActivity�����
	 */
	MainActivity act;

	int width = 0, height = 0, number = 0;
	PointF movePointf;

	private final static String TAG = "MatrixImageView";
	private GestureDetector mGestureDetector;
	/** ģ��Matrix�����Գ�ʼ�� */
	private Matrix mMatrix = new Matrix();
	/** ͼƬ���� */
	private float mImageWidth;
	/** ͼƬ�߶� */
	private float mImageHeight;

	// ��xml�ļ��д��������
	public MatrixImageView(Context context, AttributeSet attrs) {
		super(context, attrs);

		this.contexts = context;
		act = (MainActivity) this.contexts;
		//111
		if (!MyGlobal.te) {
			canvas.drawColor(Color.parseColor(MyGlobal.CANVAS_BACKGROUND));// ���û����ı�����ɫ		
		}
		// ���þ��μ���
		MatrixTouchListener mListener = new MatrixTouchListener();
		setOnTouchListener(mListener);
		mGestureDetector = new GestureDetector(getContext(),
				new GestureListener(mListener));
		// ��������Ϊbalck
//		setBackgroundColor(Color.BLACK);
		// ��������������ΪFIT_CENTER����ʾ��ͼƬ����������/��С��View�Ŀ�ȣ�������ʾ
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
		// ������ͼƬ�󣬻�ȡ��ͼƬ������任����
		mMatrix.set(getImageMatrix());
		float[] values = new float[9];
		mMatrix.getValues(values);
		// ͼƬ���Ϊ��Ļ��ȳ����ű���
		mImageWidth = getWidth() / values[Matrix.MSCALE_X];
		mImageHeight = (getHeight() - values[Matrix.MTRANS_Y] * 2)
				/ values[Matrix.MSCALE_Y];
	}

	public class MatrixTouchListener implements OnTouchListener {
		/** ������Ƭģʽ */
		private static final int MODE_DRAG = 1;
		/** �Ŵ���С��Ƭģʽ */
		private static final int MODE_ZOOM = 2;
		/** ��֧��Matrix */
		private static final int MODE_UNABLE = 3;
		/** ������ż��� */
		float mMaxScale = 6;
		/** ˫��ʱ�����ż��� */
		float mDobleClickScale = 2;
		private int mMode = 0;//
		/** ���ſ�ʼʱ����ָ��� */
		private float mStartDis;
		/** ��ǰMatrix */
		private Matrix mCurrentMatrix = new Matrix();

		/** ���ڼ�¼��ʼʱ�������λ�� */
		private PointF startPoint = new PointF();

		int startX, startY;

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			switch (event.getActionMasked()) {
			case MotionEvent.ACTION_DOWN:
				// �����϶�ģʽ
				mMode = MODE_DRAG;
				startPoint.set(event.getX(), event.getY());
				isMatrixEnable();
				//1111111111111111
				
				if(MyGlobal.te){
					System.out.println(MyGlobal.CHOSE_PIC);
					canvas.drawBitmap(MyGlobal.CHOSE_PIC, new Matrix(), pen);
					
					MyGlobal.te=false;
				}
				
				// ԭ����/////////////////////////////////////////////////////////
				startX = (int) event.getX();
				startY = (int) event.getY();
				MyGlobal.left = startX;
				MyGlobal.top = startY;
//				System.out.println(MyGlobal.left + "    " + MyGlobal.top);
				if (act.back) {// �ж��Ƿ����˳�����ť
					baseBitmap = act.afterCancelBitmap;
					// System.out.println(baseBitmap + "===down222");
					canvas = new Canvas(baseBitmap);
					act.back = false;
					// System.out.println("������");
				}
				act.bt_back.setEnabled(true);
				act.initDrawer(baseBitmap, canvas, pen, MyGlobal.color, 2);
				
				// �÷�����֪��Ҫ��ʼ�滭��
				new ConnectServer(MyGlobal.START_DRAW, act).start();

				// ////////////////////////////////////////////////////////////////////

				// ///////////////�µ�
				// di = new DrawImage();
				// startX = (int) event.getX();
				// startY = (int) event.getY();
				// di.DrawImageDown(act, event, baseBitmap, canvas, pen, startX,
				// startY, showPopuWindow);

				break;
			case MotionEvent.ACTION_UP:
				if (MyGlobal.TYPE_SMART) {// ʶ��ģʽ
					dpp.setuppointpath(upp.pathStringArrayList);// ��Ӱ��µ��ɿ��ıʻ�
					MyGlobal.SMART_TEMP.add(upp.pathStringArrayList);//���һ�ʣ������Ƚϴ�С
					upp.pathStringArrayList = new ArrayList<String>();
					// //////////1��ʶ�����//////////////
					Judge judge=new Judge(dpp);
					MyGlobal.IS_RECTANGLE=judge.checkRectangle();
					boolean hand_out=false;
					if(MyGlobal.IS_RECTANGLE){//����Ǿ���
						Rectangle r=new Rectangle(contexts, act, canvas, pen, dpp, upp);
						r.finshRect();
						hand_out=true;
					}
					
					//////ͨ��////////////////////////////////////////////
					if(!hand_out){
						new ConnectServer(MyGlobal.HAND_UP, act).start();//���÷�������¼һ��
					}
					MyGlobal.SMART_TEMP=new ArrayList<ArrayList<String>>();
					
				} else {//��ͨģʽ
					// ��¼һ��
					dpp.setuppointpath(upp.pathStringArrayList);
					upp.pathStringArrayList = new ArrayList<String>();// ��ռ�¼
					// �����ɿ�����
					new ConnectServer(MyGlobal.HAND_UP, act).start();
					System.out.println("x:"+event.getX()+"  y:"+event.getY());
					try {//ʵʱ����
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
				// System.out.println("MatrixImageView�ƶ���");

				// else if (mMode==MODE_DRAG) {
				// setDragMatrix(event);
				// }

				// �����ǲ���
				if (mMode == MODE_DRAG && (act.type % 2 != 0)) {
					setDragMatrix(event);
				} else {
					if (MyGlobal.TYPE_SMART) {// ʶ��ģʽ
						myDraw(event);
					} else {// ��ͨģʽ
							// �µ�
							// û����///////////////////////////////////////////////////////////
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
				float dx = event.getX() - startPoint.x; // �õ�x����ƶ�����
				float dy = event.getY() - startPoint.y; // �õ�x����ƶ�����
				// �����˫����ͻ,����10f�������϶�
				if (Math.sqrt(dx * dx + dy * dy) > 10f) {
					startPoint.set(event.getX(), event.getY());
					// �ڵ�ǰ�������ƶ�
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
			if (act.TYPE_CLEAN == 1) {// �����ť��������
				baseBitmap = null;
				baseBitmap = Bitmap.createBitmap(MyGlobal.SCREE_WIDTH,
						MyGlobal.SCREE_HEIGHT, Config.ARGB_8888);// ������һ���յ�ͼƬ
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
		 * �ж����ż����Ƿ��Ǹı��
		 * 
		 * @return true��ʾ�ǳ�ʼֵ,false��ʾ��ʼֵ
		 */
		private boolean isZoomChanged() {
			float[] values = new float[9];
			getImageMatrix().getValues(values);
			// ��ȡ��ǰX�����ż���
			float scale = values[Matrix.MSCALE_X];
			// ��ȡģ���X�����ż����������Ƚ�
			mMatrix.getValues(values);
			return scale != values[Matrix.MSCALE_X];
		}

		/**
		 * �͵�ǰ����Աȣ�����dy��ʹͼ���ƶ��󲻻ᳬ��ImageView�߽�
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
		 * �͵�ǰ����Աȣ�����dx��ʹͼ���ƶ��󲻻ᳬ��ImageView�߽�
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
		 * ��������Matrix
		 * 
		 * @param event
		 */
		private void setZoomMatrix(MotionEvent event) {
			// ֻ��ͬʱ�����������ʱ���ִ��
			if (event.getPointerCount() < 2)
				return;
			float endDis = distance(event);// ��������
			if (endDis > 10f) { // ������ָ��£��һ���ʱ�����ش���10
				float scale = endDis / mStartDis;// �õ����ű���
				mStartDis = endDis;// ���þ���
				mCurrentMatrix.set(getImageMatrix());// ��ʼ��Matrix
				float[] values = new float[9];
				mCurrentMatrix.getValues(values);

				scale = checkMaxScale(scale, values);
				setImageMatrix(mCurrentMatrix);
			}
		}

		/**
		 * ����scale��ʹͼ�����ź󲻻ᳬ�������
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
		 * ����Matrix
		 */
		private void reSetMatrix() {
			if (checkRest()) {
				mCurrentMatrix.set(mMatrix);
				setImageMatrix(mCurrentMatrix);
			}
		}

		/**
		 * �ж��Ƿ���Ҫ����
		 * 
		 * @return ��ǰ���ż���С��ģ�����ż���ʱ������
		 */
		private boolean checkRest() {
			// TODO Auto-generated method stub
			float[] values = new float[9];
			getImageMatrix().getValues(values);
			// ��ȡ��ǰX�����ż���
			float scale = values[Matrix.MSCALE_X];
			// ��ȡģ���X�����ż����������Ƚ�
			mMatrix.getValues(values);
			return scale < values[Matrix.MSCALE_X];
		}

		/**
		 * �ж��Ƿ�֧��Matrix
		 */
		private void isMatrixEnable() {
			// �����س���ʱ����������
			if (getScaleType() != ScaleType.CENTER) {
				setScaleType(ScaleType.MATRIX);
			} else {
				mMode = MODE_UNABLE;// ����Ϊ��֧������
			}
		}

		/**
		 * ����������ָ��ľ���
		 * 
		 * @param event
		 * @return
		 */
		private float distance(MotionEvent event) {
			float dx = event.getX(1) - event.getX(0);
			float dy = event.getY(1) - event.getY(0);
			/** ʹ�ù��ɶ���������֮��ľ��� */
			return (float) Math.sqrt(dx * dx + dy * dy);
		}

		/**
		 * ˫��ʱ����
		 */
		public void onDoubleClick() {
			float scale = isZoomChanged() ? 1 : mDobleClickScale;
			mCurrentMatrix.set(mMatrix);// ��ʼ��Matrix
			mCurrentMatrix.postScale(scale, scale, getWidth() / 2,
					getHeight() / 2);
			setImageMatrix(mCurrentMatrix);
		}
	}

	// ���Ƽ���
	private class GestureListener extends SimpleOnGestureListener {
		private final MatrixTouchListener listener;

		public GestureListener(MatrixTouchListener listener) {
			this.listener = listener;
		}

		@Override
		public boolean onDown(MotionEvent e) {
			// ����Down�¼�
			return true;
		}

		@Override
		public boolean onDoubleTap(MotionEvent e) {
			// ����˫���¼�
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
