package visitRecord.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

import demo.example.zwx.activity.R;
import visitRecord.Base.BaseActivity;

@ContentView(R.layout.activity_person_info)
public class PersonInfoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_person_info, menu);
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

    public  void showPersonInfo(View view){
        startActivity(new Intent(this, PreviewPersonInfoActivity.class));
    }

    public void save(View view){
        Log.i("personinfo","in save");
        new AlertDialog.Builder(PersonInfoActivity.this).setPositiveButton("保存成功",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(PersonInfoActivity.this, PreviewActivity.class));
            }
        }).show();


    }

    @Event(value=R.id.back)
    private void onBackClick(View view){
        finish();
    }


    @Event(value=R.id.avatar)
    private void onAvatarClick(View view) {
        startActivity(new Intent(this, PreviewPersonInfoActivity.class));
    }

}
