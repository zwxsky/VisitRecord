package visitRecord.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import demo.example.zwx.activity.R;


public class MyPhotoDialog extends Dialog {

	public MyPhotoDialog(Context context, int theme) {
		super(context, theme);
	}

	public MyPhotoDialog(Context context) {
		super(context);
	}

	public static class Builder {
		private Context context;
		private View view;
		private OnClickListener listener;

		public Builder(Context context) {
			this.context = context;
		}

		public Builder setListener(OnClickListener listener) {
			this.listener = listener;
			return this;
		}

		public MyPhotoDialog create() {
			final MyPhotoDialog dialog = new MyPhotoDialog(this.context, R.style.MyDialog);
			dialog.setCancelable(true);
			dialog.setCanceledOnTouchOutside(false);
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			this.view = inflater.inflate(R.layout.my_photo_dialog, null);
			dialog.addContentView(view, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			TextView tvCamera = (TextView) view.findViewById(R.id.tv_camera);
			TextView tvGallery = (TextView) view.findViewById(R.id.tv_gallery);
			TextView tvDelete = (TextView) view.findViewById(R.id.tv_delete);
			TextView tvCannel = (TextView) view.findViewById(R.id.tv_cannel);
			tvCamera.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					if (listener != null) {
						listener.onClick(dialog, 0);
					}
				}
			});
			tvGallery.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					if (listener != null) {
						listener.onClick(dialog, 1);
					}
				}
			});
			tvDelete.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					if (listener != null) {
						listener.onClick(dialog, 2);
					}
				}
			});
			tvCannel.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					dialog.dismiss();
					if (listener != null) {
						listener.onClick(dialog, 3);
					}
				}
			});
			Window win = dialog.getWindow();
			win.setGravity(Gravity.BOTTOM);
			Activity activity = (Activity) context;
			WindowManager windowManager = activity.getWindowManager();
			Display display = windowManager.getDefaultDisplay();
			WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
			lp.width = (int) (display.getWidth()); // 设置宽度
			dialog.getWindow().setAttributes(lp);
			return dialog;
		}

	}

}
