# clog

[![Build Status](https://travis-ci.org/victorneo/clog.png?branch=master)](https://travis-ci.org/victorneo/clog)

Clog is a blogging web app built on Clojure. To be specific, it is built using
the following libraries:

- Compojure
- [Selmer](https://github.com/yogthos/Selmer) for templating

### TODO

- Render templates using Selmer
- Save blog posts into a SQLite database and view them
- Authentication

### Prerequisites

You will need [Leiningen][1] 2.0.0 or above installed.

[1]: https://github.com/technomancy/leiningen

### Running

To start a web server for the application, run:

    lein ring server

### License

Code is MIT Licensed, see LICENSE.

Copyright © 2013 Victor
