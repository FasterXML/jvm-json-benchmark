#!/bin/sh

java -cp lib/\*:target/jvm-json-benchmark-0.5.0-SNAPSHOT.jar \
   -server -Xms256m -Xmx256m \
   com.fasterxml.jvmjsonperf.caliper.CaliperLauncher \
   com.fasterxml.jvmjsonperf.caliper.TwitterCaliperReadTest \
  --trials 3
