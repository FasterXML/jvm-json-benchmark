package com.fasterxml.jvmjsonperf.dbconv;

/**
 * Driver that uses "manual" (hand-written) serialization with Jackson
 * (compared to Bean-generated one)
 */
public final class JacksonDriverManual
    extends DbconvDriver
{
    public JacksonDriverManual() throws Exception
    {
        super(new JacksonConverterManual());
    }
}
