package visitRecord.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import demo.example.zwx.activity.R;
import visitRecord.model.RecordModel;
import visitRecord.util.ViewHolder;

/**
 * Created by luo on 15-12-2.
 */
public class RecordAdapter extends AdapterManager<RecordModel> {



    public RecordAdapter(List<RecordModel> list, Context context) {
        super(list, context);
    }


    @Override
    public RecordModel getItem(int position) {
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
        convertView = this.inflater.inflate(R.layout.arrange_item, null);

        TextView title= ViewHolder.get(convertView,R.id.title);
        TextView date= ViewHolder.get(convertView,R.id.date);
//        convertView.setTag(viewHolder);


        title.setText("探访"+ list.get(position).getName());
        date.setText(list.get(position).getGmtVisit().toString());
        return convertView;
    }
}
