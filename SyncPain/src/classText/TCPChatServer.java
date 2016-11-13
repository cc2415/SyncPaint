package classText;

import java.awt.*;
import javax.swing.*;

import java.net.*;
import java.io.*;

import java.util.*;

public class TCPChatServer {
	JFrame frame;
	JPanel panel;
	JTextArea txtArea;
	JScrollPane jsp;
	Vector v = new Vector();// 集合，线程安全。用法跟ArrayList一样
	public TCPChatServer() {
		frame = new JFrame("服务器端");
		panel = new JPanel();
		txtArea = new JTextArea(20, 30);
		jsp = new JScrollPane(txtArea);
		JScrollBar jsb = jsp.getVerticalScrollBar();
		panel.add(jsp);
		frame.add(panel);
		frame.setSize(350, 400);
		frame.setVisible(true);
		init();
	}

	public void init() {
		try {
			ServerSocket ss = new ServerSocket(6666);

			// 为了能够接受多个客户端的请求
			while (true) {
				Socket s = ss.accept();// 接收客户端连接请求

				BufferedReader br = new BufferedReader(new InputStreamReader(
						s.getInputStream()));

				// 获得输出流
				PrintWriter pw = new PrintWriter(s.getOutputStream());

				// 将输出流加入集合
				v.add(pw);
				Chat c = new Chat(br);
				c.start();

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class Chat extends Thread {

		BufferedReader br;

		public Chat(BufferedReader br) {
			this.br = br;
		}

		public void run() {
//			while (true) {
				try {
					String line = br.readLine();//接受到的字符串
					txtArea.append(line + "\r\n");

					// 遍历集合
					Iterator it = v.iterator();
					while (it.hasNext()) {
						PrintWriter pw = (PrintWriter) it.next();
						pw.println(line);
						pw.flush();
					}
				} catch (Exception e) {
					e.printStackTrace();
//				}
			}
		}
	}

	public static void main(String[] args) {
		new TCPChatServer();
	}

}
