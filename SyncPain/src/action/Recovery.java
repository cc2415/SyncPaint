package action;

import global.MyGlobalVarable;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * 恢复撤销
 * @author cc
 *
 */
public class Recovery {
	public Recovery(Graphics2D g2d, ArrayList<ArrayList<String>> dppArrayList,
			int recoveryNumber) {// backDrawNumber要撤销的
		// g2d.setColor(Color.white);
		if (recoveryNumber == -1) {
			recoveryNumber = 0;
		}
		System.out.println("remove前size是：" + dppArrayList.size());
		//把撤销的放入恢复中
		dppArrayList.add(MyGlobalVarable.RECOVERY_ARRAYLIST.get(recoveryNumber));
		MyGlobalVarable.RECOVERY_ARRAYLIST.remove(recoveryNumber);
		if (dppArrayList.size() == 0) {
			g2d.clearRect(0, 0, 720, 922);
		} else {
			g2d.clearRect(0, 0, 720, 922);
			for (int i = 0; i < dppArrayList.size(); i++) {
				// arrayList2的字符串是：startX,startY,stopX,stopY,penSize,ColorString
				ArrayList<String> arrayList2 = dppArrayList.get(i);
				for (int j = 0; j < arrayList2.size(); j++) {
					String string = arrayList2.get(j);
					String[] ss = string.split(",");
					int x1 = Integer.parseInt(ss[0]);
					int y1 = Integer.parseInt(ss[1]);
					int x2 = Integer.parseInt(ss[2]);
					int y2 = Integer.parseInt(ss[3]);
					BasicStroke st = new BasicStroke(Integer.parseInt(ss[4]),
							BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
					g2d.setStroke(st);// 设置大小
					g2d.setColor(Color.decode(ss[5]));
					g2d.drawLine(x1, y1, x2, y2);
				}
			}
		}

	}
}
