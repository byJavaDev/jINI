package de.byjavadev.jini.entry;

/**
 * Represents an empty/placeholder ini entry where only the key is given
 */

public class EmptyIniEntry extends IniEntry
{
    public EmptyIniEntry(String key, final int lineNumber)
    {
        super(key, "", lineNumber);
    }
}
