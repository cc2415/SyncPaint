package global;

import android.R.integer;
import android.graphics.Bitmap;
import android.graphics.Point;

import java.util.ArrayList;

public class MyGlobal {
	/**
	 * ������־
	 */
	public static String REVOKE="aa";
	/**
	 * �ָ�������־
	 */
	public static String RECOVERY_draw="ab";
	/**
	 * ���ͼ���־
	 */
	public static String CLEANIMAGER="ac";
	/**
	 * ��ʼ�滭��־
	 */
	public static String START_DRAW="ad";
	/**
	 * ��ͨģʽ�ɿ���־
	 */
	public static String HAND_UP="ae";
	/**
	 * ����ģʽ�ɿ���־
	 */
	public static String SMART_HAND_UP="af"; 
	/**
	 * ��Ļ��С��־
	 */
	public static String SCREE="ag";
	
	/**
	 * ��ų�����
	 */
	public static ArrayList<ArrayList<String>> RECOVERY=new ArrayList<ArrayList<String>>();
	/**
	 * �Ƿ񳬹�8��
	 */
	public static boolean OUT_OF_EIGHT=false;
	//��ɫrgb
	public static int r=0;
	public static int g=0;
	public static int b=0;
	/**
	 * rgbתΪstring��ɫ����
	 */
	public static String color="#000000";
	/**
	 * �ʴ�С
	 */
	public static int PEN_SIZE=1;
	/**
	 * ���屳����ɫ
	 */
	public static String CANVAS_BACKGROUND="#ffffff";
	/**
	 * ��Ļ��
	 */
	public static int SCREE_WIDTH=720;
	/**
	 * ��Ļ��
	 */
	public static int SCREE_HEIGHT=1184;
	public static boolean TYPE_MOVE=false;
	/**
	 * �Ƿ�������ģʽ
	 */
	public static boolean TYPE_SMART=false;
	
	public static float left;
	public static float top;
	public static float right;
	public static float bottom;
	
	/**
	 * ������ʱ�洢·�����ֱ��С
	 */
	public static ArrayList<ArrayList<String>> SMART_TEMP=new ArrayList<ArrayList<String>>();
	/**
	 * ����ģʽ�¾���	
	 */
	public static boolean IS_RECTANGLE=false;
	/**
	 * ����ģʽ��Բ��
	 */
	public static boolean IS_CIRCLE=false;
	
	public static Point Max=new Point();
	/**
	 * ������ļ���
	 */
	public static String SAVE_NAME;
	/**
	 * �Ƿ񱣴��
	 */
	public static boolean Saved=false;
	
	public static String A_BROKE;
	public static String B_BROKE;
	public static String C_BROKE;
	public static String D_BROKE;
	
	public static Bitmap CHOSE_PIC=null;
	public static boolean te=false;
}
