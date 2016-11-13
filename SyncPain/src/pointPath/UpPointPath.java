package pointPath;

import java.util.ArrayList;
/**
 * 记录按下到松开的路径，一笔内的所有的点
 * @author cc
 *
 */
public class UpPointPath {
	//4个参数连成一条线
	public static ArrayList<String> pathStringArrayList=new ArrayList<String>();
	/**
	 *  记录一条线后重置
	 * @param startX 	A点位置x
	 * @param startY	A点位置y
	 * @param stopX	B点位置x
	 * @param stopY	B点位置y
	 * @param penSize	笔大小 1
	 * @param color	颜色代码#ff0000
	 */
	public void rememberPath(int startX,int startY,int stopX,int stopY,int penSize,String color){
		pathStringArrayList.add(startX+","+startY+ ","+stopX+ ","+stopY+","+penSize+","+color);
	}
	
}
