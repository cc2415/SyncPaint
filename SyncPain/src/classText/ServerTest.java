package classText;

import java.net.*;
import java.io.*;

public class ServerTest {

	public static void main(String[] args) {
		try{
			//1\����ServerSocket����
			ServerSocket ss = new ServerSocket(6666);
			
			//2��ʹ��accept�����ȴ��ͻ��˵�����,ֱ���пͻ�������
			Socket s = ss.accept();
			
			//3����������
			OutputStream os = s.getOutputStream();
			
			PrintWriter pw = new PrintWriter(os);
			pw.println("hello world");
			pw.flush();
			
			
			pw.close();
			os.close();
			s.close();
			ss.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
