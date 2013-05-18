#!/bin/sh
 
echo "About to run full 'dbconv' test on JSON drivers, medium data set"

# Nothing big stored in memory, heap can remain modest 
# -Djapex.runTime=30 \
java -server -cp lib/\* \
 -Xmx128M \
 -Djapex.runsPerDriver=3 \
 -Djapex.warmupTime=5 \
 -Djapex.runTime=30 \
 -Djapex.numberOfThreads=1 \
 -Djapex.reportsDirectory=japex-reports \
 -Djapex.inputDir=data/db100.xml \
 com.sun.japex.Japex \
 testcfg/dbconv-json.xml

echo "Done!";
