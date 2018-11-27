package com.iyanuadelekan.kanary.app

import com.iyanuadelekan.kanary.app.constant.Protocol
import com.iyanuadelekan.kanary.app.framework.resource.ResourceManager
import com.iyanuadelekan.kanary.app.framework.resource.ResourceRegistry
import com.iyanuadelekan.kanary.app.resource.Resource

/**
 * @author Iyanu Adelekan on 17/08/2018.
 */

/**
 * Annotation specifying that the target class is intended to be used as
 * an application resource that can be registered within Kanary's
 * [ResourceRegistry] via a [ResourceManager].
 *
 * @property resourceType - the type of resource specified.
 */
@MustBeDocumented
@Target(AnnotationTarget.CLASS)
annotation class Resource(val resourceType: Resource.Type)

/**
 * Annotation specifying that the marked class is a valid application request handler.
 *
 * @property description - request handler description.
 * @property protocol - Application layer protocol handled.
 */
@MustBeDocumented
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
internal annotation class RequestHandler(val description: String, val protocol: Protocol)

/**
 * Annotation specifying that the marked class is a valid API response entity.
 *
 * @property description - response entity description.
 */
@MustBeDocumented
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class ResponseEntity(val description: String = "API response entity.")