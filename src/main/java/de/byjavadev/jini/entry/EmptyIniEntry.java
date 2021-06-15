package de.byjavadev.jini.entry;

public class EmptyIniEntry extends IniEntry
{
    public EmptyIniEntry(String key, final int lineNumber)
    {
        super(key, "", lineNumber);
    }
}
