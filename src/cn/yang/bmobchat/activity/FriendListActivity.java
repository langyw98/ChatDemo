package cn.yang.bmobchat.activity;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import cn.bmob.im.BmobUserManager;
import cn.bmob.im.bean.BmobChatUser;
import cn.bmob.im.bean.BmobInvitation;
import cn.bmob.im.bean.BmobMsg;
import cn.bmob.im.db.BmobDB;
import cn.bmob.im.inteface.EventListener;
import cn.yang.bmobchat.MessageBroadcastReceiver;
import cn.yang.bmobchat.R;
import cn.yang.bmobchat.adapter.FriendListAdapter;

public class FriendListActivity extends Activity implements EventListener,OnItemClickListener {

	private ListView lv_friend; 
	private List<BmobChatUser> friends;
	private FriendListAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_friendlist);

		BmobUserManager buManager = BmobUserManager.getInstance(this);

		MessageBroadcastReceiver.eventListener = this;
		init();
	}


	private void init() {
		// TODO Auto-generated method stub
		lv_friend = (ListView) findViewById(R.id.listview);
		friends = BmobDB.create(this).getContactList();
		adapter = new FriendListAdapter(this, friends);
		lv_friend.setAdapter(adapter);
		lv_friend.setOnItemClickListener(this);
	}


	@Override
	public void onAddUser(BmobInvitation arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMessage(BmobMsg arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onNetChange(boolean arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onOffline() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onReaded(String arg0, String arg1) {
		// TODO Auto-generated method stub

	}


	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		// TODO Auto-generated method stub
		BmobChatUser bcUser = (BmobChatUser) adapter.getItem(position);
		Intent intent = new Intent(this,ChatActivity.class);
		intent.putExtra("target_user", bcUser);
		startActivity(intent);
	}
}
