package demo.example.zwx.visitrecord;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by luo on 15-10-30.
 */
public class ListDisplay extends Activity {

    String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry","WebOS","Ubuntu","Windows7","Max OS X"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_record);

        ArrayAdapter visitorarr = new ArrayAdapter<String>(this,R.layout.activity_listview,mobileArray);
        ListView visitor = (ListView) findViewById(R.id.visitor);
        visitor.setAdapter(visitorarr);

    }
}
