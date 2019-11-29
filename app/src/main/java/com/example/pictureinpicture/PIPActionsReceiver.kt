package com.example.pictureinpicture

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class PIPActionsReceiver : BroadcastReceiver() {

    override fun onReceive(p0: Context?, p1: Intent?) {
        if(p1==null || !p1.action.equals("PIP_ACTION_MUTE")){
            return
        }
        //do other thing
    }
}