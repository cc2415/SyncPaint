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
 * ����
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
		Bitmap bitmap = Bitmap.createBitmap(MyGlobal.SCREE_WIDTH, MyGlobal.SCREE_HEIGHT, Config.ARGB_8888);// ������һ���յ�ͼƬ
		baseBitmap=bitmap;
		// ����һ�Ż���
		Canvas canvas = new Canvas(baseBitmap);
		// ��������Ϊ��ɫ
		canvas.drawColor(Color.parseColor(MyGlobal.CANVAS_BACKGROUND));
		// ��������
		paint = new Paint();
		paint.setDither(true);
		paint.setAntiAlias(true);// ���û��ʵľ��Ч��
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeCap(Paint.Cap.ROUND);// ���ñʵı�ͷ
		paint.setStrokeJoin(Paint.Join.ROUND);// �������ӵ�
		paint.setDither(true);
		
		//��Ҫ�����ķ���ָ���
		MyGlobal.RECOVERY.add(dppArrayList.get(backNumber));
//		MyGlobal.SMART_TEMP.add(dppArrayList.get(backNumber));//������ʱ�У������ҳ����x��y
		System.out.println("����backdraw�У�recovery��size��:"+MyGlobal.RECOVERY.size());
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
					paint.setStrokeWidth(Integer.parseInt(ss[4]));// ���û��ʵĴ�С
					paint.setColor(Color.parseColor(ss[5]));
					// �Ƚ���ɫ��������
					canvas.drawLine(x1, y1, x2, y2, paint);
//					System.out.println(x1+"  "+y1+"  "+x2+"  "+y2);
				}
			}
			return baseBitmap;
		}
	}
	
	public Bitmap smart_backBitmap(Paint paint,Bitmap baseBitmap,
			ArrayList<ArrayList<String>> dppArrayList, int backNumber) {
		Bitmap bitmap = Bitmap.createBitmap(MyGlobal.SCREE_WIDTH, MyGlobal.SCREE_HEIGHT, Config.ARGB_8888);// ������һ���յ�ͼƬ
		baseBitmap=bitmap;
		// ����һ�Ż���
		Canvas canvas = new Canvas(baseBitmap);
		// ��������Ϊ��ɫ
		canvas.drawColor(Color.parseColor(MyGlobal.CANVAS_BACKGROUND));
		// ��������
		paint = new Paint();
		paint.setDither(true);
		paint.setAntiAlias(true);// ���û��ʵľ��Ч��
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeCap(Paint.Cap.ROUND);// ���ñʵı�ͷ
		paint.setStrokeJoin(Paint.Join.ROUND);// �������ӵ�
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
					paint.setStrokeWidth(Integer.parseInt(ss[4]));// ���û��ʵĴ�С
					paint.setColor(Color.parseColor(ss[5]));
					// �Ƚ���ɫ��������
					canvas.drawLine(x1, y1, x2, y2, paint);
				}
			}
			return baseBitmap;
		}
	}

	
	
	// public BackDraw(ArrayList<Bitmap> dpp, MatrixImageView iv,
	// Bitmap afterCancelBitmap, boolean back) {
	// // bitmap����
	// int index = 0;
	// if (dpp.size() > 0) {
	// index = dpp.size() - 2;// ������һ����
	// if (index == -1) {
	// index = 0;
	// Bitmap bm = Bitmap.createBitmap(720, 922, Config.ARGB_8888);
	// iv.setImageBitmap(bm);
	// afterCancelBitmap = Bitmap.createBitmap(bm);
	// dpp.remove(0);
	// back = true;
	// return;
	// } else {
	// System.out.println("��ǰ��ʾ���ǵڣ�" + index + " ��λ��");
	// Bitmap bitmap = dpp.get(index);
	// afterCancelBitmap = Bitmap.createBitmap(bitmap);// ��Imagview����ֹ����
	// iv.setImageBitmap(bitmap);
	// System.out.println("Ҫɾ����λ���ǣ�" + index + 1);
	// dpp.remove(index + 1);
	// back = true;
	// }
	// }
	// }
}
