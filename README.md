# Benchmark for comparing JSON performance on Java platform

This project contains benchmark suite for comparing performance of JSON-to/from-POJOs
data binding, using various Java JSON libraries.

## Basics

Libraries included are mostly ones that can do fully automatic data-binding; but in some
cases we may also include ones that only implement Tree-based processing, in which case
additional manual coding is required.
We may also include tree-based variants of data-binding - capable libraries in cases where that makes sense.
In all cases we indicate processing-module uses (which indicates level-of-effort for using
library for data-binding use cases).

## Libraries included

### Flex-JSON

* Home page: http://sourceforge.net/projects/flexjson
* Version tested: 2.1

NOTE: version 3.0 appears to be available, but I could not make it work.
2.1 appears to be the official publicly available version.

### Genson

* Home page: http://code.google.com/p/genson/
* Version tested: 0.94

Similar to GSON, need to use JDK `InputStreamReader`. In addition, output only as `String`, which
is then written using `OutputStreamWriter`.

### GSON

* Home page: http://code.google.com/p/google-gson/)
* Version tested: 2.2

Tested in basic data-binding mode, with default configuration.

GSON does not take `InputStream` or `OutputStream`,
so input is read using standard JDK `InputStreamReader` (with UTF-8)
and output `OutputStreamWriter`.

### Jackson

* Home page: https://github.com/FasterXML/jackson
* Version tested: 2.2

Tested in basic data-binding mode, with default configuration.

### JSON-tools

* Home page: http://jsontools.berlios.de/
* Version tested: 1.7

Seems to require two-phase process for reading (with `JSONParser` from `InputStream`, into `JSONValue`;
then mapped using `JSONMapper`.
Similarly writing has to be done using an intermediate `String` -- inefficient, but that's what API expects.

## Libraries not included

Following libraries were considered for inclusion, but had to be excluded for some reason.

### Json-smart

* Home page: http://code.google.com/p/json-smart/
* Version tested: 2.0-RC2

Simply, does not work. Although I found a way that should allow Bean conversions, code just mysteriously
fails with a class-loading error.

If anyone can suggest a fix or work-around, I will be happy to include this in tests as well.
Results from sources other than project home page suggest not-so-stellar performance (like jvm-serializers).

