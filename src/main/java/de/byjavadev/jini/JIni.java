package de.byjavadev.jini;

import de.byjavadev.jini.entry.IniEntry;
import de.byjavadev.jini.section.DefaultIniSection;
import de.byjavadev.jini.entry.EmptyIniEntry;
import de.byjavadev.jini.section.IniSection;
import lombok.Getter;
import lombok.ToString;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

@ToString
public class JIni
{
    /** All ini sections in this file */
    @Getter
    private final Set<IniSection> sections = new LinkedHashSet<>();

    public JIni(final File file) throws FileNotFoundException
    {
        this(new FileInputStream(file));
    }

    public JIni(final InputStream inputStream)
    {
        try (final Scanner scanner = new Scanner(inputStream))
        {
            IniSection currentSection = new DefaultIniSection();
            sections.add(currentSection);
            int lineNumber = 0;
            while (scanner.hasNextLine())
            {
                final String line = scanner.nextLine();
                lineNumber++;

                //Filters out comments
                if(line.startsWith(";") || line.isEmpty())
                    continue;

                if(line.startsWith("["))
                {
                    if(!line.endsWith("]"))
                    {
                        throw new IllegalArgumentException("Illegal section start in line " + lineNumber);
                    }

                    if(line.length() <= 2)
                    {
                        throw new IllegalArgumentException("Section start without section name in line " + lineNumber);
                    }

                    final String newSectionName = line.substring(1, line.length() - 1);
                    if(getSection(newSectionName) != null)
                    {
                        throw new IllegalArgumentException("Tried to declare already existing section in line " + lineNumber);
                    }

                    currentSection = new IniSection(newSectionName, new LinkedHashSet<>());
                    sections.add(currentSection);
                    continue;
                }

                final int spiltIndex = line.indexOf("=");
                if(spiltIndex == -1)
                {
                    throw new IllegalArgumentException("Line " + lineNumber + " does not contain data in the following format: key=value");
                }

                final String key = line.substring(0, spiltIndex);
                if(key.length() <= 0)
                {
                    throw new IllegalArgumentException("Line " + lineNumber + " does not provide a key");
                }

                final String value = line.substring(spiltIndex + 1);

                if(currentSection.getEntry(key) != null)
                {
                    throw new IllegalArgumentException("Line " + lineNumber + " tries to declare key which is already present");
                }

                final IniEntry iniEntry;
                if(value.length() > 1)
                {
                    iniEntry = new IniEntry(key, value, lineNumber);
                } else
                {
                    iniEntry = new EmptyIniEntry(key, lineNumber);
                }

                currentSection.getEntries().add(iniEntry);
            }
        }
    }

    /**
     * Retrieves the section by its name
     * @param name the name or {@code null} for the default section
     * @return the section or {@code null} if not present
     */

    public IniSection getSection(final String name)
    {
        return getSections().stream().filter(section -> section.getName() != null ? section.getName().equals(name) : name == null).findFirst().orElse(null);
    }

    /**
     * @return the default section
     */

    public IniSection getDefaultSection()
    {
        return getSection(null);
    }

}
