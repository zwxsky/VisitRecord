package visitRecord.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import visitRecord.model.RecordModel;
import visitRecord.model.User;

/**
 * Created by luo on 15-12-2.
 */
public class RecordAdapter extends BaseAdapter {

    LayoutInflater mInflater;
    List<RecordModel> list;

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
        return null;
    }
}
