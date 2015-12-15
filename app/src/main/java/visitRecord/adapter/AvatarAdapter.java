package visitRecord.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import demo.example.zwx.activity.R;
import visitRecord.model.RecordModel;
import visitRecord.model.VisitorModel;
import visitRecord.util.DateUtils;
import visitRecord.util.ViewHolder;

/**
 * Created by luo on 15-12-15.
 */
public class AvatarAdapter extends AdapterManager<VisitorModel> {

    public AvatarAdapter(List<VisitorModel> list, Context context) {
        super(list, context);
    }


    @Override
    public VisitorModel getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder viewHolder = new ViewHolder();

        //动态加载 item
        convertView = this.inflater.inflate(R.layout.avatar, null);

        TextView avatar= ViewHolder.get(convertView, R.id.avatar);

//        convertView.setTag(viewHolder);


        System.out.println("首字："+list.get(position).getName().substring(0,1));
        avatar.setText( list.get(position).getName().substring(0,1));


        return convertView;
    }
}
