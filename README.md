# Kanary
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

A minimalist🔬 Kotlin web framework for building🔩⚙ scalable📈 and expressive🎨 RESTful APIs

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
  server.listen(8080)
}
```

### Creating controllers
A controller is any instance of a class that extends KanaryController. The class below is a simple controller class that does nothing.
```kotlin
class DummyController : KanaryController()
```

### Creating controller actions
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
```

### Controller action lifecycle callbacks
There are two distinct action lifecycle callbacks that can be declared within a KanaryController. These are:
  * beforeAction - if declared, executes immediately before an action is executed
  * afterAction - if declared, executes immediately after an action is executed

Declaring these two callbacks is as easy as declaring a function within a controller:
```kotlin
class UserController : KanaryController() {

    override fun beforeAction(request: HttpServletRequest, response: HttpServletResponse?) {
        println("I execute before anything else!")
    }
    
    override fun afterAction(request: HttpServletRequest, response: HttpServletResponse?) {
        println("I execute once an action is completed!")
    }

    fun createUser(baseRequest: Request, request: HttpServletRequest, response: HttpServletResponse) {
        // action code goes here
    }
    
}
```
### Routing
All routing is done by one or more specified routers. A router is an instance of KanaryRouter:
```kotlin
val userRouter = KanaryRouter()
```

#### Declaring route paths
```kotlin
userRouter on "users/" use userController //router uses userController to cater for all routes prefixed by '/users'
userRouter.post("new/", userController::createUser) //maps POST '/users/new' to the createUser action in userController
```
The above can also be done with:
```kotlin
userRouter.post("users/new/", userController::createUser, userController)
```
#### Mounting routers to application
A single router can be mounted to a KanaryApp instance as follows:
```kotlin
app.mount(userRouter)
```
Numerous routers can be mounted at a go:
```kotlin
app.mount(routerA, routerB, routerC, ..., routerN)
```

### Middleware
All middleware take the form of a lambda. A single nullable instance of HttpServletRequest is passed to every middleware added to the application.
```kotlin
app.use { println("I'm middleware!") }
app.use { println("Request path info: ${it.pathInfo}") }
```
Multiple middleware can be added at a go:
```kotlin
app.use({ println("I'm middleware!") }, { println("Request path info: ${it.pathInfo}") } )
```
It is important to note that all middleware execute in a non blocking fashion parrallel to the main application thread.

#### Bundled middleware
The sole middleware bundled with Kanary is 'simpleConsoleRequestLogger'. It prints out succinct information about each request received to the console.
```kotlin
app.use(simpleConsoleRequestLogger)
```

## Working with requests and responses
### Handling requests
In most cases, request handling should be done by the use of the immutable HttpServletRequest instance passed to your controller actions. This instance is an object of Java's [HttpServletRequest](http://docs.oracle.com/javaee/6/api/javax/servlet/http/HttpServletRequest.html) with Kanary specific helper functions. These additional functions provided are:

| Function | Description | Return type |
| ------ | ------ | ------|
| getBody() | Used to retrieve HTTP request body content | JsonNode? |
| getBodyAsJson() | Used to retrieve HTTP request body content in the form of a JSON string | String |

A mutable request object is exposed in the form of a [Request](http://download.eclipse.org/jetty/stable-9/apidocs/org/eclipse/jetty/server/Request.html) instance. Request implements HttpServletRequest and as such has behaviours and characteristics similar to those possessed by the HttpServletRequest instance passed to an action. In addition to the functions shown in the table above, the Request instance passed has:

| Function | Description | Return type |
| ------ | ------ | ------|
| done() | Used to specify that a request has been successfully handled | Unit |
