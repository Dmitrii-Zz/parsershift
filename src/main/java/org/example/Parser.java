package org.example;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Parser {
    private final FileManager fileManager;
    private final Statistic statistic = new Statistic();

    public Parser(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    public void parseLine(String line, String pathOutput, String prefix) {

        try {
            BigInteger integer = new BigInteger(line);
            String pathOutFile = pathOutput + prefix + "integers.txt";

            if (fileManager.writeData(String.valueOf(integer), pathOutFile)) {
                statistic.statInteger(integer);
            }

            return;
        } catch (NumberFormatException ignored) { }

        try {
            BigDecimal aFloat = new BigDecimal(line);
            String pathOutFile = pathOutput + prefix + "floats.txt";

            if (fileManager.writeData(String.valueOf(aFloat), pathOutFile)) {
                statistic.statFloat(aFloat);
            }

            return;
        } catch (NumberFormatException ignored) { }

        if (line.isEmpty()) {
            return;
        }

        String pathOutFile = pathOutput + prefix + "strings.txt";

        if (fileManager.writeData(line, pathOutFile)) {
            statistic.statString(line);
        }
    }

    public Statistic getStatistic() {
        return statistic;
    }
}
