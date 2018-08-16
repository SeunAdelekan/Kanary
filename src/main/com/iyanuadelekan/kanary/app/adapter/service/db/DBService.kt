package com.iyanuadelekan.kanary.app.adapter.service.db

import com.iyanuadelekan.kanary.app.resource.Resource

/**
 * @author Iyanu Adelekan on 16/08/2018.
 *
 * Interface declaring properties and methods that must be implemented by
 * database services natively compatible with Kanary.
 */
interface DBService : Resource {

    /**
     * @property port - Database port. This value will be retrieved
     * from the environment configuration.
     */
    val port: Int

    /**
     * @property host - Database host. This value will be retrieved
     * from the environment configuration.
     */
    val host: String

    /**
     * @property dbName - Database name. This value will be retrieved
     * from the environment configuration.
     */
    val dbName: String

    /**
     * @property password - Database connection password. This value will be retrieved
     * from the environment configuration.
     */
    val password: String

    /**
     * This method will be invoked to connect to a database with the configured
     * database properties.
     */
    fun connect()
}