package com.fasterxml.jvmjsonperf.dbconv;

import com.fasterxml.jvmjsonperf.std.StdSmileConverter;

/**
 * Driver that uses automatic (bean/annotation-based) serialization with
 * Jackson, on top of JSON-compatible binary format called "Smile".
 */
public final class JacksonDriverSmileAutomatic
    extends DbconvDriver
{
    public JacksonDriverSmileAutomatic() throws Exception
    {
        super(new StdSmileConverter<DbData>(DbData.class));
    }
}
