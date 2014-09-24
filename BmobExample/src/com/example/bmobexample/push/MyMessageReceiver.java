package com.example.bmobexample.push;

import com.example.bmobexample.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import cn.bmob.push.PushConstants;

public class MyMessageReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		// 获取推送消息
		String message = intent.getStringExtra(PushConstants.EXTRA_PUSH_MESSAGE_STRING);
		// 发送通知
		NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		
		Notification n = new Notification();  
        n.icon = R.drawable.ic_launcher;  
        n.tickerText = "BmobExample收到消息推送";  
        n.when = System.currentTimeMillis();  
        //n.flags=Notification.FLAG_ONGOING_EVENT;  
        Intent i = new Intent();  
        PendingIntent pi = PendingIntent.getActivity(context, 0, i, 0);  
        n.setLatestEventInfo(context, "消息", message, pi);  
        n.defaults |= Notification.DEFAULT_SOUND;
        n.flags = Notification.FLAG_AUTO_CANCEL;
        nm.notify(1, n);
	}

}
