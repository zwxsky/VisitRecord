package visitRecord.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.xutils.view.annotation.ContentView;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import demo.example.zwx.activity.R;
import visitRecord.Base.BaseActivity;
import visitRecord.model.User;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    public EditText editText;
    public EditText password;
    public HttpPost httpPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void login(View view){
//        EditText account =  (EditText)findViewById(R.id.username);
//        String username = account.getText().toString();
//
//        EditText pwd =  (EditText)findViewById(R.id.password);
//        String password = pwd.getText().toString();
//        User user = new User();
//        user.setName(username);
//        user.setPwd(password);
        startActivity(new Intent(this, PreviewActivity.class));

        //verifyAccout(user);

        /*if( username.equals("root")  && password.equals("111222")){
            startActivity(new Intent(this, PreviewActivity.class));
        }else{
            new AlertDialog.Builder(this).setPositiveButton("用户名或密码错误",new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    EditText user =  (EditText)findViewById(R.id.username);
                    EditText pwd =  (EditText)findViewById(R.id.password);
                    user.setText("");
                    pwd.setText("");
                }
            }).show();
            }*/
    }

    public Boolean verifyAccout(User user){
        String uri ="";
        httpPost = new HttpPost(uri);
        HttpResponse httpResponse;
        try {
            //设置httpPost请求参数
            String url="http://192.168.1.108:8080/login";
            httpPost = new HttpPost(url);
            List <NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("name",user.getName().toString()));
            params.add(new BasicNameValuePair("pwd",user.getPwd().toString()));
            httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            httpResponse = new DefaultHttpClient().execute(httpPost);
            String result= EntityUtils.toString(httpResponse.getEntity());
            Toast.makeText(MainActivity.this, "result:" + result,Toast.LENGTH_SHORT).show();
            if(httpResponse.getStatusLine().getStatusCode() == 200){
                User user1 = (User) httpResponse.getEntity();
                startActivity(new Intent(getBaseContext(),PreviewActivity.class));
                return true;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
