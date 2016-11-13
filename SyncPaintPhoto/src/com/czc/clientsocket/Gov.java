package com.czc.clientsocket;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

public class Gov extends Activity {

	private GestureLibrary library;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text);

		GestureOverlayView gestureView = (GestureOverlayView) findViewById(R.id.gol_gest);
		gestureView.addOnGesturePerformedListener(new GestureListener());
		/* �������ƿ� */
		library = GestureLibraries.fromRawResource(this, R.raw.gestures);
		library.load();
	}

	private final class GestureListener implements OnGesturePerformedListener {
		@Override
		public void onGesturePerformed(GestureOverlayView overlay,
				Gesture gesture) {
			/*
			 * �������ƿ������û��������ƿ���Щ�����Ե����Ƽ��� �����������Զȸߵ����������û�����ͼ�������Ƶ����ƣ����ڼ��ϵ�һ��λ��
			 */
			ArrayList<Prediction> predictions = library.recognize(gesture);

			if (predictions != null && !predictions.isEmpty()) {
				// ��0����������ƥ�������
				Prediction prediction = predictions.get(0);
				// prediction.score �����ƶ�,��һ������0��С��10��˫������ֵ��1����10%���ƣ�3����30%���ƶȡ�
				if (prediction.score > 1) {
					if ("close".equals(prediction.name)) {
						System.out.println(prediction.name+"");
						finish();
					} else if ("wjh".equals(prediction.name)) {
						Uri uri = Uri.parse("tel:10086");
						Intent intent = new Intent(Intent.ACTION_CALL, uri);
						startActivity(intent);
					}
				} else {
					Toast.makeText(getApplicationContext(), "���Ʋ���ʶ��", 1).show();
				}
			}
		}
	}

	/**
	 * ���� Activity ,���ر�Ӧ��
	 */
	@Override
	protected void onDestroy() {
		android.os.Process.killProcess(android.os.Process.myPid());
		super.onDestroy();
	}
}
