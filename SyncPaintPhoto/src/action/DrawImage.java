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
 * 绘图
 * @author cc
 *
 */
public class DrawImage {

	/**
	 * 移动中
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
		// 获得笔的大小值
		int size = MyGlobal.PEN_SIZE;
		act.initDrawer(baseBitmap, canvas, pen, colorString, size);
		// 如果按下了橡皮檫按钮
		if (act.isEraser) {
			colorString = MyGlobal.CANVAS_BACKGROUND;
			act.initDrawer(baseBitmap, canvas, pen, colorString, size);
		}
		// 在画布上画线
		// 实时更新坐标和笔的颜色大小
		canvas.drawLine(startX, startY, stopX, stopY, pen);
		// 记录一笔内的所有的点
		upp.rememberPath(startX, startY, stopX, stopY, size, colorString);
		// 直接发送颜色代码过去  #ff0000
		String senParamentString = startX + "," + startY + "," + stopX + ","
				+ stopY + "," + colorString + "," + size + "," + "2";
		// 发送绘图信息的字符串
		new ConnectServer(senParamentString, act).start();
		return baseBitmap;
	}

	/**
	 * 按下l
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
		//先初始化一下
		act.initDrawer(baseBitmap, canvas, pen, "#ff0000", 1);
		if (act.back) {// 判断是否按下了撤销按钮
			 baseBitmap = act.afterCancelBitmap;
			canvas = new Canvas(baseBitmap);
			act.back = false;
		}
		act.bt_back.setEnabled(true);
		act.initDrawer(baseBitmap, canvas, pen, colorString, 25);
		// 让服务器知道要开始绘画了
		new ConnectServer(MyGlobal.START_DRAW, act).start();
//		new ConnectServer(MyGlobal.START_PAIN+"", act).start();
	}
}
