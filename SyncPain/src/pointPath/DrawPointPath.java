package pointPath;

import java.util.ArrayList;

/**
 * 存放按下到松开的路径，一笔
 * 
 * @author cc
 * 
 */
public class DrawPointPath {

	public  static ArrayList<ArrayList<String>> dpointPath=new ArrayList<ArrayList<String>>();

	// 获得路径
	public ArrayList<ArrayList<String>> getuppointpath() {
		return dpointPath;
	}

	// 存放路径
	public void setuppointpath(ArrayList<String> uppointpath) {
		dpointPath.add(uppointpath);
	}
}
