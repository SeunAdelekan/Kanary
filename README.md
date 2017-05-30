# Kanary
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

A light weight🎈 Kotlin web framework for building🔩⚙ scalable📈 and expressive🎨 RESTful APIs

```kotlin
fun main(args: Array<String>) {

    val app = KanaryApp()
    val server = Server()
    val userRouter = KanaryRouter()
    val userController = UserController()

    userRouter on "users/" use userController
    userRouter.post("new/", userController::createUser)
    userRouter.get("details/", userController::retrieveUser)

    app.mount(userRouter)
    server.handler = AppHandler(app)
    server.listen(8080)
    
}
```

## Installation
Framework resources are contained in the package com.iyanuadelekan.kanary and can be included in your application via maven. Include the following in your application's pom.xml file:
```xml
<dependencies>
  ...
  <dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>${jackson.version}</version>
  </dependency>
  ...
</dependencies>
```

## Non maven
For non-Maven use cases, you can download jars from bintray

## Features

  * Expressive routing
  * Focus on clode clarity
  * Support for controllers
  * Inclusion of HTTP helpers
  * Full support for asynchronous middleware
  * Presence of concise English like 'one liners' 
  * Availability of action lifecycle callback methods
  
## Quick start
### Creating a Kanary app and starting a server
A simple Kanary app that listens on a port is created by initializing an istance of KanaryApp, creating a Server object, creating an AppHandler instance, setting that instance as the server's handler and starting the server to listen on a specified port. 
```kotlin
fun main(args: Array<String>) {
  val app = KanaryApp()
  val server = Server()

  server.handler = AppHandler(app)
  server.listen
}
```

### Creating controllers
A controller is any instance of a class that extends KanaryController. The class below is a simple controller class that does nothing.
```kotlin
class DummyController : KanaryController()
```
Though the fact that the above class is a controller is correct, generally you'll want to specify actions within your controller to route requests to.
An action is a controller function that takes three parameters as its arguments:
  * An instance of Request (a mutable request object)
  * An instance of HttpServletRequest (an immutable request object)
  * An instance of HttpServletResponse (a response object)
  
A valid action is shown within the controller below
```kotlin
class UserController : KanaryController() {

    fun createUser(baseRequest: Request, request: HttpServletRequest, response: HttpServletResponse) {
        // action code goes here
    }
    
}
