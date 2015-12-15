package visitRecord.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import demo.example.zwx.activity.R;
import visitRecord.Base.BaseActivity;
import visitRecord.Base.MineActivity;
import visitRecord.model.CustomDate;
import visitRecord.model.RecordModel;
import visitRecord.util.DateTimePickDialogUtil;
import visitRecord.util.DateUtil;
import visitRecord.util.DateUtils;

@ContentView(R.layout.activity_add_record)
public class AddRecordActivity extends MineActivity implements RadioGroup.OnCheckedChangeListener {
    private CustomDate date;
    @ViewInject(R.id.gmtVisit)
    private EditText gmtVisit;
    @ViewInject(R.id.name)
    private EditText name;
    @ViewInject(R.id.addr)
    private EditText addr;
    @ViewInject(R.id.reason)
    private EditText reason;
    @ViewInject(R.id.listview)
    private ListView listView;
    private String initStartDateTime = "2013-09-03 14:44";
    private RecordModel recordModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//
//        rg = (RadioGroup) findViewById(R.id.radiogroup);
//        rg.setOnCheckedChangeListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_record, menu);
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

   /* public void onSubmit(View view){
        //TODO save date
        String visitor =((EditText)this.findViewById(R.id.name)).getText().toString();
        String situation = ((EditText)this.findViewById(R.id.situation)).getText().toString();
        String interview = ((EditText)this.findViewById(R.id.interview)).getText().toString();

        Log.i("dd",visitor+ " "+situation+" "+ interview);

    }*/



   /* public void save(View view){
        Log.i("personinfo","in save");
        new AlertDialog.Builder(AddRecordActivity.this).setPositiveButton("保存成功",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(AddRecordActivity.this, PreviewActivity.class));
            }
        }).show();


    }*/

   @Event(value=R.id.back)
    private void onBackClick(View view){
       finish();
   }

    public boolean dispatchTouchEvent(MotionEvent event) {
        int Action = event.getAction();
        if (Action == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, event)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(event);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(event)) {
            return true;
        }
        return onTouchEvent(event);
    }


    public  boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = { 0, 0 };
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    @Event(value=R.id.avatar)
    private void onAvatarClick(View view) {
        startActivity(new Intent(this, PreviewPersonInfoActivity.class));
    }

    @Event(value=R.id.save)
    private void onSaveClick(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("确定提交")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getBaseContext(),"保存成功",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getBaseContext(),PreviewActivity.class);
                        recordModel = new RecordModel();
                        recordModel.setName(name.getText().toString());

                        if(addr.getText().toString() !=null && addr.getText().toString() != ""){
                            recordModel.setAddr(addr.getText().toString() == null ? "" : addr.getText().toString());
                        }

                        recordModel.setReason(reason.getText().toString() == null ? "" : reason.getText().toString());
                        if(gmtVisit.getText().toString()!= null){
                            recordModel.setGmtVisit(DateUtils.strToDate(gmtVisit.getText().toString()));
                        }

                        //TODO post

                        startActivity(intent);

                    }
                })
                .setNegativeButton("取消",null)
                .show();
//        startActivity(new Intent(this, PreviewPersonInfoActivity.class));
    }

    @Event(value = R.id.gmtVisit)
    private void SelectTimeClick(View view){
        date = new CustomDate();
        Toast.makeText(this,date.toString(),Toast.LENGTH_SHORT).show();
        DateTimePickDialogUtil dateTimePickDialog = new DateTimePickDialogUtil(
                AddRecordActivity.this,initStartDateTime);
        dateTimePickDialog.dateTimePicKDialog(gmtVisit);
    }

    @Event(value = R.id.addBtn)
    private void AddBtn(View view){

    }



    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

    }
}
