package Point;

import java.util.ArrayList;

public class UpPointPath {
	// 4个参数连成一条线
	public static ArrayList<String> pathStringArrayList = new ArrayList<String>();

	/**
	 * 
	 * @param startX
	 * @param startY
	 * @param stopX
	 * @param stopY
	 * @param penSize	笔大小
	 * @param colorString 颜色代码
	 */
	public void rememberPath(int startX, int startY, int stopX, int stopY,
			int penSize,String colorString) {
		pathStringArrayList.add(startX + "," + startY + "," + stopX + ","
				+ stopY + "," + penSize+","+colorString);
	}

}