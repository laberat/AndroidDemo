package com.yyc.androiddemo.adapter;

import java.util.Collections;
import java.util.List;

import com.yyc.androiddemo.R;
import com.yyc.androiddemo.bean.TimerCount;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TimerCountAdapter extends BaseAdapter {

	private Context mContext;
	private List<TimerCount> mList;
	
	public TimerCountAdapter(Context mContext, List<TimerCount> mList) {
		super();
		this.mContext = mContext;
		this.mList = mList;
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int positon) {
		return mList.get(positon);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.listitem_stopwatch, null);

			holder.count = (TextView) convertView
					.findViewById(R.id.textview_stopwatch_listitem_count);
			holder.timerMin = (TextView) convertView
					.findViewById(R.id.textview_stopwatch_listitem_minute);
			holder.timerSec = (TextView) convertView
					.findViewById(R.id.textview_stopwatch_listitem_second);
			holder.timerCentiSec = (TextView) convertView
					.findViewById(R.id.textview_stopwatch_listitem_centisecond);
			holder.timerDiffer = (TextView) convertView
					.findViewById(R.id.textview_stopwatch_listitem_passtime);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		TimerCount timerCount = mList.get(position);

		holder.count.setText("第 "+timerCount.getCount()+" 计次");
		holder.timerMin.setText(""+timerCount.getTimerMin());//int 转为 string
		holder.timerSec.setText(""+timerCount.getTimerSec());
		holder.timerCentiSec.setText(""+timerCount.getTimerCentiSec());
		holder.timerDiffer.setText("+"+timerCount.getTimerDiffer()+" s/100");

		return convertView;
	}

	static class ViewHolder {
		TextView count;
		TextView timerMin;
		TextView timerSec;
		TextView timerCentiSec;
		TextView timerDiffer;
	}
	
	public void insert(TimerCount timerCount){
		this.mList.add(0, timerCount);
		this.notifyDataSetChanged();
	}

}
