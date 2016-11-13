package com.czc.clientsocket;

import java.io.IOException;
import java.security.spec.MGF1ParameterSpec;
import java.util.ArrayList;

import global.MyGlobal;

import connectServer.ConnectServer;

import save.SaveBitmapToJPG;
import tool.MyPopuWindow;
import tool.ShowDialog;
import view.MatrixImageView;
import view.SlideMenu;

import Point.DrawPointPath;
import action.BackDraw;
import action.Recovery;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Bitmap.Config;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Switch;
import android.widget.Toast;
//need to open the Java Sync
public class MainActivity extends Activity implements
		android.view.View.OnClickListener {

	DrawPointPath dpp = new DrawPointPath();
	int BACK=0;
	public EditText et_sendEditText;
	public Button bt_clean, bt1,bt_te;
	public static MatrixImageView iv;
	public Bitmap baseBitmap;
	public Canvas canvas;
	private ImageButton ib_menu;
	public SlideMenu slideMenu;
	public static Button bt_paincolor;
	public static Activity mActivity;
	public Context context;
	public Button bt_eraser, bt_back, bt_recover, bt_move;
	public Switch sw_smart;
	public Paint pen = new Paint();
	public int width = 0;
	public int height = 0;
	public int type = 0;
	public static int backNumber = 2;

	public static int TYPE_CLEAN;// �����־
	public static boolean isEraser = false, back = false;
	public Bitmap afterCancelBitmap;

	// ��Ϣ���л���
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				break;
			case 2:
				break;
			}
		};
	};
	private ShowDialog showDialog;
	public Bitmap decodeResource=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		mActivity = this;
		initWidget();
//		new ConnectServer(MyGlobal.SCREE+MyGlobal.SCREE_WIDTH+","+MyGlobal.SCREE_HEIGHT, mActivity).start();
		
		
		// ��ʼ������
		initDrawer(baseBitmap, canvas, pen, "#ff0000", 1);
//		initDrawer(decodeResource, canvas, pen, "#ff0000", 1);
		iv.setBackgroundColor(Color.parseColor(MyGlobal.CANVAS_BACKGROUND));
		///////////////////
//		iv.setImageBitmap(decodeResource);
		/////////////////////
		if (!MyGlobal.Saved) {
			showDialog = new ShowDialog(this, this);
		}
	}

	/**
	 * ��ʼ���ؼ�
	 */
	public void initWidget() {
		context = this;
		slideMenu = (SlideMenu) findViewById(R.id.sm);
		et_sendEditText = (EditText) findViewById(R.id.et_input);
		iv = (MatrixImageView) findViewById(R.id.iv_paint);
		ib_menu = (ImageButton) findViewById(R.id.ib_menu);
		bt_clean = (Button) findViewById(R.id.bt_clean);
		bt_paincolor = (Button) findViewById(R.id.bt_paincolor);
		bt_eraser = (Button) findViewById(R.id.cb_eraser);
		bt_back = (Button) findViewById(R.id.bt_back);
		bt_recover = (Button) findViewById(R.id.bt_recovery);
		bt1 = (Button) findViewById(R.id.bt1);
		bt_move = (Button) findViewById(R.id.bt_move);
		sw_smart = (Switch) findViewById(R.id.sw_smart);
		bt_te=(Button) findViewById(R.id.bt_te);
		
		bt_te.setOnClickListener(this);
		sw_smart.setOnClickListener(this);
		bt_move.setOnClickListener(this);
		ib_menu.setOnClickListener(this);
		bt1.setOnClickListener(this);
		bt_eraser.setOnClickListener(this);
		bt_back.setOnClickListener(this);
		bt_recover.setOnClickListener(this);
		bt_paincolor.setOnClickListener(this);
		bt_clean.setOnClickListener(this);

		// �����Ļ���
		WindowManager wm = this.getWindowManager();
		MyGlobal.SCREE_WIDTH = wm.getDefaultDisplay().getWidth();
		MyGlobal.SCREE_HEIGHT = wm.getDefaultDisplay().getHeight();

		System.out.println("gao: "+MyGlobal.SCREE_HEIGHT + "=====kuang"
				+ MyGlobal.SCREE_WIDTH);

		baseBitmap = Bitmap.createBitmap(MyGlobal.SCREE_WIDTH,
				MyGlobal.SCREE_HEIGHT, Config.ARGB_8888);
		canvas = new Canvas(baseBitmap);
		canvas.drawColor(Color.parseColor(MyGlobal.CANVAS_BACKGROUND));// ���û���������ɫ
		
		RelativeLayout.LayoutParams rlp=(LayoutParams) iv.getLayoutParams();
		rlp.width=MyGlobal.SCREE_WIDTH;
		rlp.height=MyGlobal.SCREE_HEIGHT;
		iv.setLayoutParams(rlp);
		/////////////////////
//		TYPE_CLEAN = 1; 
		///////////////////////
		
		
		ViewTreeObserver viewTreeObserver = iv.getViewTreeObserver();
		viewTreeObserver
				.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

					@Override
					public void onGlobalLayout() {
						iv.getViewTreeObserver().removeGlobalOnLayoutListener(
								this);
						height = iv.getHeight();
						width = iv.getWidth();
						System.out.println("imageview�Ŀ�   " + iv.getWidth()
								+ "---��--" + iv.getHeight());
					}
				});
	}

	/**
	 * ��ʼ������
	 * 
	 * @param baseBitmap
	 *            λͼ
	 * @param canvas
	 *            ����
	 * @param width
	 *            ������
	 * @param height
	 *            ������
	 * @param pen
	 *            ����
	 * @param color
	 *            ������ɫ
	 * @param size
	 *            ���ʴ�С
	 */
	public void initDrawer(Bitmap baseBitmap, Canvas canvas,
			Paint pen, String color, int size) {
		pen.setStrokeWidth(size);
		pen.setColor(Color.parseColor(color));
		pen.setDither(true);
		pen.setAntiAlias(true);
		pen.setStyle(Paint.Style.STROKE);
		pen.setStrokeCap(Paint.Cap.ROUND);// ���ñ�ͷ
		pen.setStrokeJoin(Paint.Join.ROUND);
		pen.setDither(true);
		canvas.drawBitmap(baseBitmap, new Matrix(), pen);
	}

	/**
	 * ����
	 */
	public void backDraw(){
		int backnumber = dpp.dpointPath.size() - 1;
		BackDraw ba = new BackDraw();
		Bitmap b = ba
				.getBitmap(pen, baseBitmap, dpp.dpointPath, backnumber);
		iv.setImageBitmap(b);
		afterCancelBitmap = null;
		afterCancelBitmap = b;
		if (dpp.dpointPath.size() == 0) {
			bt_back.setEnabled(false);
			Bitmap bitmap = Bitmap.createBitmap(MyGlobal.SCREE_WIDTH,
					MyGlobal.SCREE_HEIGHT, Config.ARGB_8888);
			afterCancelBitmap = Bitmap.createBitmap(bitmap);
		}
		back = true;
		new ConnectServer(MyGlobal.REVOKE, mActivity).start();//�÷�����Ҳִ�г���
		bt_recover.setEnabled(true);
	}
	@SuppressLint("NewApi")
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_clean:
			baseBitmap = null;
			baseBitmap = Bitmap.createBitmap(MyGlobal.SCREE_WIDTH,
					MyGlobal.SCREE_HEIGHT, Config.ARGB_8888);
			canvas = new Canvas(baseBitmap);
			canvas.drawColor(Color.parseColor(MyGlobal.CANVAS_BACKGROUND));
			initDrawer(baseBitmap, canvas, pen, "#ff0000", 1);
			canvas.drawBitmap(baseBitmap, new Matrix(), pen);
			iv.setImageBitmap(baseBitmap);
			afterCancelBitmap = baseBitmap;

			dpp.dpointPath=new ArrayList<ArrayList<String>>(); 

			bt_back.setEnabled(false);
			bt_recover.setEnabled(false);
			MyGlobal.RECOVERY=new ArrayList<ArrayList<String>>();
			
			new ConnectServer(MyGlobal.CLEANIMAGER, mActivity).start();
			backNumber = 2;
			TYPE_CLEAN = 1;

			try {
				if (MyGlobal.Saved) {
					SaveBitmapToJPG s=new SaveBitmapToJPG(context, afterCancelBitmap, MyGlobal.SAVE_NAME);
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Message msg = new Message();
			msg.what = 1;
			handler.sendMessage(msg);
			break;

		case R.id.bt_paincolor:
			new MyPopuWindow(context, bt_paincolor, 10, 10);// $1������
															// $2�ؼ�
			break;
		case R.id.cb_eraser://
			// slideMenu.switchMenu();
			if (isEraser) {
				isEraser = false;
			} else {
				isEraser = true;
			}
			break;
		case R.id.bt_back:
			backDraw();
			try {
				if (MyGlobal.Saved) {
					SaveBitmapToJPG saveBitmapToJPG=new SaveBitmapToJPG(context, afterCancelBitmap, MyGlobal.SAVE_NAME);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.bt_recovery:
			int recoveryNumber = MyGlobal.RECOVERY.size() - 1;
//			System.out.println(recoveryNumber + "");
			Recovery r = new Recovery();
			Bitmap rBitmap = r.getBitmap(pen, baseBitmap, dpp.dpointPath,
					recoveryNumber);
			iv.setImageBitmap(rBitmap);
			afterCancelBitmap = rBitmap;
			bt_back.setEnabled(true);
			if (recoveryNumber < 1) {
				bt_recover.setEnabled(false);
			}
			new ConnectServer(MyGlobal.RECOVERY_draw, mActivity).start();
			try {
				if (MyGlobal.Saved) {
					SaveBitmapToJPG saveBitmapToJPG=new SaveBitmapToJPG(context, afterCancelBitmap, MyGlobal.SAVE_NAME);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.bt1:
			showDialog=new ShowDialog(this, this);
			break;
		case R.id.ib_menu:
			slideMenu.switchMenu();
			break;
		case R.id.bt_move:
			startActivity(new Intent(MainActivity.this, Gov.class));
			if (MyGlobal.TYPE_MOVE) {
				MyGlobal.TYPE_MOVE = false;
			} else {
				MyGlobal.TYPE_MOVE = true;
			}
			System.out.println(MyGlobal.TYPE_MOVE + "");
			break;

		case R.id.sw_smart:
			if (sw_smart.isChecked()) {
				Toast.makeText(this, "����ģʽ����", 0).show();
				MyGlobal.TYPE_SMART = true;
			} else {
				Toast.makeText(this, "����ģʽ�ر�", 0).show();
				MyGlobal.TYPE_SMART = false;
			}
			break;
		case R.id.bt_te:
			Toast.makeText(this, "fdsf", 0).show();
			//111111111111
//			iv.setBackgroundResource(R.drawable.text);
			decodeResource=BitmapFactory.decodeResource(getResources(), R.drawable.text);
//			canvas.drawBitmap(decodeResource, new Matrix(), pen);
//			iv.setImageBitmap(decodeResource);
			iv.setBackgroundResource(R.drawable.text);
			MyGlobal.CHOSE_PIC=decodeResource;
			MyGlobal.te=true;
			break;
		}
		

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == event.KEYCODE_MENU) {//menu��
			slideMenu.switchMenu();
		}
		if(keyCode==event.KEYCODE_BACK){//���˽�
			BACK++;
			if (BACK>1) {
				return super.onKeyDown(keyCode, event);
			}else {
				Toast.makeText(context, "�ٰ�һ���˳�", 0).show();
				return false;
			}
		}
		if (keyCode==event.KEYCODE_VOLUME_DOWN) {//����
			backDraw();
			try {
				SaveBitmapToJPG sa=new SaveBitmapToJPG(this, afterCancelBitmap, MyGlobal.SAVE_NAME);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return super.onKeyDown(keyCode, event);
	}
	
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		MyGlobal.SAVE_NAME="";
		MyGlobal.Saved=false;
	}
}// end MainActivityClass
