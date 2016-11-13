package save;

import global.MyGlobal;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.ContactsContract.CommonDataKinds.Event;
import android.widget.Toast;

public class SaveBitmapToJPG {
	public SaveBitmapToJPG(Context context, Bitmap bitmap, String saveName)
			throws IOException {
		File path = Environment.getExternalStorageDirectory();// ���sd��Ŀ¼,Ҳ���Ǵ򿪵�Ŀ¼
		FileOutputStream fos;
//		Toast.makeText(context, "path=" + path + "", 0).show();

		// д���ļ�
//		fos = context.openFileOutput("sdfsd.txt", Context.MODE_PRIVATE);
//		fos.write("ajlkjdslkfjaskdlflsajf".getBytes());
//		fos.close();

		// ��ȡ�ļ�
//		FileInputStream fis = context.openFileInput("sdfsd.txt");
//		byte[] b = new byte[1024];
//		int length = 0;
//		ByteArrayOutputStream bo = new ByteArrayOutputStream();
//		while ((length = fis.read(b)) != -1) {
//			bo.write(b, 0, length);
//		}
//		byte[] content = bo.toByteArray();
//		System.out.println(new String(content) + "  ��ȡ�ɹ�");

		// ////////�г����е��ļ��к��ļ�////////////////
//		File[] listFiles = path.listFiles();
//		for (int i = 0; i < listFiles.length; i++) {
//			System.out.println(listFiles[i] + "\n");
//		}
		// /////////////////////////////////////

		// File file = new
		// File(path.getPath()+"/Pictures","oneText");//PictureĿ¼�µ�oneText�ļ���
		
		File file = new File(path.getPath() + "/CZC_Pic/");
		if (!file.exists()) {
			file.mkdirs();// �����ļ���
		}
		File Picfile = new File(path.getPath() + "/CZC_Pic/" + saveName
				+ ".jpg");
		if (MyGlobal.Saved) {// �Ѿ������ˣ����в��ϵĸ���
			if (Picfile.exists()) {
				Picfile.delete();// ����ļ����ھ�ɾ��

			}
		}
		
		FileOutputStream out = new FileOutputStream(Picfile);
		bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
		out.flush();
		out.close();
	}
}
