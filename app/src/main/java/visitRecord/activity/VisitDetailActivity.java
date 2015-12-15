package visitRecord.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
import visitRecord.model.VisitorModel;
import visitRecord.view.HorizontalListView;

@ContentView(R.layout.activity_visit_detail)
public class VisitDetailActivity extends BaseActivity {

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
    private RecordModel record;
    private RecordModel recordModel ;
    @ViewInject(R.id.listview)
    HorizontalListView listView;
    List<VisitorModel> list = new ArrayList<>();
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

    private List<VisitorModel> getData(Long rid){
        //TODO 根据rid获取visitorlist
        VisitorModel visitor1 = new VisitorModel();
        visitor1.setName("张三");
        VisitorModel visitor2 = new VisitorModel();
        visitor1.setName("李四");
        VisitorModel visitor3 = new VisitorModel();
        visitor1.setName("王五");

        list.add(visitor1);
        list.add(visitor2);
        list.add(visitor3);
        return list;
    }


}
