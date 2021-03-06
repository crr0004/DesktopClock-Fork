package com.android.deskclock.timer;

import android.app.FragmentManager;
import android.support.v7.widget.AppCompatTextView;
import android.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.android.deskclock.R;
import com.android.deskclock.TimerTextController;
import com.android.deskclock.data.Timer;
import com.android.deskclock.data.TimerListener;

import java.util.Map;

/**
 * Decorates TimerPagerAdapter into a ListAdapter so it can work with GridViews
 * Created by crr00 on 14/10/17.
 */

public class TimerPagerListAdapterDecorator extends BaseAdapter implements TimerListener {

    private TimerPagerAdapter mTimerPagerAdapter;
    private final Map<Integer, View> mViews = new ArrayMap<>();
    
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
        Log.d("timerdecorator", "Convertview status" + (convertView == null));
        View result = mViews.get(position);
        if(convertView != null){
            result = convertView;
        }
        if(result == null){
            result = LayoutInflater.from(parent.getContext()).inflate(R.layout.timer_item_griditem, parent, false);
            Timer timer = mTimerPagerAdapter.getTimer(position);
            AppCompatTextView text = result.findViewById(R.id.timer_griditem_editText);

            //view.update(timer);
            text.setText(TimerTextController.GetTimeString(timer.getRemainingTime(), parent.getContext()));
            mViews.put(position, result);
        }
        return result;
    }

    public boolean updateTime(){
        boolean result = false;
        for(int i : mViews.keySet()){
            Timer timer = mTimerPagerAdapter.getTimer(i);
            View view = mViews.get(i);
            AppCompatTextView text = view.findViewById(R.id.timer_griditem_editText);
            //view.update(timer);
            text.setText(TimerTextController.GetTimeString(timer.getRemainingTime(), view.getContext()));
        }
        return  result;
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
