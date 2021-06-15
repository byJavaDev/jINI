package de.byjavadev.jini.entry;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.File;

/**
 * Represents a key-value entry
 */

@Getter
@ToString
@RequiredArgsConstructor
public class IniEntry
{
    private final String key;
    private final String value;
    private final int lineNumber;

    /**
     * Determines the boolean value where:
     * <li>true</li>
     * <li>yes</li>
     * <li>1</li>
     * stand for {@code true},
     * <li>false</li>
     * <li>no</li>
     * <li>0</li>
     * stand for {@code false}
     * @return the boolean value ({@code true} or {@code false})
     * @throws IllegalArgumentException if this does not store a boolean value
     */

    public boolean getAsBoolean()
    {
        switch (getValue().toLowerCase())
        {
            case "false":
            case "no":
            case "0":
                return false;
            case "true":
            case "yes":
            case "1":
                return true;
            default:
                throw new IllegalArgumentException(value + "Line " + lineNumber + " can't be parsed to boolean");
        }
    }

    public File getAsFile()
    {
        return new File(getValue());
    }
}
