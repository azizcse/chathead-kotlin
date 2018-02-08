package com.core.head.util

import android.content.Context


/*
*  ****************************************************************************
*  * Created by : Md. Azizul Islam on 2/8/2018 at 6:01 PM.
*  * Email : azizul@w3engineers.com
*  * 
*  * Last edited by : Md. Azizul Islam on 2/8/2018.
*  * 
*  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>  
*  ****************************************************************************
*/

object Util {
    fun getDeviceWidth(context: Context): Int {
        return context.resources.displayMetrics.widthPixels
    }

    fun getDeviceHeight(context: Context): Int {
        return context.resources.displayMetrics.heightPixels
    }
}