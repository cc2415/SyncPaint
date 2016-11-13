 package action;

import global.MyGlobal;

import java.util.ArrayList;

import view.MatrixImageView;

import com.czc.clientsocket.MainActivity;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Bitmap.Config;
/**
 * 撤销
 * @author cc
 *
 */
public class BackDraw {
	/**
	 * 
	 * @param bitmap
	 * @param canvas
	 * @param paint
	 * @param dppArrayList
	 * @param backNumber
	 * @return
	 */
	public Bitmap getBitmap(Paint paint,Bitmap baseBitmap,
			ArrayList<ArrayList<String>> dppArrayList, int backNumber) {
		Bitmap bitmap = Bitmap.createBitmap(MyGlobal.SCREE_WIDTH, MyGlobal.SCREE_HEIGHT, Config.ARGB_8888);// 创建出一个空的图片
		baseBitmap=bitmap;
		// 创建一张画布
		Canvas canvas = new Canvas(baseBitmap);
		// 画布背景为灰色
		canvas.drawColor(Color.parseColor(MyGlobal.CANVAS_BACKGROUND));
		// 创建画笔
		paint = new Paint();
		paint.setDither(true);
		paint.setAntiAlias(true);// 设置画笔的锯齿效果
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeCap(Paint.Cap.ROUND);// 设置笔的笔头
		paint.setStrokeJoin(Paint.Join.ROUND);// 设置连接点
		paint.setDither(true);
		
		//把要撤销的放入恢复中
		MyGlobal.RECOVERY.add(dppArrayList.get(backNumber));
//		MyGlobal.SMART_TEMP.add(dppArrayList.get(backNumber));//放入临时中，用来找出最大x和y
		System.out.println("我在backdraw中：recovery的size是:"+MyGlobal.RECOVERY.size());
		dppArrayList.remove(backNumber);
		canvas.drawBitmap(baseBitmap, new Matrix(), paint);

		if (dppArrayList.size() == 0) {
			return baseBitmap;
		} else {
			for (int i = 0; i < dppArrayList.size(); i++) {
				ArrayList<String> arrayList = dppArrayList.get(i);
				for (int j = 0; j < arrayList.size(); j++) {
					String string = arrayList.get(j);
					String[] ss = string.split(",");
					int x1 = Integer.parseInt(ss[0]);
					int y1 = Integer.parseInt(ss[1]);
					int x2 = Integer.parseInt(ss[2]);
					int y2 = Integer.parseInt(ss[3]);
					paint.setStrokeWidth(Integer.parseInt(ss[4]));// 设置画笔的大小
					paint.setColor(Color.parseColor(ss[5]));
					// 先将灰色背景画上
					canvas.drawLine(x1, y1, x2, y2, paint);
//					System.out.println(x1+"  "+y1+"  "+x2+"  "+y2);
				}
			}
			return baseBitmap;
		}
	}
	
	public Bitmap smart_backBitmap(Paint paint,Bitmap baseBitmap,
			ArrayList<ArrayList<String>> dppArrayList, int backNumber) {
		Bitmap bitmap = Bitmap.createBitmap(MyGlobal.SCREE_WIDTH, MyGlobal.SCREE_HEIGHT, Config.ARGB_8888);// 创建出一个空的图片
		baseBitmap=bitmap;
		// 创建一张画布
		Canvas canvas = new Canvas(baseBitmap);
		// 画布背景为灰色
		canvas.drawColor(Color.parseColor(MyGlobal.CANVAS_BACKGROUND));
		// 创建画笔
		paint = new Paint();
		paint.setDither(true);
		paint.setAntiAlias(true);// 设置画笔的锯齿效果
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeCap(Paint.Cap.ROUND);// 设置笔的笔头
		paint.setStrokeJoin(Paint.Join.ROUND);// 设置连接点
		paint.setDither(true);
		
//		dppArrayList.remove(backNumber);
		canvas.drawBitmap(baseBitmap, new Matrix(), paint);
		
		if (dppArrayList.size() == 0) {
			return baseBitmap;
		} else {
			for (int i = 0; i < dppArrayList.size(); i++) {
				ArrayList<String> arrayList = dppArrayList.get(i);
				for (int j = 0; j < arrayList.size(); j++) {
					String string = arrayList.get(j);
					String[] ss = string.split(",");
					int x1 = Integer.parseInt(ss[0]);
					int y1 = Integer.parseInt(ss[1]);
					int x2 = Integer.parseInt(ss[2]);
					int y2 = Integer.parseInt(ss[3]);
					paint.setStrokeWidth(Integer.parseInt(ss[4]));// 设置画笔的大小
					paint.setColor(Color.parseColor(ss[5]));
					// 先将灰色背景画上
					canvas.drawLine(x1, y1, x2, y2, paint);
				}
			}
			return baseBitmap;
		}
	}

	
	
	// public BackDraw(ArrayList<Bitmap> dpp, MatrixImageView iv,
	// Bitmap afterCancelBitmap, boolean back) {
	// // bitmap撤销
	// int index = 0;
	// if (dpp.size() > 0) {
	// index = dpp.size() - 2;// 获得最后一副画
	// if (index == -1) {
	// index = 0;
	// Bitmap bm = Bitmap.createBitmap(720, 922, Config.ARGB_8888);
	// iv.setImageBitmap(bm);
	// afterCancelBitmap = Bitmap.createBitmap(bm);
	// dpp.remove(0);
	// back = true;
	// return;
	// } else {
	// System.out.println("当前显示的是第：" + index + " 个位置");
	// Bitmap bitmap = dpp.get(index);
	// afterCancelBitmap = Bitmap.createBitmap(bitmap);// 给Imagview，防止错乱
	// iv.setImageBitmap(bitmap);
	// System.out.println("要删除的位置是：" + index + 1);
	// dpp.remove(index + 1);
	// back = true;
	// }
	// }
	// }
}
