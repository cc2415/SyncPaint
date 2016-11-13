package tool;

import java.io.File;

import global.MyGlobal;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import com.czc.clientsocket.MainActivity;
import com.czc.clientsocket.R;

public class ShowDialog {
	AlertDialog alert = null;

	public ShowDialog(final Context contexts, final MainActivity act) {
		// dialog//
		AlertDialog.Builder builder = null;
		final View view_curson;
		builder = new AlertDialog.Builder(contexts);
		LayoutInflater inflater = act.getLayoutInflater();
		view_curson = inflater.inflate(R.layout.dialog, null, false);
		builder.setView(view_curson);
		builder.setCancelable(false);
		alert = builder.create();
		alert.show();

		view_curson.findViewById(R.id.bt_save).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						File path = Environment.getExternalStorageDirectory();
						File Picfile = new File(path.getPath() + "/CZC_Pic/"
								+ MyGlobal.SAVE_NAME + ".jpg");
						if (Picfile.exists()) {
							Toast.makeText(contexts, "文件已经存在", 1).show();
							ShowDialog s = new ShowDialog(contexts, act);
						} else {
							Toast.makeText(contexts, "保存成功", 0).show();
						}
						MyGlobal.Saved = true;
						alert.dismiss();
//						act.bt_clean.performClick();
					}
				});

		view_curson.findViewById(R.id.bt_cancel).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						alert.dismiss();
//						act.bt_clean.performClick();
					}
				});

		final EditText editText = (EditText) view_curson
				.findViewById(R.id.et_filename);
		editText.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				MyGlobal.SAVE_NAME = editText.getText().toString().trim();
			}
		});

	}

	public void dismis() {
		alert.dismiss();
	}
}
