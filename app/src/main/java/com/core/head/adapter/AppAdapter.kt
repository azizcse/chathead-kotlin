package com.core.head.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.core.head.R
import com.core.head.util.AppModel
import com.core.kbasekit.ui.base.BaseAdapter
import com.core.kbasekit.ui.base.BaseViewHolder


/*
*  ****************************************************************************
*  * Created by : Md. Azizul Islam on 2/12/2018 at 2:47 PM.
*  * Email : azizul@w3engineers.com
*  * 
*  * Last edited by : Md. Azizul Islam on 2/12/2018.
*  * 
*  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>  
*  ****************************************************************************
*/

class AppAdapter : BaseAdapter<AppModel> {

    private val context:Context
    constructor(context: Context){
        this.context = context
    }
    override fun isEqual(leftItem: AppModel, rightItem: AppModel): Boolean {
        return false
    }

    override fun newViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<AppModel> {
        val view = LayoutInflater.from(context).inflate(R.layout.item_app_layout, parent, false)
        return AppHolder(view)
    }

    private inner class AppHolder(itemView : View) : BaseViewHolder<AppModel>(itemView), View.OnClickListener{
        private var namveTv : TextView
        private var appImage:ImageView
        private var viewHolder : LinearLayout
        init {
            appImage = itemView.findViewById(R.id.app_iv)
            namveTv = itemView.findViewById(R.id.name_tv)
            viewHolder = itemView.findViewById(R.id.view_holder)
            viewHolder.setOnClickListener(this)
        }

        override fun bind(item: AppModel) {
            appImage?.setImageDrawable(item.drawable)
            namveTv.text = item.path
        }

        override fun onClick(v: View?) {
            itemClickListener.onItemClick(v!!, getItem(adapterPosition))
        }
    }
}