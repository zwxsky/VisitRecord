package visitRecord.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import demo.example.zwx.activity.R;
import visitRecord.Base.BaseActivity;
import visitRecord.model.RecordModel;

@ContentView(R.layout.activity_logs)
public class LogsActivity extends BaseActivity {

    @ViewInject(R.id.group_title)
    TextView groupTitle;
    RecordModel recordModel;
    @ViewInject(R.id.logs)
    TextView logs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_logs);
        groupTitle.setText("探访反馈小结");
        Intent intent = getIntent();
        recordModel = (RecordModel) intent.getSerializableExtra("record");



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_logs, menu);
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

    @Event(value = R.id.submit)
    private void onSubmitClick(View view){
        //TODO 将数据传到后台改变状态
        if(logs.getText()== null || logs.getText()==""){
            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setTitle("警告")
                    .setMessage("内容不能为空")
                    .setPositiveButton("确定",null)
                    .show();
        }

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("确定提交")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("取消", null)
                .show();

    }

    @Event(value = R.id.back)
    private void onBackClick(View view){
        finish();
    }
}
