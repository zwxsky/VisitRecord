package visitRecord.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import demo.example.zwx.activity.R;

/**
 * Created by luo on 15-10-30.
 */
public class ListDisplay extends Activity {

    String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry","WebOS","Ubuntu","Windows7","Max OS X"};
    String[] visitorArr = {"xuweiwei","zhuwx","wangfl"};
    String[] intervieweeArr ={"张三","里斯","王五"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_record);

        /*TextView visit = new TextView(this);
        visit.setText("zhangsan");
        TextView interview = new TextView(this);
        interview.setText("xuweiwei");
        TextView situation = new TextView(this);
        situation.setText("things are to much");
        TextView date = new TextView(this);
        date.setText("2015-9-10");

        ((LinearLayout)findViewById(R.id.label)).addView(visit);
        ((LinearLayout)findViewById(R.id.label)).addView(interview);
        ((LinearLayout)findViewById(R.id.label)).addView(situation);
        ((LinearLayout)findViewById(R.id.label)).addView(date);


        ArrayAdapter visitors = new ArrayAdapter<String>(this,R.layout.activity_listview,visitorArr);
        ListView visitor = (ListView) findViewById(R.id.visitor);
        visitor.setAdapter(visitors);*/

        ListView listView = (ListView)findViewById(R.id.list);

        List<User> uList = new ArrayList<User>();
        uList.add(new User("wangfl","xiaozhi ","to much things","2015-9-1"));
        uList.add(new User("wangfl","daxu ","to weak","2015-9-1"));
        uList.add(new User("wangfl","zhuwx ","to young too simple","2015-9-10"));

        UserAdapter adapter = new UserAdapter(this,R.layout.activity_listview);
        adapter.addAll(uList);
        listView.setAdapter(adapter);

       /* ArrayAdapter interviewees = new ArrayAdapter(this,R.layout.activity_listview,intervieweeArr);
        ListView interviewee = (ListView) findViewById(R.id.interviewee);
        interviewee.setAdapter(interviewees);*/

    }

    class UserAdapter extends  ArrayAdapter<User>{
        private int mResourceId;

        public UserAdapter(Context context, int textViewResourceId){
            super(context,textViewResourceId);
            this.mResourceId = textViewResourceId;
        }

        @Override
        public  View getView(int position, View convertView, ViewGroup parent){
            User user= getItem(position);
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(mResourceId, null);
            TextView visitor = (TextView) view.findViewById(R.id.visitor);
            TextView interview = (TextView) view.findViewById(R.id.interview);
            TextView situation = (TextView) view.findViewById(R.id.situation);
            TextView date = (TextView) view.findViewById(R.id.date);

            visitor.setText(user.getVisitor());
            interview.setText(user.getInterview());
            situation.setText(user.getSituation());
            date.setText(user.getDate());

            return view;

        }
    }

    class User {
        private String visitor;
        private String interview;
        private String situation;
        private String date;

        public User(String  visitor, String interview, String situation,String date) {
            this.visitor = visitor;
            this.interview = interview;
            this.situation = situation;
            this.date = date;
        }

        public void setVisitor(String visitor){
            this.visitor=visitor;
        }
        public String getVisitor(){
            return visitor;
        }

        public void setInterview(String interview){
            this.interview=interview;
        }
        public String getInterview(){
            return interview;
        }

        public void setSituation(String situation){
            this.situation=situation;
        }
        public String getSituation(){
            return situation;
        }

        public void setDate(String date){
            this.date=date;
        }
        public String getDate(){
            return date;
        }


    }

    public void addRecord(View view){

        startActivity(new Intent(this, AddRecordActivity.class));
    }
}
