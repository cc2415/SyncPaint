package classText;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.net.*;
import java.io.*;

import java.awt.event.*;

public class TCPChatClient {
	
	
	JFrame jf = new JFrame("客户端");
	JPanel jp = new JPanel();
	JTextArea jta = new JTextArea();
	
	
	JTextField jtf = new JTextField(20);
	JButton jbt = new JButton("发送");
	
	JScrollPane jsp = new JScrollPane(jta);
	
	BufferedReader br;
	PrintWriter pw;
	
	public TCPChatClient(){
		jf.add(jsp);
		jta.setEditable(false);//设置为不可编辑
		
		jf.add(jp,"South");
		jp.add(jtf);
		jp.add(jbt);
		
		
		
		jf.setSize(400,400);
		jf.setVisible(true);
		
		jbt.addActionListener(new A());
		
		init();
	}
	
	class A implements ActionListener{
		public void actionPerformed(ActionEvent e){
			//获得文本框中的内容
			String str = jtf.getText().trim();//trim()用于去掉字符串前后的空格
			pw.println(str);
			pw.flush();
			
		}
	}
	public void init(){
		try{
			//1、建立Socket对象，与服务器建立连接
			Socket s = new Socket("192.168.56.1",6666);
			//Socket s = new Socket("127.0.0.1",6666);
			//获得I/O流
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			pw = new PrintWriter(s.getOutputStream());
			
			//启动线程
			Chat c =new Chat();
			c.start();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	class Chat extends Thread{
		public void run(){
			while(true){
				try{
					String line =br.readLine();
					jta.append(line+"\r\n");
				}catch(Exception ex){
					ex.printStackTrace();			
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new TCPChatClient();
	}

}
