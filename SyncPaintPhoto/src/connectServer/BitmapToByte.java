package connectServer;

import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapToByte {
	public byte[] getByteFromBitmap(Bitmap bm){
//		byte[] by = null;
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);//bitmap转换为byte
		System.out.println("bitmap转为byte是：=="+baos.toByteArray());
		return baos.toByteArray();
	}
}
