package com.example.moviecompose.base.presentations.navigation

import android.content.Context

open class NavigationCoordinator<EVENT> {
     open fun init(param:Any){}
     open fun onStart(context: Context?=null, param: Any?=null){}
     open  fun onEvent(event:EVENT){}

}