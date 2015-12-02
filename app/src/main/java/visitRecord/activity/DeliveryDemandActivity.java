package visitRecord.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import demo.example.zwx.activity.R;
import visitRecord.Base.BaseActivity;
import visitRecord.model.RecordModel;

@ContentView(R.layout.activity_delivery_demand)
public class DeliveryDemandActivity extends BaseActivity implements  AdapterView.OnItemClickListener,AbsListView.OnScrollListener{
    @ViewInject(R.id.group_title)
    TextView title;
    SimpleAdapter simpleAdapter;
    @ViewInject(R.id.listview)
    ListView listView;
    List<RecordModel> recordList;
    List<Map<String,Object>> mapList;
    int pageFrom=0;
    int pageTo=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        title.setText("待领需求");


        mapList = new ArrayList<Map<String,Object>>();
        simpleAdapter = new SimpleAdapter(this,getDate(),R.layout.delivery_list,new String[]{"title","gmtVisit"},new int[]{R.id.title,R.id.gmtVisit});
//        listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(this);
        listView.setOnScrollListener(this);



    }

    public void showVisitDetail(View view){
        startActivity(new Intent(this, VisitDetailActivity.class));
    }

    public void showPersonInfo(View view){
        startActivity(new Intent(this,PersonInfoActivity.class));
    }


    private List<Map<String,Object>> getDate(){
        for(int i=0;i<20;i++){
            Map<String,Object> map = new HashMap<>();
            map.put("title","探访习主席"+i);
            map.put("gmtVisit","11月9日 下午4：30");
            map.put("status",0);
            mapList.add(map);
        }
        return mapList;

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
        Map item = (Map) listView.getItemAtPosition(position);
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
                    Map<String,Object> map = new HashMap<String,Object>();
                    map.put("title","探望青云"+i);
                    map.put("gmtVisit","新增数据");
                    map.put("status",0);
                    //mapList.clear();
                    mapList.add(map);
                }

                simpleAdapter.notifyDataSetChanged();

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
}
