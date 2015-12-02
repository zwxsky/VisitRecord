package visitRecord.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

import java.util.List;
import java.util.zip.Inflater;

import demo.example.zwx.activity.R;
import visitRecord.Adapter.UserAdapter;
import visitRecord.Base.BaseActivity;
import visitRecord.model.User;

@ContentView(R.layout.activity_visit_detail_edit)
public class VisitDetailEditActivity extends BaseActivity {

    private List<User> ulist;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_visit_detail_edit);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_visit_detail_edit, menu);
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

    @Event(value=R.id.back)
    private void onBackClick(View view){
        finish();
    }

    @Event(value=R.id.avatar)
    private void onAvatarClick(View view) {
        startActivity(new Intent(this, PreviewPersonInfoActivity.class));
    }

    @Event(value=R.id.submit)
    private void onSubmitClick(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("操作成功")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getBaseContext(), DeliveryDemandActivity.class));
                    }
                }).show();


    }

    @Event(value = R.id.addBtn)
    private void onAddBtnClick(View view){
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("请选择")
                .setItems(new String[]{"zhuwx", "xuweiwe", "wangfl"},
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //TODO 将人加入探访成员
                                LayoutInflater inflater = getLayoutInflater();
                                inflater.inflate(R.layout.avatar,null);
                                dialog.dismiss();
                            }
                        })
                .setNegativeButton("取消", null)
                .show();

        //startActivity(new Intent(this));
    }

    private List<User> getUser(){
        //TODO 获取探访者列表
        for(int i=0;i<3;i++){
            User user = new User();
            user.setName("zhuwx"+i);
            ulist.add(user);
        }

        return ulist;
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




}
