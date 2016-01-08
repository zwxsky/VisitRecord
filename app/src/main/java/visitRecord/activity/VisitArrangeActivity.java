package visitRecord.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import demo.example.zwx.activity.R;
import visitRecord.adapter.RecordAdapter;
import visitRecord.Base.BaseActivity;
import visitRecord.http.ArrangeParams;
import visitRecord.http.HttpUtil;
import visitRecord.model.CustomDate;
import visitRecord.model.Record;
import visitRecord.model.RecordCond;
import visitRecord.model.RecordModel;
import visitRecord.util.DateUtil;
import visitRecord.util.DateUtils;

@ContentView(R.layout.activity_visit_arrange)
public class VisitArrangeActivity extends BaseActivity implements AdapterView.OnItemClickListener,AbsListView.OnScrollListener {

    @ViewInject(R.id.list)
    ListView listView;
    Button visiting;
    Button finished;
    Button back;
    List<RecordModel> list = new ArrayList<RecordModel>();
    RecordAdapter adapter;
    RecordCond cond = new RecordCond();
    int start =0;
    int limit =15;
    int status= Record.STATUS_ACCEPTED;
    int scorllNum=0; //记录下拉次数
    @ViewInject(R.id.tvCurrentMonth)
    TextView tvCurrentMonth;
    Date date = new Date();
    Gson gson;
    ArrangeParams params = new ArrangeParams();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listView = (ListView)findViewById(R.id.list);
//      simpleAdapter = new SimpleAdapter(this,getVisitingData(),R.layout.arrange_item,new String[]{"title","date"},new int[]{R.id.title,R.id.date});
//      listView.setAdapter(simpleAdapter);


        listView.setOnItemClickListener(this);
        listView.setOnScrollListener(this);



        tvCurrentMonth.setText(DateUtils.dateToStr(date, "yyyy-MM"));

       /* RequestParams params = new RequestParams(HttpUtil.RECORD_LIST);
        params.addBodyParameter("fromTime", DateUtils.dateToStr(DateUtils.getFirstDayOfMonth(date), "yyyy-MM-dd hh:mm:ss"));
        params.addBodyParameter("toTime", DateUtils.dateToStr(DateUtils.getFirstDayOfMonth(date), "yyyy-MM-dd hh:mm:ss"));
        params.addBodyParameter("status",status);
        params.addHeader("");*/
        params.removeParameter("fromTime");
        params.removeParameter("toTime");
        params.removeParameter("status");
        params.fromTime = DateUtils.dateToStr(DateUtils.getFirstDayOfMonth(date),"yyyy-MM-dd hh:mm:ss");
        params.toTime = DateUtils.dateToStr(DateUtils.getLastDayOfMonth(date), "yyyy-MM-dd hh:mm:ss");
        params.status=1;
        showdata(params);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_visit_arrange, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

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



   /* public List<RecordModel> getFinishedData(){
        //TODO post 请求，获取已完成的该月数据
        RecordModel map = new RecordModel();
        status =2;
        cond.setToTime(DateUtils.getLastDayOfMonth(new Date()));
        cond.setFromTime(DateUtils.getFirstDayOfMonth(new Date()));
        cond.setStart(start);
        cond.setLimit(limit);
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

        params.status =2;

        return list;
    }

    public List<RecordModel> getVisitingData(){
        //TODO get 请求，获取未完成的该月数据
        RecordModel map = new RecordModel();
        status =1;
        cond.setToTime(DateUtils.getLastDayOfMonth(new Date()));
        cond.setFromTime(DateUtils.getFirstDayOfMonth(new Date()));
        cond.setStart(start);
        cond.setLimit(limit);
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
    }*/

   /* @Override
    public void onClick(View v) {
        RecordCond cond = new RecordCond();
        cond.setStart(start);
        cond.setLimit(limit);
        cond.setFromTime(DateUtils.getFirstDayOfMonth(new Date()));
        cond.setToTime(DateUtils.getLastDayOfMonth(new Date()));


        switch (v.getId()){
            case R.id.visiting:
                list.clear();
                params.fromTime = DateUtils.dateToStr(DateUtils.getFirstDayOfMonth(date),"yyyy-MM-dd hh:mm:ss");
                params.toTime = DateUtils.dateToStr(DateUtils.getLastDayOfMonth(date), "yyyy-MM-dd hh:mm:ss");
                params.status=1;
                showdata(params);
                listView.setOnItemClickListener(this);

                findViewById(R.id.visiting_line).setBackgroundResource(R.color.green);
                findViewById(R.id.finished_line).setBackgroundResource(R.color.gray);
                break;
            case R.id.finished:
                list.clear();;
;
                ArrangeParams pa = new ArrangeParams();
                pa.status=2;
                pa.fromTime = DateUtils.dateToStr(DateUtils.getFirstDayOfMonth(date),"yyyy-MM-dd hh:mm:ss");
                pa.toTime = DateUtils.dateToStr(DateUtils.getLastDayOfMonth(date), "yyyy-MM-dd hh:mm:ss");
                showdata(pa);

                listView.setOnItemClickListener(this);

                findViewById(R.id.visiting_line).setBackgroundResource(R.color.gray);
                findViewById(R.id.finished_line).setBackgroundResource(R.color.green);
                break;
            case R.id.back:
                finish();break;
        }
    }*/

    @Event(R.id.visiting)
    private void onVisitingClick(View view){
        list.clear();
        status =Record.STATUS_ACCEPTED;
        params.removeParameter("fromTime");
        params.removeParameter("toTime");
        params.removeParameter("status");
        params.fromTime = DateUtils.dateToStr(DateUtils.getFirstDayOfMonth(date),"yyyy-MM-dd hh:mm:ss");
        params.toTime = DateUtils.dateToStr(DateUtils.getLastDayOfMonth(date), "yyyy-MM-dd hh:mm:ss");
        params.status=status;

        showdata(params);
        listView.setOnItemClickListener(this);

        findViewById(R.id.visiting_line).setBackgroundResource(R.color.green);
        findViewById(R.id.finished_line).setBackgroundResource(R.color.gray);

    }

     @Event(R.id.finished)
        private void onFinishedClick(View view){
            list.clear();
         status =Record.STATUS_FINISHED;
            params.removeParameter("fromTime");
            params.removeParameter("toTime");
            params.removeParameter("status");
            params.fromTime = DateUtils.dateToStr(DateUtils.getFirstDayOfMonth(date),"yyyy-MM-dd hh:mm:ss");
            params.toTime = DateUtils.dateToStr(DateUtils.getLastDayOfMonth(date), "yyyy-MM-dd hh:mm:ss");
            params.status=status;
            findViewById(R.id.visiting_line).setBackgroundResource(R.color.gray);
            findViewById(R.id.finished_line).setBackgroundResource(R.color.green);

            showdata(params);
            listView.setOnItemClickListener(this);



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
                cond.setStart(limit);
                cond.setLimit(limit + scorllNum * 5);
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
        toast(DateUtils.dateToStr(DateUtils.getFirstDayOfMonth(date), "yyyy-MM-dd hh:mm:ss"));

        params.removeParameter("fromTime");
        params.removeParameter("toTime");
        params.removeParameter("status");
        params.fromTime = DateUtils.dateToStr(DateUtils.getFirstDayOfMonth(date),"yyyy-MM-dd hh:mm:ss");
        params.toTime = DateUtils.dateToStr(DateUtils.getLastDayOfMonth(date), "yyyy-MM-dd hh:mm:ss");
        params.status=status;
        showdata(params);




    }

    @Event(value = R.id.btnNextMonth)
    private void onNextBtnClick(View view){

        date = DateUtils.getRelativeDate(date, Calendar.MONTH,1);
        tvCurrentMonth.setText(DateUtils.dateToStr(date, "yyyy-MM"));
        //TODO 根据日期查询
        params.removeParameter("fromTime");
        params.removeParameter("toTime");
        params.removeParameter("status");
        params.fromTime = DateUtils.dateToStr(DateUtils.getFirstDayOfMonth(date),"yyyy-MM-dd hh:mm:ss");
        params.toTime = DateUtils.dateToStr(DateUtils.getLastDayOfMonth(date), "yyyy-MM-dd hh:mm:ss");
        params.status=status;
        showdata(params);


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
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode ==0){
            if(data.getStringExtra("date") != null) {
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


    private Gson createGson(){
        GsonBuilder builder = new GsonBuilder();

        // Register an adapter to manage the date types as long values
        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong());
            }
        });
        return builder.create();
    }

    private void showdata(ArrangeParams params){
//        params.addBodyParameter("json", params);
        Callback.Cancelable cancelable= x.http().get(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String s) {
                        toast(s);

                        gson = createGson();
                        list = gson.fromJson(s, new TypeToken<ArrayList<RecordModel>>() {
                        }.getType());

                        adapter = new RecordAdapter(list, getBaseContext());
                        listView.setAdapter(adapter);

                    }

                    @Override
                    public void onError(Throwable throwable, boolean b) {

                    }

                    @Override
                    public void onCancelled(CancelledException e) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });

    }

}
