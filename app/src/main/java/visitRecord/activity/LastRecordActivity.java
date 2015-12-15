package visitRecord.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import demo.example.zwx.activity.R;
import visitRecord.Base.BaseActivity;
import visitRecord.Base.MineActivity;
import visitRecord.model.CustomDate;

@ContentView(R.layout.activity_last_record)
public class LastRecordActivity extends MineActivity {


    private CustomDate customDate;
    @ViewInject(R.id.tvCurrentMonth)
    TextView tvCurrentMonth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_last_record, menu);
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

    @Event(value = R.id.back)
    private void OnBackClick(View view){
        finish();
    }


    @Event(value = R.id.btnPreMonth)
    private void onPreBtnClick(View view){

        CustomDate custom = new CustomDate(customDate.getYear(),customDate.getMonth()-1,customDate.getDay());
        customDate = custom;
        tvCurrentMonth.setText(custom.toYearMonth());
        //TODO 根据日期查询

    }

    @Event(value = R.id.btnNextMonth)
    private void onNextBtnClick(View view){

        CustomDate custom = new CustomDate(customDate.getYear(),customDate.getMonth()+1,customDate.getDay());
        customDate = custom;
        tvCurrentMonth.setText(custom.toYearMonth());
        //TODO 根据日期查询
    }

}
