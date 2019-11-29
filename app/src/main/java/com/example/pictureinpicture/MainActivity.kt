package com.example.pictureinpicture

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.PendingIntent
import android.app.PictureInPictureParams
import android.app.RemoteAction
import android.content.Intent
import android.graphics.drawable.Icon
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Rational
import android.widget.MediaController
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val aspectRatio:Rational= Rational(3,4)
    var params:PictureInPictureParams?=null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val videoPath=Environment.getExternalStorageDirectory().absolutePath+"/asd.mp4"

//        val mediaController:MediaController= MediaController(this)
        video_view.setVideoPath(videoPath)
//        mediaController.setAnchorView(video_view)
//        video_view.setMediaController(mediaController)
        video_view.start()
        params=PictureInPictureParams.Builder().setAspectRatio(aspectRatio)
            .setActions(getPIPActions()).build()

    }

    @SuppressLint("NewApi")
    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onPause() {
        super.onPause()
        this.enterPictureInPictureMode(params!!)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getPIPActions():ArrayList<RemoteAction> {

        val intent=Intent(this,PIPActionsReceiver::class.java)
        intent.action="PIP_ACTION_MUTE"

        val pendingIntent=PendingIntent.getBroadcast(this,123,
            intent,PendingIntent.FLAG_ONE_SHOT)

        val icon: Icon= Icon.createWithResource(this,R.drawable.add)

        val actions:ArrayList<RemoteAction> = ArrayList()
        actions.add(RemoteAction(icon,"Aman","Choudhary",pendingIntent))

        return actions
    }

}
