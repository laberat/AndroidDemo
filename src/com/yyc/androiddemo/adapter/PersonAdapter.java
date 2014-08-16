package com.yyc.androiddemo.adapter;

import java.util.List;

import com.yyc.androiddemo.R;
import com.yyc.androiddemo.bean.Person;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PersonAdapter extends BaseAdapter {
	
	private Context mContext;
	private List<Person> mList;

	public PersonAdapter(Context context, List<Person> list){
		this.mContext = context;
		this.mList = list;
	}
	
	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.listitem_personlist, null);
			holder = new ViewHolder();
			
			holder.personName = (TextView) convertView.findViewById(R.id.textview_personlist_listitem_name);
			holder.personAge = (TextView) convertView.findViewById(R.id.textview_personlist_listitem_age);
			holder.personSex = (TextView) convertView.findViewById(R.id.textview_personlist_listitem_sex);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		Person person = mList.get(position);
		holder.personName.setText("Name is : "+person.getName());
		holder.personAge.setText("Age is : "+person.getAge());
		holder.personSex.setText("Sex is : "+person.getSex());
		
		return convertView;
	}
	
	static class ViewHolder{
		TextView personName;
		TextView personAge;
		TextView personSex;
	}
	
	

}
