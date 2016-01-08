package visitRecord.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

import demo.example.zwx.activity.R;
import visitRecord.Base.BaseActivity;
import visitRecord.Base.MineActivity;

@ContentView(R.layout.activity_preview)
public class PreviewActivity extends BaseActivity implements View.OnClickListener {

    ImageButton setting; //用户设置
    TextView arrange; //探访安排
    TextView edit;   //待领需求
    TextView record; //我的记录
    TextView addRecord; //发起需求
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setting = (ImageButton)findViewById(R.id.setting);
        arrange = (TextView)findViewById(R.id.arrange);
        edit = (TextView)findViewById(R.id.edit);
        record = (TextView)findViewById(R.id.record);
        addRecord = (TextView)findViewById(R.id.addRecord);

        setting.setOnClickListener(this);
        arrange.setOnClickListener(this);
        edit.setOnClickListener(this);
        record.setOnClickListener(this);
        addRecord.setOnClickListener(this);

        MineActivity.UID=1l;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_preview, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_quit) {
            new AlertDialog.Builder(this).setTitle("你确定要退出")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            System.exit(0);
                            onDestroy();
//
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }

            }).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showPersonInfo(View view){
        startActivity(new Intent(this,PersonInfoActivity.class));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            /*各人设置*/
            case R.id.setting:
                startActivity(new Intent(this,SettingActivity.class));break;
            /* 探访安排*/
            case R.id.arrange:
                startActivity(new Intent(this,VisitArrangeActivity.class));break;
            /* 待领需求*/
            case R.id.edit:
                startActivity(new Intent(this,DeliveryDemandActivity.class));
            break;
            /*我的记录*/
            case R.id.record:
                startActivity(new Intent(this,MyRecordActivity.class));break;
            /* 创建需求*/
            case R.id.addRecord:
                startActivity(new Intent(this,AddRecordActivity.class));break;
        }

    }

    @Event(value = R.id.avatar)
    private void onAvatarClick(View view){

        Intent intent = new Intent(getBaseContext(),PreviewPersonInfoActivity.class);
        intent.putExtra("uid", MineActivity.UID);
        startActivity(intent);
    }
}
