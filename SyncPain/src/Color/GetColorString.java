package Color;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class GetColorString {
	String colorString="";
	public GetColorString(Graphics2D g2d,String position) {
		MyColorHashMap colorHashMap2 = new MyColorHashMap();
		HashMap<String, String> colorHashMap = colorHashMap2.getColorHashMap();
		Set<String> keySet = colorHashMap.keySet();
		Iterator<String> iterator = keySet.iterator();
		// �ı���ɫ
		while (iterator.hasNext()) {
			String key = iterator.next();
			String colorStringValu = colorHashMap.get(key);
			if (key.equals(position)) {
				g2d.setColor(Color.decode(colorStringValu));// �ӷ�������Ϣ�л����ɫ
				this.colorString = colorStringValu;
//				System.out.println("��hasNext�л�õ��ǣ�" + colorStringValu);
			}
		}
	}
	/**
	 * ���colorStringValue
	 * @return
	 */
	public String getColorStringValue(){
		return colorString;
	}
}
