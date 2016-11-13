package util;

public class RGBToString {
	/**
	 * 十进制转为十六进制方法
	 * 
	 * @param nibble
	 * @return
	 */
	private String getHex(int nibble) {
		switch (nibble) {
		case 0:
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
			return String.valueOf(nibble);
		case 10:
			return "a";
		case 11:
			return "b";
		case 12:
			return "c";
		case 13:
			return "d";
		case 14:
			return "e";
		case 15:
			return "f";
		}
		return "";

	}

	/**
	 * 把十进制转为十六进制
	 * 
	 * @param a
	 * @return
	 */
	private String f(int a) {
		String hex = getHex(a / 16);
		int g = a % 16;// 余数
		String hex2 = getHex(g % 16);
		return hex + hex2;
	}

	public String getStringFromRGB(int r,int g,int b) {
		String rs = f(r);
		String gs = f(g);
		String bs = f(b);
		return "#"+rs+gs+bs;
	}
}
