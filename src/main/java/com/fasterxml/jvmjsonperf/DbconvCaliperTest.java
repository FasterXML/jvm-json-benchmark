package com.fasterxml.jvmjsonperf;

import com.google.caliper.Benchmark;
import com.google.caliper.Param;

public class DbconvCaliperTest
    extends Benchmark
{
    @Param
    public String filename;

    public void timeJacksonManual(int reps) throws Exception
    {
    }
}
