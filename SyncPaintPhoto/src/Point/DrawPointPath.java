package Point;

import java.util.ArrayList;

public class DrawPointPath {
	public static ArrayList<ArrayList<String>> dpointPath = new ArrayList<ArrayList<String>>();
	// ���·��
	public ArrayList<ArrayList<String>> getuppointpath() {
		return dpointPath;
	}

	// ���·��
	public void setuppointpath(ArrayList<String> uppointpath) {
		dpointPath.add(uppointpath);
	}
}
