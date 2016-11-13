package server;

import global.MyGlobalVarable;

import java.awt.*;

import javax.swing.*;

import org.omg.CORBA.PUBLIC_MEMBER;

import Color.MyColorHashMap;
import action.BackDraw;
import action.DrawImager;
import action.DrawRec;
import action.Recovery;
import pointPath.DrawPointPath;
import pointPath.UpPointPath;

import java.net.*;
import java.io.*;
import java.util.*;

public class TCPChatServerByte extends JPanel {
	JFrame frame;
	JPanel panel;
	// JTextArea txtArea;
	// JScrollPane jsp;
	JButton jb;
	Vector v = new Vector();// 集合，线程安全。用法跟ArrayList一样
	Socket[] sockets=new Socket[11];
	private String[] strings = { "1", "1", "100", "100", "4", "4" };
	UpPointPath upPointPath = new UpPointPath();// 记录路径
	private MyColorHashMap getColor = new MyColorHashMap();
	DrawPointPath dpp = new DrawPointPath();
	ArrayList<String> rememberPath = new ArrayList<String>();

	public TCPChatServerByte() {
		frame = new JFrame("PC端");
		panel = this;
		frame.add(panel);
		frame.setBackground(Color.decode("#ffffff"));
		// panel = new JPanel();
		// txtArea = new JTextArea(20, 30);
		// jsp = new JScrollPane(txtArea);
		// JScrollBar jsb = jsp.getVerticalScrollBar();
		// panel.add(jsp);
		// frame.add(panel);
		// this 表示是jpannel
		// jb=new JButton("点击");
		// panel.add(jb);
		frame.setSize(720, 922);
		frame.setVisible(true);
		init();
	}

	public void init() {
		try {
			ServerSocket ss = new ServerSocket(6666);
			// 为了能够接受多个客户端的请求
			while (true) {
				int i=0;
				System.out.println("等待中------- ");
				Socket s = ss.accept();// 接收客户端连接请求
				System.out.println("有人链接了："+s.getInetAddress());
				BufferedReader br = new BufferedReader(new InputStreamReader(
						s.getInputStream()));
				
				// 获得输出
				PrintWriter pw = new PrintWriter(s.getOutputStream());
//				pw.write("你好  ");
				
//				BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
//				writer.write("ni hao ,huanyi ni ");
//				writer.close();
//				writer.flush();
//				System.out.println("fason chengg ");
//				pw.write("ni hao");
//				pw.flush();
				
//				 将输出流加入集合
				v.add(pw);
				Chat c = new Chat(br,pw);
				c.start();
				i++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class Chat extends Thread {

		BufferedReader br;
		BufferedWriter write;
		PrintWriter pw;
		public Chat(BufferedReader br,PrintWriter writer) {
			this.br = br;
			this.write=write;
			this.pw=writer;
		}

		public void run() {
			// while (true) {
			try {
				Graphics g = panel.getGraphics();
				Graphics2D g2d = (Graphics2D) g;
				String line = br.readLine();// 接受到的字符串
				// line：startx,starty,stopx,stopy,colorString,penSize,2
//				 System.out.println(line+"  wo shi line");
				String ge = line.substring(0, 2);
				if (line.equals(MyGlobalVarable.START_DRAW)) {// 绘图开始标志

				} else if (line.equals(MyGlobalVarable.CLEANIMAGER)) {
					// 清除图像
					g2d.clearRect(0, 0, 720, 922);
					dpp.dpointPath.removeAll(dpp.dpointPath);
					MyGlobalVarable.RECOVERY_ARRAYLIST = new ArrayList<ArrayList<String>>();
					// System.out.println(dpp.dpointPath.size()+" qingkon size");
				} else if (line.equals(MyGlobalVarable.REVOKE)) {// 撤销
				// System.out.println("撤销按下了");
					MyGlobalVarable.BACK_NUMBER = dpp.dpointPath.size() - 1;
					// System.out.println(dpp.dpointPath.size() + " da xiao ");
					new BackDraw(g2d, dpp.dpointPath,
							MyGlobalVarable.BACK_NUMBER);
				} else if (line.equals(MyGlobalVarable.RECOVERY)) {// 恢复撤销
					int recoveryNumber = MyGlobalVarable.RECOVERY_ARRAYLIST
							.size() - 1;
					new Recovery(g2d, dpp.dpointPath, recoveryNumber);
				} else if (line.equals(MyGlobalVarable.HAND_UP)) {// 普通模式 松开
					// 把之前记录的点作为arraylist记录下来
					dpp.setuppointpath(upPointPath.pathStringArrayList);
//					System.out.println(dpp.dpointPath.size()
//							+ " 普通模式松手  dpp.dpointpoath.size: "
//							+ dpp.dpointPath.size());
					upPointPath.pathStringArrayList = new ArrayList<String>();
					// System.out.println("重新画了：backNumber :"+GlobalVarable.BACK_NUMBER+"");
				} else if (ge.equals(MyGlobalVarable.SMART_HAND_UP)) {// 智能模式松开
					String[] fourPoint = line.substring(2, line.length())
							.split(":");
					// System.out.println(upPointPath.pathStringArrayList+"  ");
					upPointPath.pathStringArrayList.add(fourPoint[0]);
					dpp.setuppointpath(upPointPath.pathStringArrayList);
					upPointPath.pathStringArrayList = new ArrayList<String>();

					upPointPath.pathStringArrayList.add(fourPoint[1]);
					dpp.setuppointpath(upPointPath.pathStringArrayList);
					upPointPath.pathStringArrayList = new ArrayList<String>();

					upPointPath.pathStringArrayList.add(fourPoint[2]);
					dpp.setuppointpath(upPointPath.pathStringArrayList);
					upPointPath.pathStringArrayList = new ArrayList<String>();

					upPointPath.pathStringArrayList.add(fourPoint[3]);
					// System.out.println(upPointPath.pathStringArrayList+"    3");
					// System.out.println("tcp  dao  zhe l ");
					System.out.println("智能模式松开手  dpp.dpoipahtp.size:"
							+ dpp.dpointPath.size());
					dpp.setuppointpath(upPointPath.pathStringArrayList);
					upPointPath.pathStringArrayList = new ArrayList<String>();
					new DrawRec(g2d, fourPoint);
					MyGlobalVarable.RECOVERY_ARRAYLIST
							.remove(MyGlobalVarable.RECOVERY_ARRAYLIST.size() - 1);
				} else if (ge.equals(MyGlobalVarable.SCREEN)) {
					String[] wh = line.substring(2, line.length()).split(",");
					int width = Integer.parseInt(wh[0]);
					int height = Integer.parseInt(wh[1]);
					frame.setSize(width, height);
				} else {
//					 System.out.println(line+"   wo shi else");
					new DrawImager(line, g2d, upPointPath, getColor, dpp);
//					new SenToChi(v, line).start();;
				}
				// 遍历集合
				Iterator it = v.iterator();
				// while (it.hasNext()) {
				// PrintWriter pw = (PrintWriter) it.next();
				// pw.println(line);
				// pw.flush();
				// }
			} catch (Exception e) {
				e.printStackTrace();
				// }
			}
		}
	}

	public static void main(String[] args) {
		new TCPChatServerByte();
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		// 把撤销的放入恢复中
		if (dpp.dpointPath.size() == 0) {
			g2d.clearRect(0, 0, 720, 922);
		} else {
			g2d.clearRect(0, 0, 720, 922);
			for (int i = 0; i < dpp.dpointPath.size(); i++) {
				// arrayList2的字符串是：startX,startY,stopX,stopY,penSize,ColorString
				ArrayList<String> arrayList2 = dpp.dpointPath.get(i);
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
