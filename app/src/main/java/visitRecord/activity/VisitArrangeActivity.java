package visitRecord.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import demo.example.zwx.activity.R;
import visitRecord.Base.BaseActivity;
import visitRecord.model.RecordModel;

@ContentView(R.layout.activity_visit_arrange)
public class VisitArrangeActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener,AbsListView.OnScrollListener {

    SimpleAdapter simpleAdapter;
    ArrayAdapter<RecordModel> arrayAdapter;
    ListView listView;
    List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
    Button visiting;
    Button finished;
    Button back;
    List<RecordModel> recordModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listView = (ListView)findViewById(R.id.list);
        simpleAdapter = new SimpleAdapter(this,getVisitingData(),R.layout.arrange_item,new String[]{"title","date"},new int[]{R.id.title,R.id.date});
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(this);
        listView.setOnScrollListener(this);
        visiting = (Button) findViewById(R.id.visiting);
        finished = (Button) findViewById(R.id.finished);
        visiting.setOnClickListener(this);
        finished.setOnClickListener(this);
        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(this);



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


    public List<Map<String,Object>> getVisitingData(){
        for(int i=0;i<16;i++){
            Map map = new HashMap();
            map.put("title","探望青云"+i);
            map.put("date","7月5日 14：30");
            map.put("status",1);
            map.put("id",i);
            mapList.add(map);
        }
        return mapList;
    }
    public List<Map<String,Object>> getFinishedData(){
        for(int i=0;i<16;i++){
            Map map = new HashMap();
            map.put("title", "探望马云" + i);
            map.put("date", "7月5日 14：30");
            map.put("status", 2);
            map.put("id",i);
            mapList.add(map);
        }
        return mapList;
    }



    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.visiting:
                mapList.clear();
                simpleAdapter = new SimpleAdapter(this,getVisitingData(),R.layout.arrange_item,new String[]{"title","date"},new int[]{R.id.title,R.id.date});
                listView.setAdapter(simpleAdapter);

                listView.setOnItemClickListener(this);
                findViewById(R.id.visiting_line).setBackgroundResource(R.color.green);
                findViewById(R.id.finished_line).setBackgroundResource(R.color.gray);
                break;
            case R.id.finished:
                mapList.clear();
                simpleAdapter = new SimpleAdapter(this,getFinishedData(),R.layout.arrange_item,new String[]{"title","date"},new int[]{R.id.title,R.id.date});
                listView.setAdapter(simpleAdapter);

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
        Map item = (Map) listView.getItemAtPosition(position);
        Toast.makeText(this,"position="+position+" ,status="+item.get("status")+view.toString(),Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,VisitDetailActivity.class);
        Bundle bundle = new Bundle();
//        RecordModel record  = new RecordModel();
//        record.setLogs("ddd");
//        record.setTitle("hello test");
//        record.setReason("sick");
        bundle.putSerializable("record", (Serializable) item);
        intent.putExtras(bundle);

//        intent.putExtra("status", item.get("status").toString());
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
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("title","探望青云");
                map.put("date","新增数据");
                map.put("status",1);
                mapList.clear();
                mapList.add(map);
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
