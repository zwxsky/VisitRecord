package visitRecord.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import demo.example.zwx.activity.R;
import visitRecord.Base.BaseActivity;

@ContentView(R.layout.activity_setting)
public class SettingActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_setting, menu);
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

    @Event(value=R.id.block)
    private void personInfoClick(View view){
        //TODO 根据uid获取
        startActivity(new Intent(this,PersonInfoActivity.class));
    }

    @Event(value=R.id.back)
    private void backClick(View view){

        finish();
    }
    @Event(value=R.id.logout)
    private void logoutClick(View view){
        AlertDialog alertDialog = new AlertDialog.Builder(getBaseContext())
                .setTitle("提示")
                .setMessage("确定退出")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TODO 退出系统
                        System.exit(0);
                    }
                })
                .setNegativeButton("取消",null)
                .show();


    }
}
