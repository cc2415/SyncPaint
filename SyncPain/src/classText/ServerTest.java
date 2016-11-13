package classText;

import java.net.*;
import java.io.*;

public class ServerTest {

	public static void main(String[] args) {
		try{
			//1\创建ServerSocket对象
			ServerSocket ss = new ServerSocket(6666);
			
			//2、使用accept方法等待客户端的链接,直到有客户端请求
			Socket s = ss.accept();
			
			//3、获得输出流
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
