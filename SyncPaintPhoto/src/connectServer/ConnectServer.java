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
		 * 连接服务器
		 * @param str 要发送的数据
		 * @param activity 上下文
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
				ou.write(sendString.getBytes());// 要发送的数据，通过输出流
//				ou.write(sendString);// 要发送的数据，通过输出流
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
