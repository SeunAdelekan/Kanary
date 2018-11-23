package com.iyanuadelekan.kanary.utils

/**
 * @author Iyanu Adelekan on 26/05/2017.
 */
class RequestUtils {

    /**
     * Used to format a request [target]
     * @param target the target to be formatted
     */
     fun formatTarget(target: String?): String? {
        var target: String? = target

        if (target?.elementAt(target.length - 1) != '/') {
            target += '/'
        }
        return target
    }
}