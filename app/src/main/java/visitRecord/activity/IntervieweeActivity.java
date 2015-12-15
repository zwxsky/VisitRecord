package visitRecord.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import demo.example.zwx.activity.R;
import visitRecord.Base.MineActivity;
import visitRecord.adapter.IntervieweeAdapter;
import visitRecord.adapter.RecordAdapter;
import visitRecord.model.Record;
import visitRecord.model.RecordCond;
import visitRecord.model.RecordModel;
import visitRecord.util.DateUtils;

@ContentView(R.layout.activity_interviewee)
public class IntervieweeActivity extends MineActivity {

    private IntervieweeAdapter adapter;
    @ViewInject(R.id.listview)
    ListView listView;
    @ViewInject(R.id.group_title)
    TextView title;
    RecordCond cond = new RecordCond();
    List<RecordModel> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title.setText("核桃");
        Intent intent = getIntent();
        cond.setUid(intent.getLongExtra("uid", 0));
        adapter = new IntervieweeAdapter(getData(cond),this);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_interviewee, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private List<RecordModel> getData(RecordCond cond){

        RecordModel recordModel = new RecordModel();
        for(int i=0;i<16;i++){
            recordModel.setGmtVisit(DateUtils.getFirstDayOfMonth(new Date()));
            recordModel.setLogs("654sddddsee");
            recordModel.setReason("发现乱码" + i);
            list.add(recordModel);
        }
        //TODO 根据uid 查出和他相关的 记录
        return list;
    }

    @Event(value = R.id.back)
    private void onBackClick(View view){
        finish();
    }
}
