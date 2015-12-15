package visitRecord.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import demo.example.zwx.activity.R;
import visitRecord.Base.MineActivity;
import visitRecord.adapter.RecordAdapter;
import visitRecord.Base.BaseActivity;
import visitRecord.model.CustomDate;
import visitRecord.model.Record;
import visitRecord.model.RecordCond;
import visitRecord.model.RecordModel;
import visitRecord.util.DateUtil;
import visitRecord.util.DateUtils;

@ContentView(R.layout.activity_delivery_demand)
public class DeliveryDemandActivity extends MineActivity implements  AdapterView.OnItemClickListener,AbsListView.OnScrollListener{
    @ViewInject(R.id.group_title)
    TextView title;
    RecordAdapter adapter;
    @ViewInject(R.id.listview)
    ListView listView;
    List<RecordModel> list = new ArrayList<RecordModel>();
    List<Map<String,Object>> mapList;
    int pageFrom=0;
    int pageTo=5;
    CustomDate customDate;
    @ViewInject(R.id.tvCurrentMonth)
    TextView tvCurrentMonth;
    RecordCond cond = new RecordCond();
    Date date = new Date();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        title.setText("待领需求");
        mapList = new ArrayList<Map<String,Object>>();
//        simpleAdapter = new SimpleAdapter(this,getDate(),R.layout.delivery_list,new String[]{"title","gmtVisit"},new int[]{R.id.title,R.id.gmtVisit});
        adapter = new RecordAdapter(getDate(),this);
//      listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        listView.setOnScrollListener(this);

    }

    public void showVisitDetail(View view){
        startActivity(new Intent(this, VisitDetailActivity.class));
    }

    public void showPersonInfo(View view){
        startActivity(new Intent(this, PersonInfoActivity.class));
    }



    private List<RecordModel> getDate(){
        RecordModel map = new RecordModel();
            for(int i=0;i<20;i++){
//                map.setDate("探访习主席" + i);
                map.setName("hello,this is title"+i);
                map.setGmtVisit(new Date());
                map.setStatus(0);
                list.add(map);
            }
            return list;

        }

    @Event(value = R.id.back,
            type = View.OnClickListener.class)
    private  void onBackClick(View view){

        finish();
    }

    @Event(value = R.id.date,
            type = View.OnClickListener.class)
    private  void onDateClick(View view){
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        Intent intent = new Intent(this,VisitDetailActivity.class);
        Bundle bundle = new Bundle();
        RecordModel item = (RecordModel) list.get(position);
        bundle.putSerializable("record", (Serializable) item);
        intent.putExtras(bundle);

        startActivity(intent);



    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState){
            case SCROLL_STATE_FLING:
                Log.i("main", "用户在手指离开之前，由于用力滑了一下，视图依靠惯性继续滑动");
                pageFrom =pageFrom+5;
                pageTo = pageTo+5;

                for(int i=pageFrom;i<pageTo;i++){
                    //TODO 获取record列表
                    RecordModel map = new RecordModel();
                    map.setTitle("探望青云" + i);
                    map.setName("this is new add data" + i);
                    map.setGmtVisit(DateUtil.getDateFromString(2015,11));
                    map.setStatus(0);
                    //mapList.clear();
                    adapter.addData(map);
                }

//                adapter.notifyDataSetChanged();

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

//        CustomDate custom = new CustomDate(customDate.getYear(),customDate.getMonth()-1,customDate.getDay());
//        customDate = custom;
        date = DateUtils.getRelativeDate(date, Calendar.MONTH, -1);
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

//        CustomDate custom = new CustomDate(customDate.getYear(),customDate.getMonth()+1,customDate.getDay());
//        customDate = custom;
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
        cond.setStatus(Record.STATUS_UNACCEPT);
        //TODO POST请求
        return null;
    }

    @Event(value = R.id.tvCurrentMonth)
    private void onCurrentMonthClick(View v){
        startActivityForResult(new Intent(getBaseContext(), CalendarActivity.class), 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2 && resultCode ==0){
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

        if(resultCode==-1){
            return;
        }
    }
}
