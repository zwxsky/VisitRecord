package visitRecord.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import demo.example.zwx.activity.R;
import visitRecord.Base.BaseActivity;
import visitRecord.adapter.AvatarAdapter;
import visitRecord.model.RecordModel;
import visitRecord.model.Visitor;
import visitRecord.model.VisitorModel;
import visitRecord.view.HorizontalListView;

@ContentView(R.layout.activity_visit_detail)
public class VisitDetailActivity extends BaseActivity implements OnItemClickListener {

    @ViewInject(R.id.name)
    private TextView name;
    @ViewInject(R.id.gmtVisit)
    private TextView gmtVisit;
    @ViewInject(R.id.addr)
    private TextView addr;
    @ViewInject(R.id.reason)
    private TextView reason;
    @ViewInject(R.id.edit)
    private Button edit;
    @ViewInject(R.id.finishVisit)
    private Button finishVisit;
    @ViewInject(R.id.addVisit)
    private Button addVisit;
    private RecordModel recordModel ;
    @ViewInject(R.id.listview)
    HorizontalListView listView;
    List<Visitor> list = new ArrayList<>();
    AvatarAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_visit_detail);
        Intent intent = this.getIntent();
        recordModel = (RecordModel) intent.getSerializableExtra("record");
        if(recordModel.getStatus().equals(2)){
            finishVisit.setVisibility(View.GONE);
            addVisit.setVisibility(View.GONE);
        }

        if(recordModel.getStatus().equals(1)){
            finishVisit.setVisibility(View.VISIBLE);
            addVisit.setVisibility(View.GONE);
        }
        if(recordModel.getStatus().equals(0)){

            edit.setVisibility(View.VISIBLE);
            finishVisit.setVisibility(View.GONE);
            addVisit.setVisibility(View.VISIBLE);
        }

        //TODO 获取recordModel详情  包括List<VisitorModel>

        adapter = new AvatarAdapter(getData(17l),this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);



//        record = (RecordModel) intent.getSerializableExtra("record");
//        name.setText(record.getReason());
//        setContentView(R.layout.activity_visit_detail);

        //Toast.makeText(this,record.getReason()+record.getTitle(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_visit_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Event(value=R.id.back)
    private void onBackClick(View view){
        finish();
    }

    @Event(value=R.id.avatar)
    private void onAvatarClick(View view) {
     startActivity(new Intent(this, PreviewPersonInfoActivity.class));
    }


    @Event(value = R.id.finishVisit)
    private void onFinishClick(View view){
        Intent intent = new Intent(this,LogsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("record",(Serializable)recordModel);
        intent.putExtras(bundle);
        this.startActivity(intent);
        //TODO 未完成里面删除,完成里面增加一条
    }

    @Event(value = R.id.addVisit)
    private void onAddVisitClick(View view){
        Intent intent = new Intent(this,VisitDetailEditActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("record",(Serializable)recordModel);
        intent.putExtras(bundle);
        this.startActivity(intent);
    }

    @Event(value = R.id.edit)
    private void onEditClick(View view){
        Intent intent = new Intent(this,VisitDetailEditActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("record",(Serializable)recordModel);
        intent.putExtras(bundle);
        this.startActivity(intent);
    }

    @Event(value = R.id.name)
    private void onNameClick(View view){
        Intent intent = new Intent(this,IntervieweeActivity.class);
        intent.putExtra("uid", recordModel.getUid());
        startActivity(intent);
    }

    private List<Visitor> getData(Long rid){
        //TODO 根据rid获取visitorlist
        Visitor visitor1 = new Visitor();
        visitor1.setRid(1l);
        visitor1.setUid(1l);
        visitor1.setUname("张三");
        Visitor visitor2 = new Visitor();
        visitor2.setUname("李四");
        visitor2.setRid(2l);
        visitor2.setUid(2l);
        Visitor visitor3 = new Visitor();
        visitor3.setUname("王五");
        visitor3.setRid(3l);
        visitor3.setUid(3l);

        list.add(visitor1);
        list.add(visitor2);
        list.add(visitor3);
        return list;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        toast("hello"+position+"  name:"+list.get(position).getUname());
        Intent intent = new Intent(this,PreviewPersonInfoActivity.class);
        intent.putExtra("uid",list.get(position).getUid());
        startActivity(intent);
    }
}
