/**
 * @author Iyanu Adelekan
 */

package com.iyanuadelekan.kanary.interfaces

import com.iyanuadelekan.kanary.core.KanaryController
import com.iyanuadelekan.kanary.core.KanaryRouter

interface RouterInterface {

    fun get(path: String, action: String): KanaryRouter

    fun post(path: String, action: String): KanaryRouter

    fun put(path: String, action: String): KanaryRouter

    fun delete(path: String, action: String): KanaryRouter

    fun patch(path: String, action: String): KanaryRouter

}