package action;

import global.MyGlobalVarable;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.KeyboardFocusManager;
import java.awt.Shape;
import java.awt.Stroke;
import java.util.ArrayList;

import javax.swing.text.html.ImageView;

import Color.GetColorString;
import Color.MyColorHashMap;

/**
 * 撤销
 * 
 * @author cc
 * 
 */
public class BackDraw {
	public BackDraw(Graphics2D g2d, ArrayList<ArrayList<String>> dppArrayList,
			int backDrawNumber) {// backDrawNumber要撤销的
		// g2d.setColor(Color.white);
		if (backDrawNumber == -1) {
			backDrawNumber = 0;
		}
		//把撤销的放入恢复中
		MyGlobalVarable.RECOVERY_ARRAYLIST.add(dppArrayList.get(backDrawNumber));
		dppArrayList.remove(backDrawNumber);
		System.out.println("IN backdraw : backnumber:" + backDrawNumber + "remove后size:"
				+ dppArrayList.size());
		if (dppArrayList.size() == 0) {
			g2d.clearRect(0, 0, 720, 922);
		} else {
			g2d.clearRect(0, 0, 720, 922);
			for (int i = 0; i < dppArrayList.size(); i++) {
				// arrayList2的字符串是：startX,startY,stopX,stopY,penSize,ColorString
				ArrayList<String> arrayList2 = dppArrayList.get(i);
				for (int j = 0; j < arrayList2.size(); j++) {
					String string = arrayList2.get(j);
					String[] ss = string.split(",");
					int x1 = Integer.parseInt(ss[0]);
					int y1 = Integer.parseInt(ss[1]);
					int x2 = Integer.parseInt(ss[2]);
					int y2 = Integer.parseInt(ss[3]);
					BasicStroke st = new BasicStroke(Integer.parseInt(ss[4]),
							BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
					g2d.setStroke(st);// 设置大小
					g2d.setColor(Color.decode(ss[5]));
					g2d.drawLine(x1, y1, x2, y2);
				}
			}
			// dppArrayList.remove(backDrawNumber);
			// 把撤销的笔画放入恢复中
			// GlobalVarable.RECOVERY_ARRAYLIST.add(dppArrayList.get(backDrawNumber));
			// dppArrayList.remove(backDrawNumber);//把这一笔删除
		}

	}
}
