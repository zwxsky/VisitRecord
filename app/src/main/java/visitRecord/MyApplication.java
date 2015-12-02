package visitRecord;

import android.app.Application;

import org.xutils.x;

import demo.example.zwx.activity.BuildConfig;

/**
 * Created by luo on 15-11-26.
 */
public class MyApplication extends Application{
    @Override
    public void onCreate(){
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
}
