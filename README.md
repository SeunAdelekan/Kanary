# Kanary
![alt text](images/Kanary.JPG?raw=true "")
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[ ![Download](https://api.bintray.com/packages/iyanuadelekan/Kanary/kanary/images/download.svg) ](https://bintray.com/iyanuadelekan/Kanary/kanary/_latestVersion)

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
Framework resources are contained in the package com.iyanuadelekan.kanary and can be included in your application via Maven, Gradle and Ivy. Include the necessary out of the following in your application:
### Maven
Include Jcenter as a plugin repository
```xml
<repositories>
   <repository>
     <id>jcenter</id>
     <name>JCenter</name>
     <url>https://jcenter.bintray.com/</url>
   </repository>
</repositories>
```
Add Kanary as a project dependency
```xml
<dependencies>
  ...
  <dependency>
    <groupId>com.iyanuadelekan</groupId>
    <artifactId>kanary</artifactId>
    <version>0.9.0</version>
  </dependency>
  ...
</dependencies>
```

### Gradle
```groovy
repositories {
    jcenter()
}

dependencies {
    compile 'com.iyanuadelekan:kanary:0.9.0'
}
```

### Ivy
```xml
<dependency org='com.iyanuadelekan' name='kanary' rev='0.9.0'>
  <artifact name='kanary'></artifact>
</dependency>
```

## Others
For other use cases, you can download jars from bintray

## Features

  * Expressive routing
  * Focus on code clarity
  * Support for controllers
  * Inclusion of HTTP helpers
  * Full support for asynchronous middleware
  * Presence of concise English like 'one liners' 
  * Availability of action lifecycle callback methods
  
## Quick start
A breakdown of project packages is [here](#packages).

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

### Responding to a request
Responses are sent to a client with the use of an [HttpServletResponse](http://docs.oracle.com/javaee/6/api/javax/servlet/http/HttpServletResponse.html) passed to an action. In addition to all characteristics and behaviours exposed by this instance, the following Kanary specific helper functions are available:

| Function | Description | Return type |
| ------ | ------ | ------|
| withStatus(status: Int) | Sets the response status code | Unit |
| send(message: String) | Sends a plain text message to a client | Unit |
| sendJson(responseNode: JsonNode?) | Sends a json response to a client | Unit |
| end() | Ends the HTTP response process | Unit |
| sendStatus(status: Int) | Sends the response status code as plain text | Unit |
| sendFile(file: File, contentType: String="", contentLength: Int=0) | Sends a file to a client | Unit |
| redirect(url: String) | Redirects the request to the specified URL | Unit |
| sendHtml(html: String) | Sends HTML content to a client | Unit |

All functions above except 'sendFile' can be written in infix notation (the recommended way of writing code in Kanary). This permits the writing of beautiful, clear and expressive code for responding to clients.
Thus to send a plain text message to a client:
```kotlin
class UserController : KanaryController() {

    fun createUser(baseRequest: Request, request: HttpServletRequest, response: HttpServletResponse) {
        response withStatus 201 send "User successfully created!"
        baseRequest.done()
    }
    
}
```

## Packages
| class | package |
| ------ | ------ |
| KanaryApp | com.iyanuadelekan.kanary.app |
| KanaryController | com.iyanuadelekan.kanary.core |
| KanaryRouter | com.iyanuadelekan.kanary.core |
| AppHandler | com.iyanuadelekan.kanary.handlers |
| server | com.iyanuadelekan.kanary.server |

## Dependencies
* [Jetty](http://eclipse.org/jetty) (as an application server engine)
* [Jackson-databind](https://github.com/FasterXML/jackson-databind/blob/master/README.md) (for JSON serialization/deserialization)

## Philosophy
Kanary was created in order to facilitate the quick implementation of stable and non verbose RESTful APIs with the Kotlin programming language. 

### Convention versus Configuration
Kanary was designed to utilise a respectful approach in aiding engineers and developers to create micro-service based applications. As a consequence of this approach, no conventions are forced upon developers. The means by which an application is implemented is at the sole discretion of the implementer.

## Road map
* Addition of tests
* Creation of a vast array of sample applications demonstrating the use of Kanary
* Creation of cli utilities to support the rapid creation of Kanary applications
* Addition of hot reloading capabilities on change and save of app program files
* Implementation of a mailer
* Implementation of a template engine system for those who wish to use Kanary in an MVC oriented way
* Adding support for other popular application servers like Tomcat and Netty

## Contributing
Contributions are welcome from all corners of the world! Read the CONTRIBUTIONS.rst file to get started.
Individuals interested in becoming a part of the core development team can connect with me on LinkedIn [here](linkedin.com/in/iyanu-adelekan-13a85a112) and send a message expressing interest.

## People
Kanary was created by [Iyanu Adelekan](https://github.com/SeunAdelekan). He is the current project lead.

## License
[Apache 2.0](LICENSE)
