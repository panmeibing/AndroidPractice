package com.ice.androidpractice19_normalbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AutoBootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"APP开机自启",Toast.LENGTH_SHORT).show();
        Intent bootIntent = new Intent(context,MainActivity.class);
        bootIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(bootIntent);
    }
}
