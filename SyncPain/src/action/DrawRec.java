package action;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class DrawRec {
	String[] fourPoint;
	public DrawRec(Graphics2D g2d,String[] fourPoint){
		this.fourPoint=fourPoint;
		String p0=fourPoint[0];
		String[] a = p0.split(",");
		int x=Integer.parseInt(a[0]);
		int y=Integer.parseInt(a[1]);
		Integer[] heightAndWidth = getHeightAndWidth(fourPoint);
		System.out.println(a[4]+" wo zai drawrec ");
		BasicStroke basicStroke = new BasicStroke(Integer.parseInt(a[4]), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
		g2d.setStroke(basicStroke);//笔画大小
		g2d.setColor(Color.decode(a[5]));
		g2d.drawRect(x, y, heightAndWidth[1], heightAndWidth[0]);
	}
	/**
	 * 获得矩形的宽高
	 * @param fourPoint
	 * @return
	 */
	public Integer[] getHeightAndWidth(String[] fourPoint){
		String p0=fourPoint[0];
		String p1=fourPoint[1];
		String[] a = p0.split(",");
		String[] b = p1.split(",");
		int Height=Integer.parseInt(a[3])-Integer.parseInt(a[1]);
		int Width=Integer.parseInt(b[2])-Integer.parseInt(b[0]);
		Integer[] hAw={Height,Width};
		return hAw;
	}
}
