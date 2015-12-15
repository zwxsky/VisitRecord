package visitRecord.adapter;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.List;

import demo.example.zwx.activity.R;
import visitRecord.bean.ImgBean;
import visitRecord.util.Tools;
import visitRecord.util.ViewHolder;
import visitRecord.view.RoundAngleImageView;

/**
 * 圆角图片适配器
 * @author Administrator
 *
 */
public class RoundImgHorizontalAdapter extends AdapterManager<ImgBean> {

	private boolean hasDelete = true;
	
	public void setHasDelete(boolean hasDelete) {
		this.hasDelete = hasDelete;
	}

	public RoundImgHorizontalAdapter(List<ImgBean> list, Context context) {
		super(list, context);
//		this.bu.configDefaultLoadFailedImage(R.drawable.img_default);
//		this.bu.configDefaultLoadingImage(R.drawable.img_default);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Log.i("tag", "position===>" + position);
		if (convertView == null) {
			convertView = this.inflater.inflate(R.layout.round_iamge_item, parent, false);
		}
		int totalWidth = Tools.getScreenWidth(context);
		int width = totalWidth/5;
		int line = Tools.dip2px(context, 10);
		
		RoundAngleImageView imgImg = ViewHolder.get(convertView, R.id.img_img);
		RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) imgImg.getLayoutParams();
		params.width = width-line;
		params.height = width-line;
		ImageView imgDelete = ViewHolder.get(convertView, R.id.img_delete);
		TextView txtValue = ViewHolder.get(convertView, R.id.txt_values);
		final ImgBean bean = getItem(position);
		txtValue.setText(bean.getValue());
//		this.bu.display(imgImg, bean.getPath());
		//TODO 展示图片
		final int index = position;
		imgDelete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (bean.getPath() != null && !bean.getPath().equals("")) {
					RoundImgHorizontalAdapter.this.deleteFromIndex(index);
				}
			}
		});
		if (Tools.StringHasContent(bean.getPath()) && hasDelete) {
			imgDelete.setVisibility(View.VISIBLE);
		} else {
			imgDelete.setVisibility(View.GONE);
		}
		return convertView;
	}
}
