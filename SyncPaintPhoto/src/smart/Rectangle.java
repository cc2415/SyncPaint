package smart;

import global.MyGlobal;

import java.io.IOException;
import java.util.ArrayList;

import save.SaveBitmapToJPG;
import util.RectangleGetMaxPoint;

import com.czc.clientsocket.MainActivity;

import connectServer.ConnectServer;

import Point.DrawPointPath;
import Point.UpPointPath;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

/**
 * 矩形
 * 
 * @author cc
 * 
 */
public class Rectangle {
	private Context contexts;
	private MainActivity act;
	private Canvas canvas;
	private Paint pen;
	private DrawPointPath dpp;
	private UpPointPath upp;
	public Rectangle(Context contexts,MainActivity act,Canvas canvas,Paint pen,DrawPointPath dpp,UpPointPath upp){
		this.contexts=contexts;
		this.act=act;
		this.canvas=canvas;
		this.pen=pen;
		this.dpp=dpp;
		this.upp=upp;
	}
	
	/**
	 * 画矩形
	 * 
	 * @param act
	 *            MainActivity
	 * @param canvas
	 * @param pen
	 * @param upp
	 * @param dpp
	 */
	public void drawRectangle(MainActivity act, Canvas canvas, Paint pen,
			UpPointPath upp, DrawPointPath dpp) {
		// Point p = g(dpp.dpointPath);
		RectangleGetMaxPoint r=new RectangleGetMaxPoint();
		Point p = r.g(MyGlobal.SMART_TEMP);
		MyGlobal.SMART_TEMP=new ArrayList<ArrayList<String>>();
		canvas = new Canvas(act.afterCancelBitmap);
		canvas.drawRect(MyGlobal.left, MyGlobal.top, p.x, p.y, pen);
		act.iv.setImageBitmap(act.afterCancelBitmap);
		MyGlobal.RECOVERY.remove(MyGlobal.RECOVERY.size() - 1);// 取消撤销的
		// 矩形4个点
		Point p1 = new Point((int) MyGlobal.left, (int) MyGlobal.top);
		Point p2 = new Point((int) MyGlobal.left, p.y);
		Point p3 = new Point(p.x, p.y);
		MyGlobal.Max = p3;
		Point p4 = new Point(p.x, (int) MyGlobal.top);

		System.out.println("p1,p2,p3,p4" + p1 + "  " + p2 + "   " + p3 + "  "
				+ p4);
		upp.rememberPath(p1.x, p1.y, p2.x, p2.y, MyGlobal.PEN_SIZE,
				MyGlobal.color);
		dpp.setuppointpath(upp.pathStringArrayList);MyGlobal.A_BROKE=upp.pathStringArrayList.get(0);
		upp.pathStringArrayList = new ArrayList<String>();

		upp.rememberPath(p2.x, p2.y, p3.x, p3.y, MyGlobal.PEN_SIZE,
				MyGlobal.color);
		dpp.setuppointpath(upp.pathStringArrayList);MyGlobal.B_BROKE=upp.pathStringArrayList.get(0);
		upp.pathStringArrayList = new ArrayList<String>();

		upp.rememberPath(p3.x, p3.y, p4.x, p4.y, MyGlobal.PEN_SIZE,
				MyGlobal.color);
		dpp.setuppointpath(upp.pathStringArrayList);MyGlobal.C_BROKE=upp.pathStringArrayList.get(0);
		upp.pathStringArrayList = new ArrayList<String>();

		upp.rememberPath(p4.x, p4.y, p1.x, p1.y, MyGlobal.PEN_SIZE,
				MyGlobal.color);
		dpp.setuppointpath(upp.pathStringArrayList);MyGlobal.D_BROKE=upp.pathStringArrayList.get(0);
		upp.pathStringArrayList = new ArrayList<String>();

	}
	
	public void finshRect(){
		new ConnectServer(MyGlobal.HAND_UP, act).start();//先让服务器记录一笔
		act.bt_back.performClick();//后退按钮点击事件
		drawRectangle(act, canvas, pen, upp, dpp);
		//发送矩形坐标
		new ConnectServer(MyGlobal.SMART_HAND_UP+MyGlobal.A_BROKE+":"+MyGlobal.B_BROKE+":"+MyGlobal.C_BROKE+":"+MyGlobal.D_BROKE, act).start();
		try {
			System.out.println(act.afterCancelBitmap+"  bitmap he name "+MyGlobal.SAVE_NAME);
			SaveBitmapToJPG s=new SaveBitmapToJPG(contexts, act.afterCancelBitmap, MyGlobal.SAVE_NAME);
			act.bt_back.setEnabled(true);
			act.bt_recover.setEnabled(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}//end class
