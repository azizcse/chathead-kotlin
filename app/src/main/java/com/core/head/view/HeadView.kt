package com.core.head.view

import android.content.Context
import android.graphics.PixelFormat
import android.os.Build
import android.provider.Settings
import android.support.constraint.solver.Goal
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.core.head.R
import com.core.head.util.Util
import com.facebook.drawee.view.SimpleDraweeView
import java.util.*
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.graphics.drawable.Drawable
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.core.head.adapter.AppAdapter
import com.core.head.util.AppModel
import com.core.kbasekit.ui.base.BaseListener


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

class HeadView : BaseListener<AppModel> {

    private var layoutParam: WindowManager.LayoutParams? = null
    private var mContext: Context
    private var mWindowManager: WindowManager? = null
    private var mDownloadView: View? = null
    private val downloadHeadRecycler: RecyclerView?
    private val thumbImageView: ImageView
    private val downloadCount: TextView
    private val downloadCountView: LinearLayout
    private var isOpen = false
    private var initialX: Int = 0
    private var appAdapter : AppAdapter

    private constructor(context: Context) {
        this.mContext = context
        mDownloadView = LayoutInflater.from(mContext).inflate(R.layout.layout_download_head, null)
        downloadHeadRecycler = mDownloadView!!.findViewById(R.id.chatHeadRecyclverView) as RecyclerView
        appAdapter = AppAdapter(context)

        layoutParam = WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT)

        layoutParam!!.gravity = Gravity.TOP or Gravity.LEFT

        layoutParam!!.x = Util.getDeviceWidth(mContext) / 2
        layoutParam!!.y = Util.getDeviceHeight(mContext) / 2

        mWindowManager = mContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        mWindowManager!!.addView(mDownloadView, layoutParam)

        thumbImageView = mDownloadView!!.findViewById(R.id.thumb_img_view)
        downloadCount = mDownloadView!!.findViewById(R.id.count) as TextView
        downloadCountView = mDownloadView!!.findViewById(R.id.download_count_view)
        mDownloadView!!.visibility = View.GONE
        downloadCountView.visibility = View.GONE

        thumbImageView.setOnTouchListener(onTouchListener)
    }

    fun showHead() {
        if (mDownloadView != null && mDownloadView!!.visibility == View.VISIBLE) {
            mDownloadView!!.visibility = View.GONE
        } else {
            mDownloadView!!.visibility = View.VISIBLE
        }

    }

    companion object {
        @JvmStatic
        private var headView: HeadView? = null

        @JvmStatic
        fun getInstance(context: Context): HeadView {
            if (headView == null) {
                headView = HeadView(context)
            }
            return headView as HeadView
        }
    }

    val onTouchListener = object : View.OnTouchListener {
        private var initialX: Int = 0
        private var initialY: Int = 0
        private var initialTouchX: Float = 0.toFloat()
        private var initialTouchY: Float = 0.toFloat()

        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
            when (event?.getAction()) {
                MotionEvent.ACTION_DOWN -> {
                    initialX = layoutParam!!.x
                    initialY = layoutParam!!.y

                    initialTouchX = event.rawX
                    initialTouchY = event.rawY
                    return true
                }
                MotionEvent.ACTION_UP -> {
                    val Xdiff = (event.rawX - initialTouchX).toInt()
                    val Ydiff = (event.rawY - initialTouchY).toInt()
                    if (Xdiff < 10 && Ydiff < 10) {
                        if (isOpen) {
                            onCloseDownloadHead()
                        } else {
                            onOpenDownloadHead()
                        }
                    }
                    return true
                }
                MotionEvent.ACTION_MOVE -> {
                    layoutParam!!.x = initialX + (event.rawX - initialTouchX).toInt()
                    layoutParam!!.y = initialY + (event.rawY - initialTouchY).toInt()
                    mWindowManager!!.updateViewLayout(mDownloadView, layoutParam)
                    return true
                }
            }
            return true
        }
    }

    fun onCloseDownloadHead(){
        if (downloadHeadRecycler != null && downloadHeadRecycler.visibility == View.VISIBLE) {
            downloadHeadRecycler.visibility = View.GONE
        }
        downloadCountView.visibility = View.GONE
        layoutParam!!.x = Util.getDeviceWidth(mContext)
        layoutParam!!.y = initialX
        layoutParam!!.width = WindowManager.LayoutParams.WRAP_CONTENT
        mWindowManager!!.updateViewLayout(mDownloadView, layoutParam)
        isOpen = false
    }

    fun onOpenDownloadHead(){
        layoutParam!!.x = 0
        initialX = layoutParam!!.y
        mWindowManager!!.updateViewLayout(mDownloadView, layoutParam)
        downloadCountView.visibility = View.VISIBLE
        downloadHeadRecycler!!.setVisibility(View.VISIBLE)
        downloadHeadRecycler.layoutManager = LinearLayoutManager(mContext)
        downloadHeadRecycler.adapter = appAdapter
        appAdapter.itemClickListener = this
        isOpen = true
    }


    private val onClickListener = object : View.OnClickListener {
        override fun onClick(v: View?) {

        }
    }

    fun loadData(appList: List<AppModel>) {
        Log.e("AppDataModel","List size = ${appList.size}")
        appAdapter.clear()
        appAdapter.addItems(appList)
        downloadCount.text = "(${appList.size})"
    }

    override fun onItemClick(view: View, t: AppModel) {
        Toast.makeText(mContext, "${t.path}", Toast.LENGTH_SHORT).show()
    }

    override fun onItemClick(view: View, position: Int) {

    }


}