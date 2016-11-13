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
	Vector v = new Vector();// ���ϣ��̰߳�ȫ���÷���ArrayListһ��
	public TCPChatServer() {
		frame = new JFrame("��������");
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

			// Ϊ���ܹ����ܶ���ͻ��˵�����
			while (true) {
				Socket s = ss.accept();// ���տͻ�����������

				BufferedReader br = new BufferedReader(new InputStreamReader(
						s.getInputStream()));

				// ��������
				PrintWriter pw = new PrintWriter(s.getOutputStream());

				// ����������뼯��
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
					String line = br.readLine();//���ܵ����ַ���
					txtArea.append(line + "\r\n");

					// ��������
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
