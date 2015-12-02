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
import java.util.Map;

import demo.example.zwx.activity.R;
import visitRecord.Base.BaseActivity;
import visitRecord.model.RecordModel;

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
    private Map<String,Object> map ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_visit_detail);
        Intent intent = this.getIntent();
        map = (Map<String, Object>) intent.getSerializableExtra("record");
        if(map.get("status").equals(2)){
            finishVisit.setVisibility(View.GONE);
            addVisit.setVisibility(View.GONE);
        }

        if(map.get("status").equals(1)){
            finishVisit.setVisibility(View.VISIBLE);
            addVisit.setVisibility(View.GONE);
        }
        if(map.get("status").equals(0)){

            edit.setVisibility(View.VISIBLE);
            finishVisit.setVisibility(View.GONE);
            addVisit.setVisibility(View.VISIBLE);
        }


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
        bundle.putSerializable("record",(Serializable)map);
        intent.putExtras(bundle);
        this.startActivity(intent);
    }

    @Event(value = R.id.addVisit)
    private void onAddVisitClick(View view){
        Intent intent = new Intent(this,VisitDetailEditActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("record",(Serializable)map);
        intent.putExtras(bundle);
        this.startActivity(intent);
    }

    @Event(value = R.id.edit)
    private void onEditClick(View view){
        Intent intent = new Intent(this,VisitDetailEditActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("record",(Serializable)map);
        intent.putExtras(bundle);
        this.startActivity(intent);
    }

}
