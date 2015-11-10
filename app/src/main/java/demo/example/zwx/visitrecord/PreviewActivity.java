package demo.example.zwx.visitrecord;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class PreviewActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

//        Button button = (Button)findViewById(R.id.sendNeed);

        /*button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(this,ReleaseVisitActivity.class));
                Log.i("debug:", "click sendNeed");
            }
        });*/
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


    public void setting(View view){
        startActivity(new Intent(this,PersonInfoActivity.class));
    }


    /*
    我的记录
    */
    public void myRecord(View view){

        startActivity(new Intent(this,ListDisplay.class));
    }

    /*
    待领需求
    */
    public void showLastNeed(View view){

        startActivity(new Intent(this,DeliveryDemandActivity.class));
    }


    public void createNeed(View view){
        startActivity(new Intent(this,AddRecordActivity.class));
    }


    public void visitArrange(View view){
        startActivity(new Intent(this,VisitArrangeActivity.class));
    }

    public void showPersonInfo(View view){
        startActivity(new Intent(this,PersonInfoActivity.class));
    }
}
