package com.fasterxml.jvmjsonperf.twitter;

import com.fasterxml.jvmjsonperf.std.StdJacksonConverter;

/**
 * Driver that uses "automatic" (bean/annotation-based) serialization with
 * Jackson (compared to hand-written one)
 */
public final class JacksonDriverDatabind
    extends TwitterDriver
{
    public JacksonDriverDatabind() throws Exception
    {
        super(getConverter());
    }

    public static StdJacksonConverter<TwitterSearch> getConverter() {
        return new StdJacksonConverter<TwitterSearch>(TwitterSearch.class);
    }
}
