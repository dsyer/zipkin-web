Demo project to run Zipkin Web from a plain Java main. Useful to import into an 
IDE and use to run Zipkin we with configurable settings.

You can add zipkin "flags" to "application.properties", i.e. properties
with names in `zipkin.web.*` will be converted to command line args.
It also recognizes "server.port" as a special case. You can get a list
of flags by adding one it doesn't recognize to make it print a usage.

Common useful flags:

* `zipkin.web.query.dest` (default `localhost:9411`) the host and port 
of the zipkin server

* `zipkin.web.port` (default 8080) the port for the UI to listen on for
HTTP connections. Using this project `server.port` also works.
