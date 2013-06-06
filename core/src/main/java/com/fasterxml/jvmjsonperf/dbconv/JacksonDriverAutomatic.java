package com.fasterxml.jvmjsonperf.dbconv;

import com.fasterxml.jvmjsonperf.std.StdJacksonConverter;

/**
 * Driver that uses "automatic" (bean/annotation-based) serialization with
 * Jackson (compared to hand-written one)
 */
public final class JacksonDriverAutomatic
    extends DbconvDriver
{
    public JacksonDriverAutomatic() throws Exception
    {
        super(new StdJacksonConverter<DbData>(DbData.class));
    }
}
