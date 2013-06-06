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

## Test framework

Tests use Japex, since it

1. Is designed for full benchmark runs (not just micro-benchmarks), and
2. It provides full JVM isolation between tests, to avoid Hot-Spot problems wrt multiple competing implementations

Some attempts were made to use Caliper, which has some promising features; but in the end
Japex proved bit more mature for this specific use case.
But we may try out other frameworks in future.

## Results

None published yet -- Stay Tuned!

## Libraries included

### Fastjson

* Home page: https://github.com/alibaba/fastjson
* Version tested: 1.1.30

Tested with default settings; except configured NOT to check for cyclic dependencies (as that
adds overhead).
Library apparently can not take streaming input, so code has to read all content in; this
is done using simple copy (which gives slight advantage compared to having to do it the
usual way); but should not change results drastically.

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

Note that tests does NOT use [Afterburner](https://github.com/FasterXML/jackson-module-afterburner) module (which could increase speed by 30-40%)
as the intent is to compare default implementations, configurations.
A separate test driver may be added in future to test such variation.

### JSON-tools

* Home page: http://jsontools.berlios.de/
* Version tested: 1.7

Seems to require two-phase process for reading (with `JSONParser` from `InputStream`, into `JSONValue`;
then mapped using `JSONMapper`.
Similarly writing has to be done using an intermediate `String` -- inefficient, but that's what API expects.

## Libraries not included

Following libraries were considered for inclusion, but had to be excluded for some reason.

### Json-lib

* Home page: http://json-lib.sourceforge.net
* Version: 2.4

This library is sort-of kind-of capable of data-binding, but requires lots of additional configuration and
registration of types (see `JsonLibJsonDatabind.java` of [JVM-serializers](https://github.com/eishay/jvm-serializers)
for details); so it seems more hassle than worth.
Performance, as per jvm-serializers, also does not rank anywhere near top of Java JSON libraries, so this does not seem like a big omission.

### Json-smart

* Home page: http://code.google.com/p/json-smart/
* Version tested: 2.0-RC2

Simply, does not work. Although I found a way that should allow Bean conversions, code just mysteriously
fails with a class-loading error.

If anyone can suggest a fix or work-around, I will be happy to include this in tests as well.
Results from sources other than project home page suggest not-so-stellar performance (like jvm-serializers).

