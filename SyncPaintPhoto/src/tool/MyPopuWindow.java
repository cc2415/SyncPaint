package tool;



import util.RGBToString;
import global.MyGlobal;

import com.czc.clientsocket.MainActivity;
import com.czc.clientsocket.R;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MyPopuWindow {
	final PopupWindow popupWindow;
	Context mContext;
	MainActivity act;
	TextView t;
	EditText ed1,ed2,ed3,ed4;
	Button bt_colorButton;
	RGBToString rgbToString=new RGBToString();

	/**
	 * 
	 * @param context
	 * @param view
	 *            显示的控件下
	 * @param x
	 * @param y
	 */
	public MyPopuWindow(Context context, View view, int x, int y) {
		mContext = context;
		act=(MainActivity) context;
		bt_colorButton=(Button) act.findViewById(R.id.bt_paincolor);
		
		View myContentView = LayoutInflater.from(mContext).inflate(
				R.layout.chose, null, false);
		t=(TextView) myContentView.findViewById(R.id.textView1);
		ed1=(EditText) myContentView.findViewById(R.id.et1);
		ed2=(EditText) myContentView.findViewById(R.id.et2);
		ed3=(EditText) myContentView.findViewById(R.id.et3);
		ed4=(EditText) myContentView.findViewById(R.id.et4);
		final SeekBar se1 = (SeekBar) myContentView.findViewById(R.id.seekBar1);
		final SeekBar se2 = (SeekBar) myContentView.findViewById(R.id.seekBar2);
		final SeekBar se3 = (SeekBar) myContentView.findViewById(R.id.seekBar3);
		final SeekBar se4 = (SeekBar) myContentView.findViewById(R.id.seekBar4);
		se1.setProgress(MyGlobal.r);
		se2.setProgress(MyGlobal.g);
		se3.setProgress(MyGlobal.b);
		se4.setProgress(MyGlobal.PEN_SIZE);
		
		ed1.setText(MyGlobal.r+"");
		ed2.setText(MyGlobal.g+"");
		ed3.setText(MyGlobal.b+"");
		ed4.setText(MyGlobal.PEN_SIZE+"");
		popupWindow = new PopupWindow(myContentView, 500,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		// popupWindow.setAnimationStyle(animationStyle)设置动画
		// 点击外部消失
		popupWindow.setOutsideTouchable(true);
		popupWindow.setTouchable(true);
		//popuWindow默认是不获取焦点的
		popupWindow.setFocusable(true);
		popupWindow.setTouchInterceptor(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				return false;// 如果为true则touch事件被拦截，拦截后，点击外部无法dismiss
			}
		});
		// 一定要设置一个背景才有效
		int a = Color.parseColor("#d2d2d2");
		popupWindow.setBackgroundDrawable(new ColorDrawable(a));
		popupWindow.showAsDropDown(view, x, y);

		se1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {

//				System.out.println("我是1：：" + progress);
				MyGlobal.r=progress;
				ed1.setText(progress+"");
				MyGlobal.color=rgbToString.getStringFromRGB(MyGlobal.r, MyGlobal.g, MyGlobal.b);
				bt_colorButton.setBackgroundColor(Color.parseColor(MyGlobal.color));
				t.setTextColor(Color.parseColor(MyGlobal.color));
				
			}
		});
		se2.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {

//				System.out.println("我是2：：" + progress);
				MyGlobal.g=progress;
				ed2.setText(progress+"");
				MyGlobal.color=rgbToString.getStringFromRGB(MyGlobal.r, MyGlobal.g, MyGlobal.b);
				bt_colorButton.setBackgroundColor(Color.parseColor(MyGlobal.color));
				t.setTextColor(Color.parseColor(MyGlobal.color));
			}
		});
		se3.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {

//				System.out.println("我是3：：" + progress);
				MyGlobal.b=progress;
				ed3.setText(progress+"");
				MyGlobal.color=rgbToString.getStringFromRGB(MyGlobal.r, MyGlobal.g, MyGlobal.b);
				bt_colorButton.setBackgroundColor(Color.parseColor(MyGlobal.color));
				t.setTextColor(Color.parseColor(MyGlobal.color));
			}
		});
		
		se4.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				MyGlobal.PEN_SIZE=progress;
				ed4.setText(progress+"");
				t.setTextSize(MyGlobal.PEN_SIZE);
				
			}
		});
		
		ed1.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				MyGlobal.r=Integer.parseInt(ed1.getText().toString());
//				System.out.println(MyGobal.r);
				se1.setProgress(MyGlobal.r);
				MyGlobal.color=rgbToString.getStringFromRGB(MyGlobal.r, MyGlobal.g, MyGlobal.b);
				bt_colorButton.setBackgroundColor(Color.parseColor(MyGlobal.color));
			}
		});
		ed2.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				MyGlobal.g=Integer.parseInt(ed2.getText().toString());
//				System.out.println(MyGobal.g);
				se2.setProgress(MyGlobal.g);
				MyGlobal.color=rgbToString.getStringFromRGB(MyGlobal.r, MyGlobal.g, MyGlobal.b);
				bt_colorButton.setBackgroundColor(Color.parseColor(MyGlobal.color));
			}
		});
		ed3.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				MyGlobal.b=Integer.parseInt(ed3.getText().toString());
				se3.setProgress(MyGlobal.b);
				MyGlobal.color=rgbToString.getStringFromRGB(MyGlobal.r, MyGlobal.g, MyGlobal.b);
				bt_colorButton.setBackgroundColor(Color.parseColor(MyGlobal.color));
			}
		});
		ed4.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				MyGlobal.PEN_SIZE=Integer.parseInt(ed4.getText().toString());
				se4.setProgress(MyGlobal.PEN_SIZE);
				t.setTextSize(MyGlobal.PEN_SIZE);
			}
		});
	}
}
