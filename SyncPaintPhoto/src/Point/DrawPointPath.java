package Point;

import java.util.ArrayList;

public class DrawPointPath {
	public static ArrayList<ArrayList<String>> dpointPath = new ArrayList<ArrayList<String>>();
	// 获得路径
	public ArrayList<ArrayList<String>> getuppointpath() {
		return dpointPath;
	}

	// 存放路径
	public void setuppointpath(ArrayList<String> uppointpath) {
		dpointPath.add(uppointpath);
	}
}
