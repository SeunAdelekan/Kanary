package com.iyanuadelekan.kanary.app

import com.iyanuadelekan.kanary.app.framework.lifecycle.Context
import com.iyanuadelekan.kanary.app.router.RouteNode

typealias RouterAction = (Context) -> Unit

typealias LifeCycleEvent = () -> Unit

internal typealias RouteList = ArrayList<RouteNode>