package cn.yang.bmobchat;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import cn.bmob.im.BmobChatManager;
import cn.bmob.im.bean.BmobMsg;
import cn.bmob.im.config.BmobConfig;
import cn.bmob.im.config.BmobConstant;
import cn.bmob.im.inteface.EventListener;
import cn.bmob.im.inteface.OnReceiveListener;
import cn.bmob.im.util.BmobJsonUtil;
import cn.yang.bmobchat.config.Config;

public class MessageBroadcastReceiver extends BroadcastReceiver {

	public static EventListener eventListener = null;
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String json = intent.getStringExtra("msg");
		Log.i(Config.Tag,"message is " + json);
		Toast.makeText(context, "message is " + json, Toast.LENGTH_LONG).show();
		BmobMsg msg = new BmobMsg();
		msg.setContent(json);
		eventListener.onMessage(msg);
		parseMessage(context, json);
	}

	private void parseMessage(final Context context, String json){
		JSONObject jo;
		try {
			jo = new JSONObject(json);
			String Tag = BmobJsonUtil.getString(jo, BmobConstant.PUSH_KEY_TAG);
			if(TextUtils.isEmpty(Tag)){
				BmobChatManager.getInstance(context).createReceiveMsg(json, new OnReceiveListener() {
					
					@Override
					public void onSuccess(BmobMsg msg) {
						// TODO Auto-generated method stub
						
						Toast.makeText(context, msg.getContent(), Toast.LENGTH_LONG).show();
//						Log.i(Config.Tag, msg.getContent());
					}
					
					@Override
					public void onFailure(int arg0, String arg1) {
						// TODO Auto-generated method stub
						
					}
				});
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
