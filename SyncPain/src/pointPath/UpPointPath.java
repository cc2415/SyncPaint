package pointPath;

import java.util.ArrayList;
/**
 * ��¼���µ��ɿ���·����һ���ڵ����еĵ�
 * @author cc
 *
 */
public class UpPointPath {
	//4����������һ����
	public static ArrayList<String> pathStringArrayList=new ArrayList<String>();
	/**
	 *  ��¼һ���ߺ�����
	 * @param startX 	A��λ��x
	 * @param startY	A��λ��y
	 * @param stopX	B��λ��x
	 * @param stopY	B��λ��y
	 * @param penSize	�ʴ�С 1
	 * @param color	��ɫ����#ff0000
	 */
	public void rememberPath(int startX,int startY,int stopX,int stopY,int penSize,String color){
		pathStringArrayList.add(startX+","+startY+ ","+stopX+ ","+stopY+","+penSize+","+color);
	}
	
}
