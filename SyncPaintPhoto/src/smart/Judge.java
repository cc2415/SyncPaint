package smart;

import global.MyGlobal;

import java.util.ArrayList;

import util.RectangleGetMaxPoint;

import android.R.integer;
import android.graphics.Point;
import android.util.Log;

import Point.DrawPointPath;

/**
 * �ж��û�������ʲôͼ��
 * 
 * @author cc
 * 
 */
public class Judge {
	private int[] stxs;
	private int[] stys;
	private int[] sdxs;
	private int[] sdys;
	DrawPointPath dpp;

	public Judge(DrawPointPath dpp) {
		this.dpp = dpp;
		int b = dpp.dpointPath.size() - 1;
		ArrayList<String> pArrayList = dpp.dpointPath.get(b);
		stxs = new int[pArrayList.size() + 1];
		stys = new int[pArrayList.size() + 1];
		sdxs = new int[pArrayList.size() + 1];
		sdys = new int[pArrayList.size() + 1];
		for (int i = 0; i < pArrayList.size(); i++) {
			String[] s = pArrayList.get(i).split(",");
			int x1 = Integer.parseInt(s[0]);
			int y1 = Integer.parseInt(s[1]);
			int x2 = Integer.parseInt(s[2]);
			int y2 = Integer.parseInt(s[3]);
			stxs[i] = x1;
			stys[i] = y1;
			sdxs[i] = x2;
			sdys[i] = y2;
		}
	}

	public boolean checkRectangle() {
		RectangleGetMaxPoint rer=new RectangleGetMaxPoint();
//		Point p = RectangleGetMaxPoint.g(MyGlobal.SMART_TEMP);
		Point p = rer.g(MyGlobal.SMART_TEMP);
		Point p1 = new Point(stxs[0], stys[0]);
		Point p2 = new Point(stxs[0], p.y);
		Point p3 = new Point(p.x, p.y);
		Point p4 = new Point(p.x, stys[0]);
		int r=0,u=0,l=0,d=0;
		int polarization = 20;
		for (int i = 0; i < stxs.length; i++) {
			int a=change(polarization, new Point(stxs[i], stys[i]), new Point(sdxs[i],sdys[i]));
			if(a==1){
//				System.out.println("��ת���ˣ�"+stxs[i]+","+stys[i]+"  "+sdxs[i]+"��"+sdys[i]);
				r++;
			}else if(a==2){
//				System.out.println("��ת���ˣ�"+stxs[i]+","+stys[i]+"  "+sdxs[i]+"��"+sdys[i]);
				u++;
			}else if(a==3){
//				System.out.println("��ת���ˣ�"+stxs[i]+","+stys[i]+"  "+sdxs[i]+"��"+sdys[i]);
				l++;
			}else if(a==4){
				d++;
			}
			// �����еĵ����
//			System.out.println(i + " stx=" + stxs[i] + " sty=" + stys[i]
//					+ " sdx=" + sdxs[i] + " sdy=" + sdys[i]);
		}
		int u_p=0;
		for(int i=0;i<stxs.length;i++) {
//			if(sdys[a]<stys[a]){
//				u_p++;
//			}
//			if(u_p>2)up=true;
			int fd=change(polarization, new Point(stxs[i],stys[i]), new Point(sdxs[i],sdys[i]));
			if (fd==5) {
				u_p++;
			}
		}
		
//		System.out.println("leng: "+stxs.length+"");
		if (!(u_p>8)) {//�ж��ǲ������ϻ�       Ч�������룬��ȥ��
			if (r>1&&u>1&&l>1&&d>1) {
//				System.out.println("jude: ");
				return true;
			}else {
				return false;
			}
		}
		return false;
	}


	/**
	 * �ж�ת��
	 * @param polarization ��Χ
	 * @param startPoint
	 * @param stoPoint 
	 * @return
	 */
	public int change(int polarization,Point startPoint,Point stoPoint){
		if(stoPoint.x>startPoint.x&&stoPoint.x-startPoint.x<polarization&&stoPoint.x-startPoint.x>-polarization){
			//��ת  b��
			return 1;
		}
		else if(stoPoint.y<startPoint.y&&stoPoint.x-startPoint.x<polarization&&stoPoint.x-startPoint.x>-polarization){
			//��ת c��
			return 2;
		}
		else if(stoPoint.x<startPoint.x&&stoPoint.y-startPoint.y<polarization&&stoPoint.y-startPoint.y>-polarization){
			//��ת  d��
			return 3;
		}
		else if(stoPoint.y>startPoint.y&&stoPoint.x-startPoint.x<polarization&&stoPoint.x-startPoint.x>-polarization) {
			//a��
			return 4;
		}
		else if(stoPoint.y<startPoint.y&&stoPoint.x-startPoint.x<polarization&&stoPoint.x-startPoint.x>-polarization){
			//��a��
			return 5;
		}
		else {
			return 0;
		}
	}
	
	
}// end class
