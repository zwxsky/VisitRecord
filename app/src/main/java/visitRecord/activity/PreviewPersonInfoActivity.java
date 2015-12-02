package visitRecord.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

import demo.example.zwx.activity.R;
import visitRecord.Base.BaseActivity;

@ContentView(R.layout.activity_preview_person_info)
public class PreviewPersonInfoActivity extends BaseActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    @Event(value = R.id.mobile)
    private void onMobileClick(View view){
        new AlertDialog.Builder(PreviewPersonInfoActivity.this)
                .setTitle("点击拨打电话")
                .setPositiveButton("拨打电话", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String number = ((TextView) findViewById(R.id.mobile)).getText().toString();
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
                        startActivity(intent);
                    }
                })
                .setNegativeButton("取消",null)
                .show();
    }
}
