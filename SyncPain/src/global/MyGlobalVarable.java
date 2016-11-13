package global;

import java.util.ArrayList;

import action.BackDraw;

public class MyGlobalVarable {
	/**
	 * 撤销标志
	 */
	public static String REVOKE="aa";
	/**
	 * 恢复撤销标志
	 */
	public static String RECOVERY="ab";
	/**
	 * 清空图像标志
	 */
	public static String CLEANIMAGER="ac";
	/**
	 * 绘图开始标志
	 */
	public static String START_DRAW="ad";
	/**
	 * 普通模式手指松开标志
	 */
	public static String HAND_UP="ae";
	/**
	 * 智能模式手指松开
	 */
	public static String SMART_HAND_UP="af";
	/**
	 * 屏幕大小标志
	 */
	public static String SCREEN="ag";
	
	
	/**
	 * 标记后退了得位置
	 */
	public static int BACK_NUMBER=0;
	/**
	 * 是否第一次后退
	 */
	public static boolean FIRST_BACK=false;
	/**
	 * 记录撤销掉的笔画
	 */
	public static ArrayList<ArrayList<String>> RECOVERY_ARRAYLIST=new ArrayList<ArrayList<String>>();
}
