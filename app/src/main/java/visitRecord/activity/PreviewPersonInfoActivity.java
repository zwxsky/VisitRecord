package visitRecord.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import demo.example.zwx.activity.R;
import visitRecord.Base.BaseActivity;
import visitRecord.Base.MineActivity;
import visitRecord.http.HttpUtil;
import visitRecord.http.UserParams;
import visitRecord.model.User;

@ContentView(R.layout.activity_preview_person_info)
public class PreviewPersonInfoActivity extends BaseActivity {

    @ViewInject(R.id.group_title)
    TextView title;
    @ViewInject(R.id.edit)
    TextView edit;
    User user = new User();
    @ViewInject(R.id.avatar)
    TextView avatar;
    @ViewInject(R.id.avatar_name)
    TextView avatarName;
    @ViewInject(R.id.name)
    TextView name;
    @ViewInject(R.id.phone)
    TextView phone;
    @ViewInject(R.id.addr)
    TextView addr;
    @ViewInject(R.id.age)
    TextView age;
    @ViewInject(R.id.type)
    Spinner type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Intent  intent = getIntent();

        title.setText("个人信息");
        //TODO 根据uid获取用户信息
        Long uid = intent.getLongExtra("uid",0);
        if(uid == MineActivity.UID){
            edit.setVisibility(View.VISIBLE);
        }
        getData(uid);

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

    @Event(value = R.id.phone)
    private void onMobileClick(View view){
        new AlertDialog.Builder(PreviewPersonInfoActivity.this)
                .setTitle("点击拨打电话")
                .setPositiveButton("拨打电话", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String number = ((TextView) findViewById(R.id.phone)).getText().toString();
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
                        startActivity(intent);
                    }
                })
                .setNegativeButton("取消",null)
                .show();
    }

    @Event(value = R.id.edit)
    private void editClick(View view){
        startActivity(new Intent(getBaseContext(), PersonInfoActivity.class));

    }

    private User getData(Long uid){
        // 后来才知道，选择addQueryStringParameter还是addBodyParameter是根据服务器那边是否有url param来定的。
        RequestParams params = new RequestParams(HttpUtil.USER_INFO+uid);


        Callback.Cancelable cancelable = x.http().get(params,
                new Callback.CommonCallback<String>(){

                    @Override
                    public void onSuccess(String s) {

                        Gson gson = new Gson();
                        user = gson.fromJson(s,User.class);
                        if(user!= null){
                            avatar.setText(user.getName().substring(0, 1));
                            avatarName.setText(user.getName());

                            name.setText(user.getName());
                            phone.setText(user.getPhone());
                            addr.setText(user.getAddr());
                            age.setText(user.getAge()+"岁");
                            type.setTag(user.getType());

                        }

                    }

                    @Override
                    public void onError(Throwable throwable, boolean b) {

                    }

                    @Override
                    public void onCancelled(CancelledException e) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });

        return user;
    }


}
