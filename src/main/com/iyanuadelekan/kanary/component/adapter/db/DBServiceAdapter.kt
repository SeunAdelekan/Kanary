package com.iyanuadelekan.kanary.component.adapter.db

interface DBServiceAdapter {

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