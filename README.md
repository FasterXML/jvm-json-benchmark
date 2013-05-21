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

### Genson (0.94) (http://code.google.com/p/genson/)

Similar to GSON, need to use JDK `InputStreamReader`. In addition, output only as `String`, which
is then written using `OutputStreamWriter`.

### GSON (2.2) (http://code.google.com/p/google-gson/)

Tested in basic data-binding mode, with default configuration.

GSON does not take `InputStream` or `OutputStream`,
so input is read using standard JDK `InputStreamReader` (with UTF-8)
and output `OutputStreamWriter`.

### Jackson (2.2) (https://github.com/FasterXML/jackson)

Tested in basic data-binding mode, with default configuration.

### JSON-tools (http://jsontools.berlios.de/)

Seems to require two-phase process for reading (with `JSONParser` from `InputStream`, into `JSONValue`;
then mapped using `JSONMapper`.
Similarly writing has to be done using an intermediate `String` -- inefficient, but that's what API expects.

## Libraries not included

### Json-smart (http://code.google.com/p/json-smart/) (2.0-RC2)

Simply, does not work. Although I found a way that should allow Bean conversions, code just mysteriously
fails with a class-loading error.

If anyone can suggest a fix or work-around, I will be happy to include this in tests as well.

