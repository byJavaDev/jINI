package de.byjavadev.jini.section;

import java.util.LinkedHashSet;

public class DefaultIniSection extends IniSection
{
    public DefaultIniSection()
    {
        super(null, new LinkedHashSet<>());
    }
}
