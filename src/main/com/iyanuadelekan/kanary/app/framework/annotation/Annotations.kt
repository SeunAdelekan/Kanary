package com.iyanuadelekan.kanary.app.framework.annotation

import com.iyanuadelekan.kanary.app.resource.Resource

/**
 * @author Iyanu Adelekan on 17/08/2018.
 */
@Target(AnnotationTarget.CLASS)
annotation class Resource(val resourceType: Resource.Type)