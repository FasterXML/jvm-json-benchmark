package com.fasterxml.staxbind.dbconv;

import java.util.*;

/**
 * Simple bean class to contain one row-full of data that is part
 * of {@link DbData} data set.
 *<p>
 * Some implementation notes:
 *<ul>
 * <li>Field names do not use prefix/suffix, to make life easier
 *    for XStream
 *  </li>
 */
@SuppressWarnings("serial")
public final class DbRow
    implements java.io.Serializable // hessian needs this, as does jdk seri
{
    public enum Field {
        id {
            public void setValue(DbRow row, String value) { row.setId(Long.parseLong(value)); }
        }
        ,firstname {
            public void setValue(DbRow row, String value) { row.setFirstname(value); }
        }
        ,lastname {
            public void setValue(DbRow row, String value) { row.setLastname(value); }
        }
        ,zip {
            public void setValue(DbRow row, String value) { row.setZip(Integer.parseInt(value)); }
        }
        ,street {
            public void setValue(DbRow row, String value) { row.setStreet(value); }
        }
        ,city {
            public void setValue(DbRow row, String value) { row.setCity(value); }
        }
        ,state {
            public void setValue(DbRow row, String value) { row.setState(value); }
        }
        ;

        private Field() { }

        public abstract void setValue(DbRow row, String value);
    }

    final static HashMap<String, Field> _fields = new HashMap<String,Field>();
    static {
        for (Field f : Field.values()) {
            _fields.put(f.name(), f);
        }
    }

    long id;

    String firstname, lastname;
    int zip;
    String street, city, state;

    public DbRow() { }

    /*
    ///////////////////////////////////////////////////
    // Static helper methods
    ///////////////////////////////////////////////////
     */

    public static Field fieldByName(String name)
    {
        return _fields.get(name);
    }

    /*
    ///////////////////////////////////////////////////
    // Mutators
    ///////////////////////////////////////////////////
     */

    public void setId(long v) { id = v; }
    public void setZip(int v) { zip = v; }

    public void setFirstname(String v) { firstname = v; }
    public void setLastname(String v) { lastname = v; }
    public void setStreet(String v) { street = v; }
    public void setCity(String v) { city = v; }
    public void setState(String v) { state = v; }

    /**
     * Non-type-safe method that can be used for convenient by-name
     * assignmend
     */
    public boolean assign(String fieldName, String valueStr)
    {
        Field f = _fields.get(fieldName);
        if (f != null) {
            f.setValue(this, valueStr);
            return true;
        }
        return false;
    }

    /*
    ///////////////////////////////////////////////////
    // Accessors
    ///////////////////////////////////////////////////
     */

    public long getId() { return id; }
    public int getZip() { return zip; }

    public String getFirstname() { return firstname; }
    public String getLastname() { return lastname; }
    public String getStreet() { return street; }
    public String getCity() { return city; }
    public String getState() { return state; }

    /*
    ///////////////////////////////////////////////////
    // Std methods
    ///////////////////////////////////////////////////
     */

    @Override
        public boolean equals(Object o)
    {
        if (o == this) return true;
        if (o == null) return false;
        if (o.getClass() != getClass()) return false;

        DbRow other = DbRow.class.cast(o);
        return (this.id == other.id)
            && (this.zip == other.zip)
            && (this.firstname.equals(other.firstname))
            && (this.lastname.equals(other.lastname))
            && (this.street.equals(other.street))
            && (this.city.equals(other.city))
            && (this.state.equals(other.state))
            ;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("[DBRow:");
        sb.append(" id: ").append(id);
        sb.append(" name: ").append(firstname).append(' ').append(lastname);
        sb.append(" address: ");
        sb.append(street).append(' ').append(city).append(' ').append(state);
        sb.append(' ').append(zip);
        sb.append("]");
        return sb.toString();
    }
}
