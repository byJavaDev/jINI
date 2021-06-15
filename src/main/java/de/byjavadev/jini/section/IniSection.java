package de.byjavadev.jini.section;

import de.byjavadev.jini.entry.IniEntry;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Set;

/**
 * Represents a configuration section of the .ini file
 */

@Getter
@ToString
@RequiredArgsConstructor
public class IniSection
{
    /** The name of this section */
    private final String name;

    /** All entries in this section*/
    private final Set<IniEntry> entries;

    /**
     * Retrieves an entry from this section
     * @param key the key of the entry
     * @return the entry or {@code null} if not present
     */

    public IniEntry getEntry(final String key)
    {
        return entries.stream().filter(iniEntry -> iniEntry.getKey().equals(key)).findFirst().orElse(null);
    }
}
