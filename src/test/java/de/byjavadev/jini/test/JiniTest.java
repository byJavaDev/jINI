package de.byjavadev.jini.test;

import de.byjavadev.jini.JIni;
import lombok.SneakyThrows;

public class JiniTest
{

    @SneakyThrows
    public static void main(String[] args)
    {
        final JIni jIni = new JIni(JiniTest.class.getResourceAsStream("/test.ini"));
        System.out.println(jIni);
    }

}
