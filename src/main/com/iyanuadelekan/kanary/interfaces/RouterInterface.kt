/**
 * @author Iyanu Adelekan
 */

package com.iyanuadelekan.kanary.interfaces

import com.iyanuadelekan.kanary.core.KanaryController
import com.iyanuadelekan.kanary.core.KanaryRouter

interface RouterInterface {

    fun get(path: String, controller: KanaryController): KanaryRouter

    fun post(path: String, controller: KanaryController): KanaryRouter

    fun put(path: String, controller: KanaryController): KanaryRouter

    fun delete(path: String, controller: KanaryController): KanaryRouter

    fun patch(path: String, controller: KanaryController): KanaryRouter

}