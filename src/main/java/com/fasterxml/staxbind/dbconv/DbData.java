package com.fasterxml.staxbind.dbconv;

import java.util.*;

import javax.xml.bind.annotation.*;

import com.fasterxml.staxbind.std.StdItem;

/**
 * Simple bean class to contain all "DB data" read from data files.
 *<p>
 * Some implementation notes:
 *<ul>
 * <li>Field names do not use prefix/suffix, to make life easier
 *    for XStream
 *  </li>
 * <li>Plural name "rows" has been changed to "row", because the
 *   canonical xml format uses "row" as tag.
 *  </li>
 *</ul>
 */
@SuppressWarnings("serial")
@XmlRootElement(name="table") // needed by JAXB
public final class DbData
    extends StdItem<DbData>
{
    List<DbRow> rows;

    public DbData() { }

    /*
    ///////////////////////////////////////////////////
    // Mutators
    ///////////////////////////////////////////////////
     */

    public void setRow(List<DbRow> r) { rows = r; }
    public void addRow(DbRow row) { getRow().add(row); }

    /*
    ///////////////////////////////////////////////////
    // Accessors
    ///////////////////////////////////////////////////
     */

    public int size() { return (rows == null) ? 0 : rows.size(); }

    public Iterator<DbRow> rows() { return getRow().iterator(); }

    /**
     *<p>
     * Note: name uses singular row just to make life easier with
     * JAXB and other name convention based tools.
     */
    public List<DbRow> getRow()
    {
        // must return non-null for JAXB to work
        if (rows == null) {
            rows = new ArrayList<DbRow>(100);
        }
        return rows;
    }

    /*
    ///////////////////////////////////////////////////
    // Std methods
    ///////////////////////////////////////////////////
     */

    @Override
    public boolean _equals(DbData other)
    {
        List<DbRow> rows1 = this.getRow();
        List<DbRow> rows2 = other.getRow();
        if (rows1.size() != rows2.size()) {
            return false;
        }
        for (int i = 0, len = rows1.size(); i < len; ++i) {
            if (!rows1.get(i).equals(rows2.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("[DBData: ");
        sb.append(getRow().size()).append(" entries]");
        return sb.toString();
    }
}
