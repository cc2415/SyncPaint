package classText;

import java.net.*;
import java.io.*;

public class ClientTest {

	public static void main(String[] args) {
		try {
			//1、建立Socket对象，建立与服务器端的连接
			Socket s = new Socket("127.0.0.1",6666);
			
			//2、获得输入流
			InputStream is = s.getInputStream();
			BufferedReader br =new BufferedReader(new InputStreamReader(is));
			//等待，直到服务器端发送数据过来
			String line = br.readLine();
			
			System.out.println(line);
			//关闭资源
			br.close();
			is.close();
			s.close();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
