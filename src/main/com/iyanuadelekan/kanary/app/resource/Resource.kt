package com.iyanuadelekan.kanary.app.resource

/**
 * @author Iyanu Adelekan on 16/08/2018.
 */
interface Resource {

    /**
     * @property [resourceType] - the type of created resource.
     */
    val resourceType: Resource.Type

    /**
     * Enum class of supported app resource types.
     */
    enum class Type {
        DATABASE,
        CACHE,
        MAILER_SERVICE,
    }
}