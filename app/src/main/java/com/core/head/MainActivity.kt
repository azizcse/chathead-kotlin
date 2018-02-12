package com.core.head

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.TextView
import com.core.head.view.HeadView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var helloTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        helloTextView = findViewById(R.id.hello_text)
        helloTextView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if(isOverlayAllow()) {
            HeadView.getInstance(this).showHead()
        }else{
            askPermission()
        }
    }

    private fun isOverlayAllow(): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true
        }
        return if (!Settings.canDrawOverlays(this)) {
            false
        } else true

    }

    private fun askPermission(){
        val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + packageName))
        startActivityForResult(intent, 100)
    }
}
