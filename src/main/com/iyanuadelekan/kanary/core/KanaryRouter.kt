/**
 * @author Iyanu Adelekan on 23/05/2017.
 */

package com.iyanuadelekan.kanary.core

import com.iyanuadelekan.kanary.interfaces.RouterInterface

/**
 * @property basePath the root path of mapped HTTP request
 * @constructor Initializes KanaryRouter object
 */
class KanaryRouter(val basePath: String, val controller: KanaryController): RouterInterface {

    /**
     * Router function handling GET requests
     * @return the KanaryRouter instance
     */
    override fun get(path: String, action: String): KanaryRouter {
        return this
    }

    /**
     * Router function handling POST requests
     * @return the KanaryRouter instance
     */
    override fun post(path: String, action: String): KanaryRouter {
        return this
    }

    /**
     * Router function handling DELETE requests
     * @return the KanaryRouter instance
     */
    override fun delete(path: String, action: String): KanaryRouter {
        return this
    }

    /**
     * Router function handling PUT requests
     * @return the KanaryRouter instance
     */
    override fun put(path: String, action: String): KanaryRouter {
        return this
    }

    /**
     * Router function handling PATCH requests
     * @return the KanaryRouter instance
     */
    override fun patch(path: String, action: String): KanaryRouter {
        return this
    }

}