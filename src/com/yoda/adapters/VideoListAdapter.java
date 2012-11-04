package com.yoda.adapters;

import java.io.File;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yoda.rec.R;
import com.yoda.utility.DIUtility;

public class VideoListAdapter extends BaseAdapter {
	private static File[] fileList;
	
	private LayoutInflater mInflater;

	public VideoListAdapter(Context context, File[] results) {
		fileList = results;
		mInflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return fileList.length;
	}

	public Object getItem(int position) {
		return fileList[position];
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		System.gc();
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.custom_row_view, null);
			holder = new ViewHolder();
			holder.txtName = (TextView) convertView.findViewById(R.id.name);
			holder.txtSize = (TextView) convertView.findViewById(R.id.size);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.txtName.setText(fileList[position].getName());

		holder.txtSize.setText("Size: "	+ DIUtility.humanReadableByteCount(1000000, true));

		return convertView;
	}

	static class ViewHolder {
		TextView txtName;
		TextView txtSize;
	}

}
