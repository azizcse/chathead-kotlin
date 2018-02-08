package com.core.head.view

import android.content.Context
import android.graphics.PixelFormat
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import com.core.head.R
import com.core.head.util.Util
import com.facebook.drawee.view.SimpleDraweeView
import java.util.*


/*
*  ****************************************************************************
*  * Created by : Md. Azizul Islam on 2/8/2018 at 5:56 PM.
*  * Email : azizul@w3engineers.com
*  * 
*  * Last edited by : Md. Azizul Islam on 2/8/2018.
*  * 
*  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>  
*  ****************************************************************************
*/

class HeadView{
    private var layoutParam : WindowManager.LayoutParams
    private var mContext : Context
    private val mWindowManager: WindowManager
    private val mDownloadView: View
    private val downloadHeadRecycler: RecyclerView?
    private val thumbImageView: SimpleDraweeView
    private val downloadCount: TextView
    private val downloadCountView: LinearLayout


    private constructor(context: Context){
        this.mContext = context
        mDownloadView = LayoutInflater.from(mContext).inflate(R.layout.layout_download_head, null)
        downloadHeadRecycler = mDownloadView.findViewById(R.id.chatHeadRecyclverView) as RecyclerView

        layoutParam = WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT)

        layoutParam.gravity = Gravity.TOP or Gravity.LEFT

        layoutParam.x = Util.getDeviceWidth(mContext)
        layoutParam.y = Util.getDeviceHeight(mContext) / 3

        mWindowManager = mContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        mWindowManager.addView(mDownloadView, layoutParam)

        thumbImageView = mDownloadView.findViewById(R.id.thumb_img_view) as SimpleDraweeView
        downloadCount = mDownloadView.findViewById(R.id.count) as TextView
        downloadCountView = mDownloadView.findViewById(R.id.download_count_view)
        mDownloadView.visibility = View.GONE
        downloadCountView.visibility = View.GONE

    }

    companion object {
        @JvmStatic
        private var headView : HeadView? = null
        @JvmStatic
        fun getInstance(context: Context): HeadView{
            if(headView == null){
                headView = HeadView(context)
            }
            return headView as HeadView
        }
    }

    val onTouchListener = object : View.OnTouchListener{
        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
          return true
        }

    }



}