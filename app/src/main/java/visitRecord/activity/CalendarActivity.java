package visitRecord.activity;

import android.content.Intent;
import android.os.Bundle;

import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import demo.example.zwx.activity.R;
import visitRecord.adapter.CalendarViewAdapter;
import visitRecord.Base.BaseActivity;
import visitRecord.model.CustomDate;
import visitRecord.view.CalendarCard;


public class CalendarActivity extends BaseActivity implements OnClickListener, CalendarCard.OnCellClickListener {
    private ViewPager mViewPager;
    private int mCurrentIndex = 498;
    private CalendarCard[] mShowViews;
    private CalendarViewAdapter<CalendarCard> adapter;
    private SildeDirection mDirection = SildeDirection.NO_SILDE;
    enum SildeDirection {
        RIGHT, LEFT, NO_SILDE;
    }

    private ImageButton preImgBtn, nextImgBtn;
    private TextView monthText;
    private ImageButton closeImgBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_calendar);
        mViewPager = (ViewPager) this.findViewById(R.id.vp_calendar);
        preImgBtn = (ImageButton) this.findViewById(R.id.btnPreMonth);
        nextImgBtn = (ImageButton) this.findViewById(R.id.btnNextMonth);
        monthText = (TextView) this.findViewById(R.id.tvCurrentMonth);
        closeImgBtn = (ImageButton) this.findViewById(R.id.btnClose);
        preImgBtn.setOnClickListener(this);
        nextImgBtn.setOnClickListener(this);
        closeImgBtn.setOnClickListener(this);

        CalendarCard[] views = new CalendarCard[3];
        for (int i = 0; i < 3; i++) {
            views[i] = new CalendarCard(this, this);
        }
        adapter = new CalendarViewAdapter<>(views);
        setViewPager();

    }

    private void setViewPager() {
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(498);
        mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                measureDirection(position);
                updateCalendarView(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPreMonth:
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
                break;
            case R.id.btnNextMonth:
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
                break;
            case R.id.btnClose:
                Intent intent = getIntent();
                setResult(-1,intent);
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void clickDate(CustomDate date) {
        Intent intent = getIntent();
        intent.putExtra("date",date.toString());
        setResult(0,intent);
        finish();
        Toast.makeText(this,date.toString(),Toast.LENGTH_SHORT).show();
    }


    @Override
    public void changeDate(CustomDate date) {
        monthText.setText(date.month + "月");
    }

    /**
     * 计算方向
     *
     * @param arg0
     */
    private void measureDirection(int arg0) {

        if (arg0 > mCurrentIndex) {
            mDirection = SildeDirection.RIGHT;

        } else if (arg0 < mCurrentIndex) {
            mDirection = SildeDirection.LEFT;
        }
        mCurrentIndex = arg0;
    }

    // 更新日历视图
    private void updateCalendarView(int arg0) {
        mShowViews = adapter.getAllItems();
        if (mDirection == SildeDirection.RIGHT) {
            mShowViews[arg0 % mShowViews.length].rightSlide();
        } else if (mDirection == SildeDirection.LEFT) {
            mShowViews[arg0 % mShowViews.length].leftSlide();
        }
        mDirection = SildeDirection.NO_SILDE;
    }



}
