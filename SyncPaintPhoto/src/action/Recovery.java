package action;

import global.MyGlobal;

import java.util.ArrayList;

import android.R.integer;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Bitmap.Config;
/**
 * 恢复撤销
 * @author cc
 *
 */
public class Recovery {
	public Bitmap getBitmap(Paint paint, Bitmap baseBitmap,
			ArrayList<ArrayList<String>> dppArrayList, int recoveryNumber) {
		Bitmap bitmap = Bitmap.createBitmap(MyGlobal.SCREE_WIDTH, MyGlobal.SCREE_HEIGHT, Config.ARGB_8888);// 创建出一个空的图片
		baseBitmap = bitmap;
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

		if(recoveryNumber==-1){
			
		}else {
			dppArrayList.add(MyGlobal.RECOVERY.get(recoveryNumber));
			MyGlobal.RECOVERY.remove(recoveryNumber);
			canvas.drawBitmap(baseBitmap, new Matrix(), paint);
		}

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
}
