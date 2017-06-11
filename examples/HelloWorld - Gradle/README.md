# Kanary Hello World
This is a sample Kanary RestAPI with a gradle build configuration, already prepared for Heroku deployment

Quickstart
==========

Deploy using Heroku:

* Download and cd into the directory.
```sh
$ cd examples/HelloWorld\ -\ Gradle/
```

* Create a new git project and add all the files to it
```sh
$ git add .
```

* Commit all the files to git
```sh
$ git commit -m "init"
```

* Login to Heroku
```sh
$ heroku login
```

* Create a new Heroku project and take note of the url
```sh
$ heroku create
```

* Push everything to Heroku and your new RestAPI will be available at the url you copied earlier
```sh
$ git push heroku master
```
