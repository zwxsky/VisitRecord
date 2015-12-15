package visitRecord.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import demo.example.zwx.activity.R;
import visitRecord.model.RecordModel;
import visitRecord.util.DateUtils;
import visitRecord.util.ViewHolder;

/**
 * Created by luo on 15-12-2.
 */
public class IntervieweeAdapter extends AdapterManager<RecordModel> {



    public IntervieweeAdapter(List<RecordModel> list, Context context) {
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
        convertView = this.inflater.inflate(R.layout.interviewee_item, null);

        TextView reason= ViewHolder.get(convertView,R.id.reason);
        TextView date= ViewHolder.get(convertView,R.id.date);
        TextView logs= ViewHolder.get(convertView,R.id.logs);
//        convertView.setTag(viewHolder);


        reason.setText( list.get(position).getReason());
        date.setText(DateUtils.dateToStr(list.get(position).getGmtVisit()));
        logs.setText(list.get(position).getLogs().toString());
        return convertView;
    }
}
