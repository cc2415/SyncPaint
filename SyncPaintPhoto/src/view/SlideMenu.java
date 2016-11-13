package view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.FrameLayout;
import android.widget.Scroller;

public class SlideMenu extends FrameLayout{
	private View menuView,mainView;
	private int menuWidth = 0;
	private Scroller scroller;
	public SlideMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public SlideMenu(Context context) {
		super(context);
		init();
	}
	
	private void init(){
		scroller = new Scroller(getContext());
	}
	
	/**
	 * 褰�1绾х殑瀛恦iew鍏ㄩ儴鍔犺浇瀹岃皟鐢紝鍙互鐢ㄥ垵濮嬪寲瀛恦iew鐨勫紩鐢�
	 * 娉ㄦ剰锛岃繖閲屾棤娉曡幏鍙栧瓙view鐨勫楂�
	 */
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		menuView = getChildAt(0);
		mainView = getChildAt(1);
		menuWidth = menuView.getLayoutParams().width;
	}
	
	/**
	 * widthMeasureSpec鍜宧eightMeasureSpec鏄郴缁熸祴閲廠lideMenu鏃朵紶鍏ョ殑鍙傛暟锛�
	 * 杩�2涓弬鏁版祴閲忓嚭鐨勫楂樿兘璁㏒lideMenu鍏呮弧绐椾綋锛屽叾瀹炴槸姝ｅソ绛変簬灞忓箷瀹介珮
	 */
//	@Override
//	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//		
//		int measureSpec = MeasureSpec.makeMeasureSpec(menuWidth, MeasureSpec.EXACTLY);
//		
//		//娴嬮噺鎵�鏈夊瓙view鐨勫楂�
//		//閫氳繃getLayoutParams鏂规硶鍙互鑾峰彇鍒板竷灞�鏂囦欢涓寚瀹氬楂�
//		menuView.measure(measureSpec, heightMeasureSpec);
//		//鐩存帴浣跨敤SlideMenu鐨勬祴閲忓弬鏁帮紝鍥犱负瀹冪殑瀹介珮閮芥槸鍏呮弧鐖剁獥浣�
//		mainView.measure(widthMeasureSpec, heightMeasureSpec);
//		
//	}
	///////////////////////////text////////////////////
//	@Override
//	public boolean onInterceptTouchEvent(MotionEvent ev) {
//		switch (ev.getAction()) {
//		case MotionEvent.ACTION_DOWN:
//			downX = (int) ev.getX();
//			break;
//		case MotionEvent.ACTION_MOVE:
//			int deltaX = (int) ( ev.getX()- downX);
//			
//			if(Math.abs(deltaX)>8){
//				return true;
//			}
//			break;
//		}
//		return super.onInterceptTouchEvent(ev);
//	}
	/////////////////////////////////////////////////////////////////

	/**
	 * l: 褰撳墠瀛恦iew鐨勫乏杈瑰湪鐖秜iew鐨勫潗鏍囩郴涓殑x鍧愭爣
	 * t: 褰撳墠瀛恦iew鐨勯《杈瑰湪鐖秜iew鐨勫潗鏍囩郴涓殑y鍧愭爣
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
//		Log.e("MAIN", "L: "+l+"   t: "+t  +"  r: "+r  + "   b: "+b);
		menuView.layout(-menuWidth, 0, 0, menuView.getMeasuredHeight());
		mainView.layout(0, 0, r, b);
	}
	
	private int downX;
	
	///////////////////////////text//////////////////////
//	@Override
//	public boolean onTouchEvent(MotionEvent event) {
//		switch (event.getAction()) {
//		case MotionEvent.ACTION_DOWN:
//			downX = (int) event.getX();
//			break;
//		case MotionEvent.ACTION_MOVE:
//			int moveX = (int) event.getX();
//			int deltaX = (int) ( moveX- downX);
//			
//			int newScrollX = getScrollX() - deltaX;
//			
//			if(newScrollX<-menuWidth)newScrollX = -menuWidth;
//			if(newScrollX>0)newScrollX = 0;
//			
//			Log.e("Main", "scrollX: "+getScrollX());
//			scrollTo(newScrollX, 0);
//			downX = moveX;
//			break;
//		case MotionEvent.ACTION_UP:
//			if(getScrollX()>-menuWidth/2){
////				//鍏抽棴鑿滃崟
//				closeMenu();
//			}else {
//				//鎵撳紑鑿滃崟
//				openMenu();
//			}
//			break;
//		}
//		return true;
//	}
	////////////////////////////////////////////////////
	
	private void closeMenu(){
		scroller.startScroll(getScrollX(), 0, 0-getScrollX(), 0, 400);
		invalidate();
	}
	
	private void openMenu(){
		scroller.startScroll(getScrollX(), 0, -menuWidth-getScrollX(), 0, 400);
		invalidate();
	}
	
	/**
	 * Scroller涓嶄富鍔ㄥ幓璋冪敤杩欎釜鏂规硶
	 * 鑰宨nvalidate()鍙互鎺夎繖涓柟娉�
	 * invalidate->draw->computeScroll
	 */
	@Override
	public void computeScroll() {
		super.computeScroll();
		if(scroller.computeScrollOffset()){//杩斿洖true,琛ㄧず鍔ㄧ敾娌＄粨鏉�
			scrollTo(scroller.getCurrX(), 0);
			invalidate();
		}
	}

	/**
	 * 鍒囨崲鑿滃崟鐨勫紑鍜屽叧
	 */
	public void switchMenu() {
		if(getScrollX()==0){
			//闇�瑕佹墦寮�
			openMenu();
		}else {
			//闇�瑕佸叧闂�
			closeMenu();
		}
	}
	

}
