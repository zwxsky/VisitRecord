package visitRecord.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import demo.example.zwx.activity.R;
import visitRecord.Base.MineActivity;
import visitRecord.adapter.RecordAdapter;
import visitRecord.Base.BaseActivity;
import visitRecord.model.Record;
import visitRecord.model.RecordCond;
import visitRecord.model.RecordModel;
import visitRecord.util.DateUtil;
import visitRecord.util.DateUtils;


@ContentView(R.layout.activity_my_record)
public class MyRecordActivity extends MineActivity implements View.OnClickListener, AdapterView.OnItemClickListener,AbsListView.OnScrollListener  {

    RecordCond cond = new RecordCond();
    int start =0;
    int limit =15;
    int status= Record.STATUS_ACCEPTED;
    int scorllNum=0; //记录下拉次数
    @ViewInject(R.id.tvCurrentMonth)
    TextView tvCurrentMonth;
    @ViewInject(R.id.list)
    ListView listView;
    List<RecordModel> list = new ArrayList<RecordModel>();
    RecordAdapter adapter;
    @ViewInject(R.id.visiting)
    Button visiting;
    @ViewInject(R.id.finished)
    Button finished;
    Date date = new Date();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        visiting.setOnClickListener(this);
        finished.setOnClickListener(this);
        date = DateUtils.getRelativeDate(date,Calendar.MONTH,0);
        tvCurrentMonth.setText(DateUtils.dateToStr(date,"yyyy-MM"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_record, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Event(value = R.id.btnPreMonth)
    private void onPreBtnClick(View view){

        date = DateUtils.getRelativeDate(date, Calendar.MONTH, -1);
        tvCurrentMonth.setText(DateUtils.dateToStr(date, "yyyy-MM"));
        //TODO 根据日期查询
        cond.setFromTime(DateUtils.getFirstDayOfMonth(date));
        cond.setToTime(DateUtils.getLastDayOfMonth(date));
        cond.setStart(start);
        cond.setLimit(limit);
        adapter = new RecordAdapter(queryByCond(cond),this);
        listView.setAdapter(adapter);



    }

    @Event(value = R.id.btnNextMonth)
    private void onNextBtnClick(View view){

        date = DateUtils.getRelativeDate(date, Calendar.MONTH,1);
        tvCurrentMonth.setText(DateUtils.dateToStr(date, "yyyy-MM"));
        //TODO 根据日期查询
        cond.setFromTime(DateUtils.getFirstDayOfMonth(date));
        cond.setToTime(DateUtils.getLastDayOfMonth(date));
        cond.setStart(start);
        cond.setLimit(limit);
        adapter = new RecordAdapter(queryByCond(cond),this);
        listView.setAdapter(adapter);
    }

    private List<RecordModel> queryByCond(RecordCond cond){
        //TODO POST请求
        return null;
    }

    @Event(value = R.id.tvCurrentMonth)
    private void onCurrentMonthClick(View v){
        startActivityForResult(new Intent(getBaseContext(), CalendarActivity.class), 3);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==3 && resultCode ==0){
            String dateStr = data.getStringExtra("date");
            if(dateStr != null) {
                date = DateUtils.strToDate(data.getStringExtra("date"));

                tvCurrentMonth.setText(DateUtils.dateToStr(date, "yyyy-MM"));
                //TODO 查询对应的数据
                cond.setFromTime(DateUtils.getFirstDayOfMonth(date));
                cond.setToTime(DateUtils.getLastDayOfMonth(date));
                cond.setStart(start);
                cond.setLimit(limit);
                adapter = new RecordAdapter(queryByCond(cond), this);
                listView.setAdapter(adapter);
            }
        }
    }

    @Override
    public void onClick(View v) {
        RecordCond cond = new RecordCond();
        cond.setStart(start);
        cond.setLimit(limit);
        cond.setFromTime(DateUtils.getFirstDayOfMonth(new Date()));
        cond.setToTime(DateUtils.getLastDayOfMonth(new Date()));
        cond.setUid(MineActivity.UID);


        switch (v.getId()){
            case R.id.visiting:
                list.clear();
                cond.setStatus(Record.STATUS_ACCEPTED);
                adapter = new RecordAdapter(queryByCond(cond),this);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(this);

                findViewById(R.id.visiting_line).setBackgroundResource(R.color.green);
                findViewById(R.id.finished_line).setBackgroundResource(R.color.gray);
                break;
            case R.id.finished:
                list.clear();;
                cond.setStatus(Record.STATUS_FINISHED);
                adapter = new RecordAdapter(queryByCond(cond),this);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(this);

                findViewById(R.id.visiting_line).setBackgroundResource(R.color.gray);
                findViewById(R.id.finished_line).setBackgroundResource(R.color.green);
                break;
            case R.id.back:
                finish();break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}
