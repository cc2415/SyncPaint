package util;

import global.MyGlobal;

import java.security.spec.MGF1ParameterSpec;
import java.util.ArrayList;

import android.graphics.Point;

public class RectangleGetMaxPoint {
	Point point;
//	public static RectangleGetMaxPoint mrect;
//	//单例模式，懒汉式变种
//	private RectangleGetMaxPoint() {};
//	public static synchronized RectangleGetMaxPoint mRectangleGetMaxPoint() {
//		if (mrect == null) {
//			mrect = new RectangleGetMaxPoint();
//			return mrect;
//		}
//		return mrect;
//	}

	/**
	 * 获得最大点
	 * @param dppArrayList
	 * @return
	 */
	public static Point g(ArrayList<ArrayList<String>> dppArrayList) {
		int MaxX = 0, MaxY = 0;
//		System.out.println(MyGlobal.SMART_TEMP+"  wo zai g limia");
		ArrayList<String> arrayList = dppArrayList.get(0);
		for (int j = 0; j < arrayList.size(); j++) {
			String string = arrayList.get(j);
			String[] ss = string.split(",");
			int x1 = Integer.parseInt(ss[0]);
			int y1 = Integer.parseInt(ss[1]);
			int x2 = Integer.parseInt(ss[2]);
			int y2 = Integer.parseInt(ss[3]);
			int t = checkMax(x1, x2);
			MaxX = checkMax(t, MaxX);
			int ty = checkMax(y1, y2);
			MaxY = checkMax(ty, MaxY);
		}
		Point poi = new Point();
		poi.set(MaxX, MaxY);
//		dppArrayList = new ArrayList<ArrayList<String>>();
		return poi;// 返回一个点
	}

	/**
	 * 取最大值
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int checkMax(int a, int b) {
		return a > b ? a : b;
	}

}
