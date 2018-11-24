package com.iyanuadelekan.kanary.app.constant

/**
 * @author Iyanu Adelekan on 18/08/2018.
 *
 * Supported application layer protocols the framework can handle.
 */
enum class Protocol {
    HTTP, // includes HTTPS
}

/**
 * Route type definitions.
 */
internal enum class RouteType {
    POST,
    GET,
    DELETE,
    OPTIONS,
    PUT;

    companion object {
        val methodSet = HashSet<String>()

        init {
            RouteType.values().forEach {
                methodSet.add(it.name)
            }
        }
    }
}