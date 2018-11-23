package com.iyanuadelekan.kanary.exceptions

import com.iyanuadelekan.kanary.app.resource.Resource

class ResourceNotFoundException(resourceType: Resource.Type) :
        Exception("No resource of type $resourceType has been registered.")

class ResourceOverrideException(resourceType: Resource.Type) :
        Exception("A resource of type $resourceType has already been registered.")

class InvalidRouteException(override val message: String) : Exception(message)