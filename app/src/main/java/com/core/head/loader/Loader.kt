package com.core.head.loader

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import com.core.head.provider.AppEvent
import com.core.head.provider.BusProvider
import com.core.head.util.AppModel
import java.util.ArrayList


/*
*  ****************************************************************************
*  * Created by : Md. Azizul Islam on 2/12/2018 at 1:32 PM.
*  * Email : azizul@w3engineers.com
*  * 
*  * Last edited by : Md. Azizul Islam on 2/12/2018.
*  * 
*  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>  
*  ****************************************************************************
*/

class Loader : Thread {
    private val context : Context
    constructor(context: Context){
        this.context = context
    }

    override fun run() {
        getInstalledApps()
    }

    private fun getInstalledApps() {
        val res = ArrayList<AppModel>()
        val packs = context.getPackageManager().getInstalledPackages(0)
        for (i in packs.indices) {
            val p = packs.get(i)
            if (isSystemPackage(p) == false) {
                val appName = p.applicationInfo.loadLabel(context.getPackageManager()).toString()
                val icon = p.applicationInfo.loadIcon(context.getPackageManager())
                res.add(AppModel(appName, icon))
            }
        }

        val appEvent = AppEvent(res)
        BusProvider.getBus().post(appEvent)
    }

    private fun isSystemPackage(pkgInfo: PackageInfo): Boolean {
        return if (pkgInfo.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM != 0) true else false
    }
}