package Point;

import java.util.ArrayList;

public class UpPointPath {
	// 4����������һ����
	public static ArrayList<String> pathStringArrayList = new ArrayList<String>();

	/**
	 * 
	 * @param startX
	 * @param startY
	 * @param stopX
	 * @param stopY
	 * @param penSize	�ʴ�С
	 * @param colorString ��ɫ����
	 */
	public void rememberPath(int startX, int startY, int stopX, int stopY,
			int penSize,String colorString) {
		pathStringArrayList.add(startX + "," + startY + "," + stopX + ","
				+ stopY + "," + penSize+","+colorString);
	}

}