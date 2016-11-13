package global;

import android.R.integer;
import android.graphics.Bitmap;
import android.graphics.Point;

import java.util.ArrayList;

public class MyGlobal {
	/**
	 * 撤销标志
	 */
	public static String REVOKE="aa";
	/**
	 * 恢复撤销标志
	 */
	public static String RECOVERY_draw="ab";
	/**
	 * 清空图像标志
	 */
	public static String CLEANIMAGER="ac";
	/**
	 * 开始绘画标志
	 */
	public static String START_DRAW="ad";
	/**
	 * 普通模式松开标志
	 */
	public static String HAND_UP="ae";
	/**
	 * 智能模式松开标志
	 */
	public static String SMART_HAND_UP="af"; 
	/**
	 * 屏幕大小标志
	 */
	public static String SCREE="ag";
	
	/**
	 * 存放撤销的
	 */
	public static ArrayList<ArrayList<String>> RECOVERY=new ArrayList<ArrayList<String>>();
	/**
	 * 是否超过8个
	 */
	public static boolean OUT_OF_EIGHT=false;
	//颜色rgb
	public static int r=0;
	public static int g=0;
	public static int b=0;
	/**
	 * rgb转为string颜色代码
	 */
	public static String color="#000000";
	/**
	 * 笔大小
	 */
	public static int PEN_SIZE=1;
	/**
	 * 画板背景颜色
	 */
	public static String CANVAS_BACKGROUND="#ffffff";
	/**
	 * 屏幕宽
	 */
	public static int SCREE_WIDTH=720;
	/**
	 * 屏幕高
	 */
	public static int SCREE_HEIGHT=1184;
	public static boolean TYPE_MOVE=false;
	/**
	 * 是否开启智能模式
	 */
	public static boolean TYPE_SMART=false;
	
	public static float left;
	public static float top;
	public static float right;
	public static float bottom;
	
	/**
	 * 用来临时存储路径，分辨大小
	 */
	public static ArrayList<ArrayList<String>> SMART_TEMP=new ArrayList<ArrayList<String>>();
	/**
	 * 智能模式下矩形	
	 */
	public static boolean IS_RECTANGLE=false;
	/**
	 * 智能模式下圆形
	 */
	public static boolean IS_CIRCLE=false;
	
	public static Point Max=new Point();
	/**
	 * 保存的文件名
	 */
	public static String SAVE_NAME;
	/**
	 * 是否保存过
	 */
	public static boolean Saved=false;
	
	public static String A_BROKE;
	public static String B_BROKE;
	public static String C_BROKE;
	public static String D_BROKE;
	
	public static Bitmap CHOSE_PIC=null;
	public static boolean te=false;
}
