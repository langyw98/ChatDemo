package cn.yang.bmobchat.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import cn.bmob.im.BmobChat;
import cn.bmob.im.BmobUserManager;
import cn.bmob.im.bean.BmobChatUser;
import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.listener.SaveListener;
import cn.yang.bmobchat.R;
import cn.yang.bmobchat.config.Config;

public class LoginActivity extends Activity {

	private EditText et_name;
	private EditText et_pwd;

	class Loginfo {
		public String name;
		public String pwd;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		init();
		BmobChat.getInstance(this).init(Config.applicationId);
	}

	private void init() {
		et_name = (EditText) findViewById(R.id.et_name);
		et_pwd = (EditText) findViewById(R.id.et_pwd);
	}

	private Loginfo getLoginfo() {
		Loginfo loginfo = null;
		loginfo = new Loginfo();
		loginfo.name = et_name.getText().toString();
		loginfo.pwd = et_pwd.getText().toString();
		return loginfo;
	}

	public void BtnOnClick(View view) {
		switch (view.getId()) {
		case R.id.btn_reg: {
			Loginfo loginfo = getLoginfo();
			final BmobChatUser bu = new BmobChatUser();
			bu.setUsername(loginfo.name);
			bu.setPassword(loginfo.pwd);
			//将user和设备id进行绑定
			bu.setDeviceType("android");
			bu.setInstallId(BmobInstallation.getInstallationId(this));
			bu.signUp(this, new SaveListener() {

				@Override
				public void onSuccess() {
					// TODO Auto-generated method stub
					// Toast.makeText(LoginActivity.this, "reg success",
					// Toast.LENGTH_LONG).show();
					BmobUserManager userManager = BmobUserManager.getInstance(LoginActivity.this);
					userManager.bindInstallationForRegister(bu.getUsername());
					Intent intent = new Intent(LoginActivity.this,
							FriendListActivity.class);
					startActivity(intent);
					finish();
				}

				@Override
				public void onFailure(int arg0, String arg1) {
					// TODO Auto-generated method stub
					Toast.makeText(LoginActivity.this, "reg fail",
							Toast.LENGTH_LONG).show();
				}
			});
			break;
		}
		case R.id.btn_login: {
			Loginfo loginfo = getLoginfo();
			final BmobChatUser bu = new BmobChatUser();
			bu.setUsername(loginfo.name);
			bu.setPassword(loginfo.pwd);
			bu.login(this, new SaveListener() {

				@Override
				public void onFailure(int arg0, String arg1) {
					// TODO Auto-generated method stub
					Toast.makeText(LoginActivity.this, "login fail",
							Toast.LENGTH_LONG).show();
				}

				@Override
				public void onSuccess() {
					// TODO Auto-generated method stub
					BmobUserManager userManager = BmobUserManager.getInstance(LoginActivity.this);
					userManager.bindInstallationForRegister(bu.getUsername());
					Intent intent = new Intent(LoginActivity.this,
							FriendListActivity.class);
					startActivity(intent);
					finish();
				}

			});
			break;
		}
		}
	}
}
