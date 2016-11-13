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
		/* 加载手势库 */
		library = GestureLibraries.fromRawResource(this, R.raw.gestures);
		library.load();
	}

	private final class GestureListener implements OnGesturePerformedListener {
		@Override
		public void onGesturePerformed(GestureOverlayView overlay,
				Gesture gesture) {
			/*
			 * 查找手势库中与用户画的手势库有些相似性的手势集合 并按照相似性度高到低排序，与用户画的图形最相似的手势，放在集合第一个位置
			 */
			ArrayList<Prediction> predictions = library.recognize(gesture);

			if (predictions != null && !predictions.isEmpty()) {
				// 第0个手势是最匹配的手势
				Prediction prediction = predictions.get(0);
				// prediction.score 是相似度,是一个大于0、小于10的双精度数值，1就是10%相似，3就是30%相似度。
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
					Toast.makeText(getApplicationContext(), "手势不能识别", 1).show();
				}
			}
		}
	}

	/**
	 * 销毁 Activity ,并关闭应用
	 */
	@Override
	protected void onDestroy() {
		android.os.Process.killProcess(android.os.Process.myPid());
		super.onDestroy();
	}
}
