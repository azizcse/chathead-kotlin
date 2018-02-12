package com.core.head.provider

import com.squareup.otto.Bus
import com.squareup.otto.ThreadEnforcer


/*
*  ****************************************************************************
*  * Created by : Md. Azizul Islam on 2/12/2018 at 1:35 PM.
*  * Email : azizul@w3engineers.com
*  * 
*  * Last edited by : Md. Azizul Islam on 2/12/2018.
*  * 
*  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>  
*  ****************************************************************************
*/

object BusProvider {
    private val bus = Bus(ThreadEnforcer.ANY)
    fun getBus():Bus{
        return bus
    }
}