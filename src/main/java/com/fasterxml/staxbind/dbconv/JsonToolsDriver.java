package com.fasterxml.staxbind.dbconv;

import org.codehaus.staxbind.std.StdJsonToolsConverter;

/**
 * Driver that uses Json-tools-based data binding for JSON serialization
 */
public final class JsonToolsDriver
    extends DbconvDriver
{
    public JsonToolsDriver() throws Exception
    {
        super(new StdJsonToolsConverter<DbData>(DbData.class));
    }
}
