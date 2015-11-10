package demo.example.zwx.visitrecord;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {

    public EditText editText;
    public EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        EditText user =  (EditText)findViewById(R.id.username);
        String username = user.getText().toString();

        EditText pwd =  (EditText)findViewById(R.id.password);
        String password = pwd.getText().toString();

        if( username.equals("root")  && password.equals("111222")){
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

        }



    }
}
