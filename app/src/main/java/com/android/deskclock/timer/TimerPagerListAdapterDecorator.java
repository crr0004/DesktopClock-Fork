package com.android.deskclock.timer;

import android.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.android.deskclock.R;
import com.android.deskclock.data.Timer;
import com.android.deskclock.data.TimerListener;

/**
 * Decorates TimerPagerAdapter into a ListAdapter so it can work with GridViews
 * Created by crr00 on 14/10/17.
 */

public class TimerPagerListAdapterDecorator extends BaseAdapter implements TimerListener {

    private TimerPagerAdapter mTimerPagerAdapter;
    
    TimerPagerListAdapterDecorator(FragmentManager fragmentManager){
        mTimerPagerAdapter = new TimerPagerAdapter(fragmentManager);
        
    }
    
    @Override
    public int getCount() {
        return mTimerPagerAdapter.getCount();
    }

    @Override
    public Object getItem(int position) {
        return mTimerPagerAdapter.getTimer(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("timerdecorator", "" + mTimerPagerAdapter.getCount());
        final TimerItem view = (TimerItem) LayoutInflater.from(parent.getContext()).inflate(R.layout.timer_item, parent, false);
        Timer timer = mTimerPagerAdapter.getTimer(position);
        view.update(timer);

        return view;
    }

    @Override
    public void timerAdded(Timer timer) {
        mTimerPagerAdapter.timerAdded(timer);
    }

    @Override
    public void timerUpdated(Timer before, Timer after) {
        mTimerPagerAdapter.timerUpdated(before, after);
    }

    @Override
    public void timerRemoved(Timer timer) {
        mTimerPagerAdapter.timerRemoved(timer);
    }
}
