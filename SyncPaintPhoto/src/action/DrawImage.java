package action;

import global.MyGlobal;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import color.ColorHashMap;

import com.czc.clientsocket.MainActivity;

import Point.UpPointPath;
import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Bitmap.Config;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.widget.EditText;
import connectServer.ConnectServer;
/**
 * ��ͼ
 * @author cc
 *
 */
public class DrawImage {

	/**
	 * �ƶ���
	 * 
	 * @param act
	 * @param baseBitmap
	 * @param canvas
	 * @param showPopuWindow
	 * @param pen
	 * @param event
	 * @param startX
	 * @param startY
	 * @param stopX
	 * @param stopY
	 * @param upp
	 * @return
	 */
	public Bitmap DrawImageBitmap(MainActivity act, Bitmap baseBitmap,
			Canvas canvas, String colorString, Paint pen,
			MotionEvent event, int startX, int startY, int stopX, int stopY,
			UpPointPath upp) {
		int width = 0, height = 0;
		// ��ñʵĴ�Сֵ
		int size = MyGlobal.PEN_SIZE;
		act.initDrawer(baseBitmap, canvas, pen, colorString, size);
		// �����������Ƥ�߰�ť
		if (act.isEraser) {
			colorString = MyGlobal.CANVAS_BACKGROUND;
			act.initDrawer(baseBitmap, canvas, pen, colorString, size);
		}
		// �ڻ����ϻ���
		// ʵʱ��������ͱʵ���ɫ��С
		canvas.drawLine(startX, startY, stopX, stopY, pen);
		// ��¼һ���ڵ����еĵ�
		upp.rememberPath(startX, startY, stopX, stopY, size, colorString);
		// ֱ�ӷ�����ɫ�����ȥ  #ff0000
		String senParamentString = startX + "," + startY + "," + stopX + ","
				+ stopY + "," + colorString + "," + size + "," + "2";
		// ���ͻ�ͼ��Ϣ���ַ���
		new ConnectServer(senParamentString, act).start();
		return baseBitmap;
	}

	/**
	 * ����l
	 * 
	 * @param act
	 * @param event
	 * @param baseBitmap
	 * @param canvas
	 * @param pen
	 * @param startX
	 * @param startY
	 * @param showPopuWindow
	 */
	public void DrawImageDown(MainActivity act, MotionEvent event,
			Bitmap baseBitmap, Canvas canvas, Paint pen, int startX,
			int startY, String colorString) {
		//�ȳ�ʼ��һ��
		act.initDrawer(baseBitmap, canvas, pen, "#ff0000", 1);
		if (act.back) {// �ж��Ƿ����˳�����ť
			 baseBitmap = act.afterCancelBitmap;
			canvas = new Canvas(baseBitmap);
			act.back = false;
		}
		act.bt_back.setEnabled(true);
		act.initDrawer(baseBitmap, canvas, pen, colorString, 25);
		// �÷�����֪��Ҫ��ʼ�滭��
		new ConnectServer(MyGlobal.START_DRAW, act).start();
//		new ConnectServer(MyGlobal.START_PAIN+"", act).start();
	}
}
