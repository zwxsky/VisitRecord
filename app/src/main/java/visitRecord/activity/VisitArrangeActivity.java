package visitRecord.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import demo.example.zwx.activity.R;
import visitRecord.adapter.RecordAdapter;
import visitRecord.Base.BaseActivity;
import visitRecord.model.CustomDate;
import visitRecord.model.Record;
import visitRecord.model.RecordCond;
import visitRecord.model.RecordModel;
import visitRecord.util.DateUtils;

@ContentView(R.layout.activity_visit_arrange)
public class VisitArrangeActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener,AbsListView.OnScrollListener {

    @ViewInject(R.id.list)
    ListView listView;
    Button visiting;
    Button finished;
    Button back;
    List<RecordModel> list = new ArrayList<RecordModel>();
    RecordAdapter adapter;
    RecordCond cond = new RecordCond();
    int pageFrom =0;
    int pageTo =15;
    int status= Record.STATUS_ACCEPTED;
    int scorllNum=0; //记录下拉次数
    @ViewInject(R.id.tvCurrentMonth)
    TextView tvCurrentMonth;
    Date date = new Date();
    CustomDate customDate = new CustomDate();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listView = (ListView)findViewById(R.id.list);
//      simpleAdapter = new SimpleAdapter(this,getVisitingData(),R.layout.arrange_item,new String[]{"title","date"},new int[]{R.id.title,R.id.date});
//      listView.setAdapter(simpleAdapter);
        adapter = new RecordAdapter(getVisitingData(),this);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);
        listView.setOnScrollListener(this);
        visiting = (Button) findViewById(R.id.visiting);
        finished = (Button) findViewById(R.id.finished);
        visiting.setOnClickListener(this);
        finished.setOnClickListener(this);
        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(this);

        tvCurrentMonth.setText(customDate.toYearMonth());



       /* preImgBtn = (ImageButton) this.findViewById(R.id.btnPreMonth);
        nextImgBtn = (ImageButton) this.findViewById(R.id.btnNextMonth);

        CalendarCard[] views = new CalendarCard[3];
        for (int i = 0; i < 3; i++) {
            views[i] = new CalendarCard(this, this);
        }
        adapter = new CalendarViewAdapter<>(views);
        setViewPager();*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_visit_arrange, menu);
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

    public  void showVisitDetail(View view){
        startActivity(new Intent(this,VisitDetailActivity.class));
    }



    public List<RecordModel> getFinishedData(){
        //TODO post 请求，获取已完成的该月数据
        RecordModel map = new RecordModel();
        status =2;
        cond.setToTime(DateUtils.getLastDayOfMonth(new Date()));
        cond.setFromTime(DateUtils.getFirstDayOfMonth(new Date()));
        cond.setPageFrom(pageFrom);
        cond.setPageTo(pageTo);
        //TODO post查询
        for(int i=0;i<16;i++){
            map.setName("探望马云" + i);
            map.setDate("7月5日 14：30");
            map.setGmtVisit(DateUtils.getLastDayOfMonth(new Date()));
            map.setId(i + 2l);
            map.setUid(1024l);
            map.setStatus(2);
            list.add(map);
        }
        return list;
    }

    public List<RecordModel> getVisitingData(){
        //TODO get 请求，获取未完成的该月数据
        RecordModel map = new RecordModel();
        status =1;
        cond.setToTime(DateUtils.getLastDayOfMonth(new Date()));
        cond.setFromTime(DateUtils.getFirstDayOfMonth(new Date()));
        cond.setPageFrom(pageFrom);
        cond.setPageTo(pageTo);
        //TODO post查询
        for(int i=0;i<16;i++){
            map.setName("探望青云"+i);
            map.setDate("7月5日 14：30");
            map.setGmtVisit(DateUtils.getFirstDayOfMonth(new Date()));
            map.setId(i+2l);
            map.setUid(1024l);
            map.setStatus(status);
            list.add(map);
        }
        return list;
    }

    @Override
    public void onClick(View v) {
        RecordCond cond = new RecordCond();
        cond.setPageFrom(pageFrom);
        cond.setPageTo(pageTo);
        cond.setFromTime(DateUtils.getFirstDayOfMonth(new Date()));
        cond.setToTime(DateUtils.getLastDayOfMonth(new Date()));


        switch (v.getId()){
            case R.id.visiting:
                list.clear();
                adapter = new RecordAdapter(getVisitingData(),this);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(this);

                findViewById(R.id.visiting_line).setBackgroundResource(R.color.green);
                findViewById(R.id.finished_line).setBackgroundResource(R.color.gray);
                break;
            case R.id.finished:
                list.clear();;
                adapter = new RecordAdapter(getFinishedData(),this);
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

        RecordModel recordModel = list.get(position);
        Toast.makeText(this,"position="+position+" ,id="+id+view.toString(),Toast.LENGTH_SHORT).show();
       //TODO 获取id，根据id获取探访详情

        Intent intent = new Intent(this,VisitDetailActivity.class);
        Bundle bundle = new Bundle();

        bundle.putSerializable("record", (Serializable) recordModel);
        intent.putExtras(bundle);

//        intent.putExtra("status", item.get("status").toYearMonth());
//        intent.putExtras("list", (Serializable) getFinishedData());
//        bundle.putSerializable("list", (Serializable) getFinishedData());
//        intent.putExtra(bundle);
        startActivity(intent);
    }

    @Event(value = R.id.back)
    private void OnBackClick(View view){
        finish();
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState){
            case SCROLL_STATE_FLING:
                Log.i("main", "用户在手指离开之前，由于用力滑了一下，视图依靠惯性继续滑动");
                RecordModel map= new RecordModel();
                map.setName("探望more 马云");
                map.setGmtVisit(DateUtils.getFirstDayOfMonth(new Date()));
                map.setStatus(status);
                //TODO 查询更多数据
              /*  scorllNum++;
                cond.setPageFrom(pageTo);
                cond.setPageTo(pageTo + scorllNum * 5);
                cond.setFromTime(DateUtils.getFirstDayOfMonth(date));
                cond.setToTime(DateUtils.getLastDayOfMonth(date));
                cond.setStatus(status);
                List<RecordModel> recordModelList = queryByCond(cond);*/

                //=================
                adapter.addData(map);
                break;
            case SCROLL_STATE_IDLE:
                Log.i("main","视图已经停止滑动");break;
            case SCROLL_STATE_TOUCH_SCROLL:
                Log.i("main","手指没有离开屏幕，视图还在滑动");break;
        }

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    @Event(value = R.id.btnPreMonth)
    private void onPreBtnClick(View view){

        date = DateUtils.getRelativeDate(date, Calendar.MONTH,-1);
        tvCurrentMonth.setText(DateUtils.dateToStr(date, "yyyy-MM"));
        //TODO 根据日期查询
        cond.setFromTime(DateUtils.getFirstDayOfMonth(date));
        cond.setToTime(DateUtils.getLastDayOfMonth(date));
        cond.setPageFrom(pageFrom);
        cond.setPageTo(pageTo);
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
        cond.setPageFrom(pageFrom);
        cond.setPageTo(pageTo);
        adapter = new RecordAdapter(queryByCond(cond),this);
        listView.setAdapter(adapter);
    }

    private List<RecordModel> queryByCond(RecordCond cond){
       //TODO POST请求
        return null;
    }

    @Event(value = R.id.tvCurrentMonth)
    private void onCurrentMonthClick(View v){
        startActivityForResult(new Intent(getBaseContext(), CalendarActivity.class), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==1 && resultCode ==0){
            if(data.getStringExtra("date") != null) {
                date = DateUtils.strToDate(data.getStringExtra("date"));

                tvCurrentMonth.setText(DateUtils.dateToStr(date, "yyyy-MM"));
                //TODO 查询对应的数据
                cond.setFromTime(DateUtils.getFirstDayOfMonth(date));
                cond.setToTime(DateUtils.getLastDayOfMonth(date));
                cond.setPageFrom(pageFrom);
                cond.setPageTo(pageTo);
                adapter = new RecordAdapter(queryByCond(cond), this);
                listView.setAdapter(adapter);
            }
        }
    }



}
