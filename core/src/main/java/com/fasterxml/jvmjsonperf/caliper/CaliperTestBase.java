package com.fasterxml.jvmjsonperf.caliper;

import java.io.*;

import com.google.caliper.Benchmark;

public abstract class CaliperTestBase
    extends Benchmark
{
    protected byte[] readAll(File f)
        throws IOException
    {
        byte[] buffer = new byte[4000];
        ByteArrayOutputStream tmpStream = new ByteArrayOutputStream((int) f.length());
        int count;
        FileInputStream fis = new FileInputStream(f);

        while ((count = fis.read(buffer)) > 0) {
            tmpStream.write(buffer, 0, count);
        }
        fis.close();
        tmpStream.close();
        return tmpStream.toByteArray();
    }
}
