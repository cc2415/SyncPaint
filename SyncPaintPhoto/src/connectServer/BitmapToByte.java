package connectServer;

import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapToByte {
	public byte[] getByteFromBitmap(Bitmap bm){
//		byte[] by = null;
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);//bitmapת��Ϊbyte
		System.out.println("bitmapתΪbyte�ǣ�=="+baos.toByteArray());
		return baos.toByteArray();
	}
}
