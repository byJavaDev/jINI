package de.byjavadev.jini.section;

import java.util.LinkedHashSet;

/**
 * Represents the default ini section (if no header has been specified)
 */

public class DefaultIniSection extends IniSection
{
    public DefaultIniSection()
    {
        super(null, new LinkedHashSet<>());
    }
}
