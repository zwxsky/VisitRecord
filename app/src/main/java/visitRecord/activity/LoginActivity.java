package visitRecord.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import demo.example.zwx.activity.R;
import visitRecord.Base.BaseActivity;
import visitRecord.Base.MineActivity;
import visitRecord.http.HttpUtil;
import visitRecord.model.User;

public class LoginActivity extends BaseActivity {

    @ViewInject(R.id.username)
    EditText username;
    @ViewInject(R.id.password)
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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

    @Event(value=R.id.loginBtn)
    private void loginClick(View view){

        //TODO login 接口实现
        String url = HttpUtil.LOGIN + "?name=" + username.getText() + "&pwd=" + password.getText();
                // "&lng=1&lat=2";
//                "&lng=" + BMapApiApp.getInstance().location.getLongitude() + "&lat=" + BMapApiApp.getInstance().location.getLatitude();

//        HttpUtils httpUtils = HttpUtil.getHttpRequest(this);
//        httpUtils.send(HttpRequest.HttpMethod.GET, url, new MyHttpRequestCallBack<User>(User.class, this, "正在登录...") {

        if(username.getText().toString().trim().equals("")){
            toast("请输入用户名");
            return;
        }
        if(password.getText().toString().trim().equals("")){
            toast("请输入密码");
            return;
        }

        MineActivity.UID = 1001l;






    }
}
