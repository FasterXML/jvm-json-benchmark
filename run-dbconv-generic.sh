#!/bin/sh

java -cp lib/\* \
 -Djapex.inputDir=data/db100.xml \
 -Djapex.warmupTime=5 \
 -Djapex.runTime=15 \
 -Djapex.warmupsPerDriver=1 \
 -Djapex.runsPerDriver=1 \
 com.sun.japex.Japex testcfg/dbconv-generic.xml

