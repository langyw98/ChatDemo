package cn.yang.bmobchat.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cn.bmob.im.bean.BmobMsg;
import cn.yang.bmobchat.R;

public class ChatAdapter extends BaseAdapter {

	private Context context;
	private List<BmobMsg> msgList;
	public ChatAdapter(Context context, List<BmobMsg> msgList) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.msgList = msgList;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return msgList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return msgList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder;
		if(convertView == null){
			convertView = View.inflate(context, R.layout.item_friendlist, null);
			viewHolder = new ViewHolder();
			viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		viewHolder.tv_name.setText(msgList.get(position).getContent());
		
		return convertView;
	}

	private class ViewHolder{
		public TextView tv_name;
	}

	public void addMsg(BmobMsg msg){
		msgList.add(msg);
		notifyDataSetChanged();
	}
}
