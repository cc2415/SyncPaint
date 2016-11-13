package server;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Iterator;
import java.util.Vector;

public class SenToChi extends Thread {
	Vector v;
	String line;

	public SenToChi(Vector v, String line) {
		this.v = v;
		this.line = line;
	}

	@Override
	public void run() {
		while (true) {
			// 遍历集合
			Iterator it = v.iterator();
			while (it.hasNext()) {
				PrintWriter pw = (PrintWriter) it.next();
				pw.println(line);
				pw.close();
				System.out.println("发送了");
			}
		}
	}
}