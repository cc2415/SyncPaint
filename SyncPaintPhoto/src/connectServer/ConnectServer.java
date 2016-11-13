package connectServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.czc.clientsocket.MainActivity;
import com.czc.clientsocket.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.widget.EditText;

public class ConnectServer extends Thread{
		String sendString = "";
//		byte[] sendString =null;
		EditText et_sendEditText;
		Socket socket=null;
		/**
		 * ���ӷ�����
		 * @param str Ҫ���͵�����
		 * @param activity ������
		 */
		public ConnectServer(String str,Activity activity) {
			sendString = str;
			et_sendEditText=(EditText) activity.findViewById(R.id.et_input);
		}
		@Override
		public void run() {
			Message msg = new Message();
//			bundle = new Bundle();
//			bundle.clear(); 
			try {
				String ip = et_sendEditText.getText().toString().trim();
				socket = new Socket(ip, 6666);
				OutputStream ou = socket.getOutputStream();
				ou.write(sendString.getBytes());// Ҫ���͵����ݣ�ͨ�������
//				ou.write(sendString);// Ҫ���͵����ݣ�ͨ�������
				ou.close();
				socket.close();
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}
