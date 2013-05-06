package com.fasterxml.staxbind.dbconv;

import java.io.*;
import java.util.*;

import com.fasterxml.jackson.core.*;

/**
 * Converter that uses Jackson JSON processor for data binding,
 * using hand-written bindings.
 */
public class JacksonConverterManual
    extends DbConverter
{
    final JsonFactory _jsonFactory;

    public JacksonConverterManual()
    {
        _jsonFactory = new JsonFactory();
    }

    @Override
    public DbData readData(InputStream in)
        throws IOException
    {
        JsonParser jp = _jsonFactory.createParser(in);
        DbData result = new DbData();

        if (jp.nextToken() != JsonToken.START_ARRAY) { // data validity check
            throw new IOException("Expected data to be an array");
        }

        // And then all instances
        JsonToken t;
        
        while ((t = jp.nextToken()) != JsonToken.END_ARRAY) {
            if (t != JsonToken.START_OBJECT) { // sanity check, must be this
                throw new IOException("Broken JSON doc: got "+t);
            }
            DbRow row = new DbRow();
            while ((t = jp.nextToken()) != JsonToken.END_OBJECT) {
                if (t != JsonToken.FIELD_NAME) { // sanity check, must be this
                    throw new IOException("Broken JSON doc: got "+t);
                }
                String fn = jp.getCurrentName();
                // Let's move to value
                jp.nextToken();
                DbRow.Field f = DbRow.fieldByName(fn);
                if (f == null) { // sanity check
                    throw new IOException("Unexpected field '"+fn+"': not one of recognized field names");
                }
                switch (f) {
                case id:
                    row.setId(jp.getLongValue());
                    break;
                case firstname:
                    row.setFirstname(jp.getText());
                    break;
                case lastname:
                    row.setLastname(jp.getText());
                    break;
                case zip:
                    row.setZip(jp.getIntValue());
                    break;
                case street:
                    row.setStreet(jp.getText());
                    break;
                case city:
                    row.setCity(jp.getText());
                    break;
                case state:
                    row.setState(jp.getText());
                    break;
                }
            }
            result.addRow(row);
        }

        jp.close();
        return result;
    }

    @Override
    public int writeData(OutputStream out, DbData data) throws Exception
    {
        JsonGenerator jg = _jsonFactory.createGenerator(out, JsonEncoding.UTF8);
        jg.writeStartArray();
        Iterator<DbRow> it = data.rows();

        while (it.hasNext()) {
            DbRow row = it.next();
            jg.writeStartObject();

            jg.writeFieldName(DbRow.Field.id.name());
            jg.writeNumber(row.getId());

            jg.writeFieldName(DbRow.Field.firstname.name());
            jg.writeString(row.getFirstname());

            jg.writeFieldName(DbRow.Field.lastname.name());
            jg.writeString(row.getLastname());

            jg.writeFieldName(DbRow.Field.zip.name());
            jg.writeNumber(row.getZip());

            jg.writeFieldName(DbRow.Field.street.name());
            jg.writeString(row.getStreet());

            jg.writeFieldName(DbRow.Field.city.name());
            jg.writeString(row.getCity());

            jg.writeFieldName(DbRow.Field.state.name());
            jg.writeString(row.getState());

            jg.writeEndObject();
        }
        jg.writeEndArray();
        jg.close();
        return -1;
    }
}
