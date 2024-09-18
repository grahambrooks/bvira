# bvira - sample web application

This repository is no longer maintained (Archived)

Bvira is a reference/example web application modeled on an extremely
successful project. The application architecture exhibited a very
clean separation of concerns and overall the project demonstrated how
a number of principles and practices contributed to the success of the
project.

## Build Times

After 3+ months of development the project build and unit test cycle
was less that 2 minutes. (Bvira is very primitive by comparison and
currently builds and tests in 8 seconds). A short build time meant
that developers could concentrate on delivering value through
application features.

## Self contained source and dependencies

Everything that is required to build and test the application in development is stored under version control. Developers were responsible for adding, updating and removing dependencies from the appropriate lib folder manually. All build tools were also stored in the repository.

This single source of truth meant that new developers could be up and running by installing a JDK and checking out the source repository.

Keeping the dependencies small was everyone's business.

Bvira is missing a persistence capability. The project that inspired bvira used an in-memory hypersonic in development to keep the builds fast.

Some characteristics of Bvira

## Requests handling

Requests are handled and routed by a custom servlet to component classes. Each component class handles a number of paths. POST requests are routed to Command classes that accept parameters and then using the Post-Redirect-Get pattern return the URI for the later GET request. Get requests are again routed to a component to a presenter for the requested URI.

## Response rendering

Bvira using stringtemplate for content templates. StringTemplate (http://www.stringtemplate.org) has a very clean separation of concerns and by design does not allow any business logic within the template.

## Dependency injection

Capabilities like dependency injection are placed behind a facade that only exposes the functionality required by the application. Currently dependency injection is provided by PicoContainer (http://picocontainer.codehaus.org) but because it is behind application classes it can be quite easily replaced as additional requirements are identified.



[![Bitdeli Badge](https://d2weczhvl823v0.cloudfront.net/grahambrooks/bvira/trend.png)](https://bitdeli.com/free "Bitdeli Badge")

