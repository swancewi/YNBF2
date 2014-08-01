package com.example.test676;

import java.util.ArrayList;

import com.example.test676.R;
import com.example.test676.classes.RealEstateAgent;
import com.example.test676.constants.Constants;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {
	
	private Activity activity;
	private ArrayList<RealEstateAgent> rea;
	private static LayoutInflater inflater = null;
	private static DisplayImageOptions options;
	private static ImageLoader imageLoader = ImageLoader.getInstance();
	
	public ListAdapter(Activity activity, ArrayList<RealEstateAgent> rea) {
		this.activity = activity;
		this.rea = rea;
		
		if(imageLoader.isInited()) 
			imageLoader.destroy();
		imageLoader.init(ImageLoaderConfiguration.createDefault(activity));
		inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		options = new DisplayImageOptions.Builder()
		.showImageForEmptyUri(R.drawable.ic_launcher)
		.showImageOnFail(R.drawable.ic_launcher)
		.cacheInMemory(true)
		.cacheOnDisk(true)
		.considerExifParams(true)
		.build();
	}
	
	@Override
	public int getCount() {
		return Math.max(this.rea.size(),1);
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	private class ViewHolder{
		public TextView textViewName;
		public TextView textViewNumber;
		public ImageView imagePhoto;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View rowView = convertView;
		ViewHolder holder = new ViewHolder();
		
		if(rowView==null) {
			rowView= inflater.inflate(R.layout.rea_list_view, null);
			holder.textViewName = (TextView) rowView.findViewById(R.id.nameText);
			holder.textViewNumber = (TextView) rowView.findViewById(R.id.phoneNumberText);
			holder.imagePhoto = (ImageView) rowView.findViewById(R.id.iconREA);
			 
			rowView.setTag(holder);
		}
		else {
			holder = (ViewHolder) rowView.getTag();
		}
		if(rea.size()<=0) {
			holder.textViewName.setText("No Agents Found");
		}
		else {
			RealEstateAgent tempREA = new RealEstateAgent();
			tempREA = (RealEstateAgent) rea.get(position);
			
			Log.v(this.toString(), "Name: " + tempREA.getName());
			
			holder.textViewName.setText(tempREA.getName());
			holder.textViewNumber.setText(tempREA.getPhoneNumber());
			imageLoader.displayImage(tempREA.getPhotoURLwithSize(Constants.SMALL_WIDTH), holder.imagePhoto, options, null); 
			
			rowView.setOnClickListener(new OnItemClickListener(position));
		}
		return rowView;
	}
	
	/**Click listener to start process to launch activity to display one agent
	 * 
	 * @author Steven Wance
	 *
	 */
	private class OnItemClickListener implements OnClickListener {

		private int clickPosition;
		
		OnItemClickListener(int position) {
			clickPosition = position;
		}
		@Override
		public void onClick(View v) {
			LaunchOneWindow(clickPosition);
		}
	}
	
	/**Launches activity to display one agent
	 * 
	 * @param clickPosition
	 */
	private void LaunchOneWindow(int clickPosition) {
		RealEstateAgent agent = rea.get(clickPosition);
		
		//TODO make realestateagent parcelable
		Intent i = new Intent(activity, OneAgentActivity.class);
		i.putExtra(Constants.AGENT_ID, agent.getId());
		i.putExtra(Constants.AGENT_NAME, agent.getName());
		i.putExtra(Constants.AGENT_PHONE_NUMBER, agent.getPhoneNumber());
		i.putExtra(Constants.AGENT_PHOTO_URL, agent.getPhoto());
		i.putExtra(Constants.AGENT_REBRAND, agent.getRebrand());
		i.putExtra(Constants.AGENT_IS_TEAM, agent.getIsTeam());
		
		if (!(agent.getIsTeam()))
			i.putExtra(Constants.AGENT_OFFICE, agent.getOffice());

		activity.startActivity(i);
	}

}