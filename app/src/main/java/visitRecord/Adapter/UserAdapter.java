package visitRecord.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import demo.example.zwx.activity.R;
import visitRecord.activity.VisitDetailEditActivity;
import visitRecord.model.Base;
import visitRecord.model.User;

/**
 * Created by luo on 15-12-1.
 */
public class UserAdapter extends BaseAdapter{

    LayoutInflater mInflater;
    List<User> list;



    public final class ViewHolder{
        public TextView name;
    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.list_item,null);
            viewHolder.name= (TextView) convertView.findViewById(R.id.name);
            convertView.setTag(viewHolder);
        }else{
            convertView.getTag();
        }

        viewHolder.name.setText(list.get(position).getName());
        return convertView;
    }
}
