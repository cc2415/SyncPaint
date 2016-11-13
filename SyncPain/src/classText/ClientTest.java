package classText;

import java.net.*;
import java.io.*;

public class ClientTest {

	public static void main(String[] args) {
		try {
			//1������Socket���󣬽�����������˵�����
			Socket s = new Socket("127.0.0.1",6666);
			
			//2�����������
			InputStream is = s.getInputStream();
			BufferedReader br =new BufferedReader(new InputStreamReader(is));
			//�ȴ���ֱ���������˷������ݹ���
			String line = br.readLine();
			
			System.out.println(line);
			//�ر���Դ
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
