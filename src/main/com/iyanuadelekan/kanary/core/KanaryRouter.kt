/**
 * @author Iyanu Adelekan on 23/05/2017.
 */

package com.iyanuadelekan.kanary.core

import com.iyanuadelekan.kanary.interfaces.RouterInterface

class KanaryRouter(val basePath: String): RouterInterface {

    /**
     * Router function handling GET requests
     */
    override fun get(path: String, controller: KanaryController): KanaryRouter {
        return this
    }

    /**
     * Router function handling POST requests
     */
    override fun post(path: String, controller: KanaryController): KanaryRouter {
        return this
    }

    override fun delete(path: String, controller: KanaryController): KanaryRouter {
        return this
    }

    override fun put(path: String, controller: KanaryController): KanaryRouter {
        return this
    }

    override fun patch(path: String, controller: KanaryController): KanaryRouter {
        return this
    }

}