package color;

import java.util.HashMap;
import java.util.Hashtable;

import android.R.integer;
import android.graphics.Color;

public class ColorHashMap {
	String BLACK;
	String BLUE;
	String CYAN;
	String DKGRAY;
	String GRAY;
	String GREEN;
	String LTGRAY;
	String MAGENTA;
	String RED;
	String TRANSPARENT;
	String WHITE;
	String YELLOW;
	public HashMap<String, String> getHashMap(){
		HashMap<String, String> hash=new HashMap<String, String>();
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
