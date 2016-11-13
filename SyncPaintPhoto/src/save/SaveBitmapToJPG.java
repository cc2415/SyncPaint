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
		File path = Environment.getExternalStorageDirectory();// 获得sd卡目录,也就是打开的目录
		FileOutputStream fos;
//		Toast.makeText(context, "path=" + path + "", 0).show();

		// 写入文件
//		fos = context.openFileOutput("sdfsd.txt", Context.MODE_PRIVATE);
//		fos.write("ajlkjdslkfjaskdlflsajf".getBytes());
//		fos.close();

		// 读取文件
//		FileInputStream fis = context.openFileInput("sdfsd.txt");
//		byte[] b = new byte[1024];
//		int length = 0;
//		ByteArrayOutputStream bo = new ByteArrayOutputStream();
//		while ((length = fis.read(b)) != -1) {
//			bo.write(b, 0, length);
//		}
//		byte[] content = bo.toByteArray();
//		System.out.println(new String(content) + "  读取成功");

		// ////////列出所有的文件夹和文件////////////////
//		File[] listFiles = path.listFiles();
//		for (int i = 0; i < listFiles.length; i++) {
//			System.out.println(listFiles[i] + "\n");
//		}
		// /////////////////////////////////////

		// File file = new
		// File(path.getPath()+"/Pictures","oneText");//Picture目录下的oneText文件夹
		
		File file = new File(path.getPath() + "/CZC_Pic/");
		if (!file.exists()) {
			file.mkdirs();// 创建文件夹
		}
		File Picfile = new File(path.getPath() + "/CZC_Pic/" + saveName
				+ ".jpg");
		if (MyGlobal.Saved) {// 已经保存了，进行不断的更新
			if (Picfile.exists()) {
				Picfile.delete();// 如果文件存在就删除

			}
		}
		
		FileOutputStream out = new FileOutputStream(Picfile);
		bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
		out.flush();
		out.close();
	}
}
