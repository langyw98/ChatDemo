package cn.yang.bmobchat.activity;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import cn.bmob.im.BmobChatManager;
import cn.bmob.im.BmobUserManager;
import cn.bmob.im.bean.BmobChatUser;
import cn.bmob.im.bean.BmobInvitation;
import cn.bmob.im.bean.BmobMsg;
import cn.bmob.im.inteface.EventListener;
import cn.bmob.v3.listener.FindListener;
import cn.yang.bmobchat.MessageBroadcastReceiver;
import cn.yang.bmobchat.R;

public class FriendListActivity extends Activity implements EventListener{
	private String MsgCotent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_friendlist);
		
		BmobUserManager buManager = BmobUserManager.getInstance(this);
		setTitle(buManager.getCurrentUser().getUsername());
		
		MessageBroadcastReceiver.eventListener = this;
	}
	
	public void BtnOnClick(View view){
		switch(view.getId())
		Toast.makeText(this, "test", Toast.LENGTH_LONG).show();
		BmobUserManager buManager = BmobUserManager.getInstance(this);
//		BmobChatUser curUser = buManager.getCurrentUser();
		BmobChatUser targetUser = null;
		buManager.queryUserById("88868bdf35", new FindListener<BmobChatUser>() {

			@Override
			public void onError(int arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(List<BmobChatUser> arg0) {
				// TODO Auto-generated method stub
				BmobMsg msg = BmobMsg.createTextSendMsg(FriendListActivity.this, "88868bdf35", "Test send msg to 123");
				BmobChatManager.getInstance(FriendListActivity.this).sendTextMessage(arg0.get(0), msg);
			}
		});

	}

	@Override
	public void onAddUser(BmobInvitation arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMessage(BmobMsg arg0) {
		// TODO Auto-generated method stub
		MsgCotent = arg0.getContent();
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
}
