package com.yyc.androiddemo.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yyc.androiddemo.R;
import com.yyc.androiddemo.activity.LocationListActivity;
import com.yyc.androiddemo.adapter.PersonAdapter.ViewHolder;
import com.yyc.androiddemo.bean.Location;
import com.yyc.androiddemo.bean.Person;
import com.yyc.androiddemo.util.HttpDownloaderUtil;

public class LocationAdapter extends BaseAdapter {

	private Context mContext;
	private List<Location> mList;
	
	public LocationAdapter(Context context, List<Location> list){
		this.mContext = context;
		this.mList = list;
	}
	
	@Override
	public int getCount() {
		return this.mList.size();
	}

	@Override
	public Object getItem(int position) {
		return this.mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.listitem_locationlist, null);
			holder = new ViewHolder();
			
			holder.locationTitle = (TextView) convertView.findViewById(R.id.textview_locationlist_listitem_title);
			holder.locationDescription = (TextView) convertView.findViewById(R.id.textview_locationlist_listitem_description);
			holder.locationPic =  (ImageView) convertView.findViewById(R.id.imageview_locationlist_listitem_pic);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		Location location = mList.get(position);
		//œ¬‘ÿÕº∆¨
		holder.locationTitle.setText("Name is : "+location.getTitle());
		holder.locationDescription.setText("Age is : "+location.getDescription());
		holder.locationPic.setImageResource(R.drawable.imageview_default);
		
		return convertView;
	}
	
	static class ViewHolder{
		TextView locationTitle;
		TextView locationDescription;
		ImageView locationPic;
	}

}
