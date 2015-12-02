package visitRecord.Base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.internal.widget.AppCompatPopupWindow;

import org.xutils.x;

/**
 * Created by luo on 15-11-27.
 */
public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }
}
