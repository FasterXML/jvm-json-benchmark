package com.fasterxml.staxbind.dbconv;

import org.codehaus.staxbind.std.StdJacksonConverter;

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
