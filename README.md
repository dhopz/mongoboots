# Makers Mongoboots

_Coaching this? Find the source
[here.](https://github.com/makersacademy/slug/blob/main/materials/universe/engineering_projects/mongo-boots/README.ed.md)_

This is the Mongoboots Engineering Project.

It comes set up with:

* The JUnit5 test framework.
* The Spring Boot web framework.
* Spring Data MongoDB.

The project comes just with one feature implemented. Your task is to work
through the remaining tickets. To do this, you will work in groups, working
through the same dynamics you did before during the Aceplay project.

## MongoDB Official Crash Course

Before getting our hands on the Mongoboots project, we will learn more about
MongoDB, one of the top NoSQL choices when working with databases.

Your first task will be to sign up for and complete [an Official MongoDB
Introductory course.](https://university.mongodb.com/courses/M001/about)

:boom: You could earn a certification at the end of this course too!

## Getting the project set up

### Stage One: Project & Tests

Open this directory using [IntelliJ](https://www.jetbrains.com/idea/). You may
need to wait for the dependencies to install.

Then, in the Gradle sidebar:

* To run the tests: `mongo-boots -> Tasks -> Verification -> Test`

If you encounter any problems, they are not intended. Contact your coach for
advice.

### Stage Two: Development Server

Finally, to run the server, open the Gradle sidebar and run `mongo-boots -> Tasks ->
Application -> bootRun`.

This won't work out of the blue. If you are getting a `Connection`-type error,
this is intended.

The thing is we have specified that we want to use MongoDB as part of our
project. And if you have explored the codebase, and ideally done some
diagramming, you may have noticed there is already some functionality included
as part of the app.

Your first task as part of this module is to investigate what we are missing as
part of our database connection configuration.

<!-- OMITTED -->

When the server has started successfully, you'll be able to visit
`http://localhost:8080/` and get some sort of output, even if it is not
something you would expect!

### Stage Three: Work through Mongoboots Tickets

<!-- OMITTED -->

You will find the tickets under [this file](./tickets.yml).

## Documentation

You will not find as much documentation as with Aceplay, and this is also
intended. You will be able to refer to many of the things you learned as part of
the previous project.

* **[Kickoff video](https://www.youtube.com/watch?v=5mjmE6_BmAE)**
