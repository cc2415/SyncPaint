package Color;

import java.awt.Color;
import java.util.HashMap;

public class MyColorHashMap {
	public HashMap<String, String> getColorHashMap(){
		HashMap<String, String> hash=new HashMap<String, String>();
		//要和客户端的ColorHashMap相对应,也可以动态添加
		hash.put("0","#ff0000");
		hash.put("1", "#543044");
		hash.put("2", "#007d65");
		hash.put("3", "#6950a1");
		hash.put("4", "#FA8072");
		hash.put("5","#00ff00");
		hash.put("6", "#0000ff");
		return hash;
	}
}
