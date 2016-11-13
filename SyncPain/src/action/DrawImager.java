package action;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import Color.GetColorString;
import Color.MyColorHashMap;
import pointPath.DrawPointPath;
import pointPath.UpPointPath;
/**
 * 绘图
 * @author cc
 *
 */
public class DrawImager {
	String[] strings;
	Graphics2D g2d;
	MyColorHashMap getColor;
	DrawPointPath dpp;

	public DrawImager(String line, Graphics2D g2d, UpPointPath upPointPath,
			MyColorHashMap getColor, DrawPointPath dpp) {
		this.g2d = g2d;
		this.getColor = getColor;
		this.dpp = dpp;
		strings = line.split(",");
//		System.out.println(strings[0] + "--" + strings[1] + "---" + strings[2]
//				+ "---" + strings[3] + "-----" + strings[4] + "----"
//				+ strings[5] + "," + strings[6]);
		int startX = Integer.parseInt(strings[0]);
		int startY = Integer.parseInt(strings[1]);
		int stopX = Integer.parseInt(strings[2]);
		int stopY = Integer.parseInt(strings[3]);
		int size = Integer.parseInt(strings[5]);
		//可以得到所有的点线了
		BasicStroke strokeSize = new BasicStroke(size, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_BEVEL);
		g2d.setStroke(strokeSize);// 画笔粗细和类型
		
//		
		g2d.setColor(Color.decode(strings[4]));
		upPointPath.rememberPath(startX, startY, stopX, stopY, size,strings[4]);
		g2d.drawLine(startX, startY, stopX, stopY);
//		System.out.println("dao zhe l ");

	}
}
