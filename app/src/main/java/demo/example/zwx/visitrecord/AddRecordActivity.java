package demo.example.zwx.visitrecord;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.EditText;
import android.widget.RadioGroup;

public class AddRecordActivity extends ActionBarActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);
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

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

//        switch(checkedId){
//            case R.id.radio1:
//                Log.i("tag:","旧地址");break;
//            case R.id.radio2:
//                Log.i("tag","新地址");break;
//
//        }

    }

    public void showPersonInfo(View view){
        startActivity(new Intent(this,PreviewPersonInfoActivity.class));
    }

    public void save(View view){
        Log.i("personinfo","in save");
        new AlertDialog.Builder(AddRecordActivity.this).setPositiveButton("保存成功",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(AddRecordActivity.this, PreviewActivity.class));
            }
        }).show();


    }

    public void cancal(View view){
        startActivity(new Intent(this,PreviewActivity.class));
    }

}
