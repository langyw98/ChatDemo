package cn.yang.bmobchat.activity;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import cn.bmob.im.BmobChatManager;
import cn.bmob.im.bean.BmobChatUser;
import cn.bmob.im.bean.BmobMsg;
import cn.bmob.im.db.BmobDB;
import cn.yang.bmobchat.R;
import cn.yang.bmobchat.adapter.ChatAdapter;

public class ChatActivity extends Activity {

	private ListView lv_chat;
	private BmobChatUser targetUser;
	private ChatAdapter adapter;
	private EditText et_content;

	private BmobChatManager chatManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);

		targetUser = (BmobChatUser) getIntent().getSerializableExtra(
				"target_user");
		chatManager = BmobChatManager.getInstance(this);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		lv_chat = (ListView) findViewById(R.id.lv_chat);
		et_content = (EditText) findViewById(R.id.et_content);

		adapter = new ChatAdapter(this, BmobDB.create(this).queryMessages(
				targetUser.getObjectId(), 1));
		lv_chat.setAdapter(adapter);
	}

	public void BtnOnClick(View view) {
		switch (view.getId()) {
		case R.id.btn_send: {
			String strMsg = et_content.getText().toString();
			// 组装BmobMessage对象
			BmobMsg message = BmobMsg.createTextSendMsg(this,
					targetUser.getObjectId(), strMsg);
			// 默认发送完成，将数据保存到本地消息表和最近会话表中
			chatManager.sendTextMessage(targetUser, message);

			break;
		}
		}
	}
}
