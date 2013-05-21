#!/bin/sh
 
echo "About to run full 'dbconv' test with medium data set"

# Nothing big stored in memory, heap can remain modest 
# -Djapex.runTime=30 \
java -server -cp lib/\* \
 -Xms256m -Xmx256m \
 -Djapex.runsPerDriver=3 \
 -Djapex.warmupTime=5 \
 -Djapex.runTime=30 \
 -Djapex.numberOfThreads=1 \
 -Djapex.reportsDirectory=japex-reports \
 -Djapex.inputDir=data/db-medium/ \
 com.sun.japex.Japex \
 testcfg/dbconv-generic.xml

echo "Done!";
