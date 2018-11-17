package com.iyanuadelekan.kanary.app

import com.iyanuadelekan.kanary.app.framework.lifecycle.Context
import com.iyanuadelekan.kanary.app.router.AppRouter

typealias RouterAction = (Context) -> Unit

internal typealias RouteList = ArrayList<AppRouter.RouteNode>