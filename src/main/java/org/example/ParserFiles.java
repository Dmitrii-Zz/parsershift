package org.example;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ParserFiles {
    private static final String PATH_INPUT_DEFAULT = "./";
    private final Statistic statistic = new Statistic();
    private boolean isShortStats = false;
    private boolean isFullStats = false;
    private boolean isAddInFile = false;
    private boolean isPathOutput = false;
    private boolean isPrefix = false;
    private final List<String> files = new ArrayList<>();
    private String prefix = "";
    private String pathOutput;


    public void cultivationInputArgs(String[] args) {

        for (int i = 0; i < args.length; i++) {

            switch (args[i]) {
                case "-o":

                    if (isPathOutput) {
                        System.out.println("Ключ " + args[i] + " уже был!");
                        return;
                    }

                    isPathOutput = true;
                    pathOutput = args[i + 1] + "/";
                    i++;
                    break;
                case "-p":

                    if (isPrefix) {
                        System.out.println("Ключ " + args[i] + " уже был!");
                        return;
                    }

                    isPrefix = true;
                    prefix = args[i + 1];
                    i++;
                    break;
                case "-a":

                    if (isAddInFile) {
                        System.out.println("Ключ " + args[i] + " уже был!");
                        return;
                    }

                    isAddInFile = true;
                    break;
                case "-s":

                    if (isShortStats) {
                        System.out.println("Ключ " + args[i] + " уже был!");
                        return;
                    } else if (isFullStats) {
                        System.out.println("Уже выбрана полная статистика!");
                        return;
                    }

                    isShortStats = true;
                    break;
                case "-f":

                    if (isFullStats) {
                        System.out.println("Ключ " + args[i] + " уже был!");
                        return;
                    } else if (isShortStats) {
                        System.out.println("Уже выбрана короткая статистика!");
                        return;
                    }

                    isFullStats = true;
                    break;
                default:

                    if (args[i].contains(".txt")) {
                        files.add(args[i]);
                    } else {
                        System.out.println("Ошибка ввода параметров: " + args[i]);
                        return;
                    }

                    break;
            }
        }

        if (!isPathOutput) {
            pathOutput = PATH_INPUT_DEFAULT;
        }

        checkAddDataInFile();
        readFiles();

        if (isShortStats) {
            statistic.printShortStat();
        } else {
            statistic.printFullStat();
        }
    }

    private void readFiles() {
        for (String nameFiles : files) {
            extractionDataFromFile(nameFiles);
        }
    }

    private void checkAddDataInFile() {

        if (isAddInFile) {
            return;
        }

        String pathOutFile = pathOutput + prefix;
        List<String> nameFiles = List.of(pathOutFile + "integers.txt",
                pathOutFile + "floats.txt", pathOutFile + "strings.txt");

        for (String nameFile : nameFiles) {

            File file = new File(nameFile);

            if (!file.exists()) {
                continue;
            }

            try (FileWriter writer = new FileWriter(nameFile)) {
                writer.write("");
            } catch (IOException exception) {
                System.out.println("Ошибка при записи в файл");
            }
        }
    }

    private void extractionDataFromFile(String nameFile) {

        File file = new File(nameFile);

        try (Reader fileReader = new FileReader(PATH_INPUT_DEFAULT + file)) {

            BufferedReader br = new BufferedReader(fileReader);
            String line;

            while ((line = br.readLine()) != null) {
                parseLine(line);
            }

            br.close();
        } catch (IOException ignored) { }
    }

    public void parseLine(String line) {

        try {
            BigInteger integer = new BigInteger(line);
            String pathOutFile = pathOutput + prefix + "integers.txt";
            writeData(String.valueOf(integer), pathOutFile);
            statistic.statInteger(integer);
            return;
        } catch (NumberFormatException e) {
            System.out.println("Это не целое число!");
        }

        try {
            BigDecimal aFloat = new BigDecimal(line);
            String pathOutFile = pathOutput + prefix + "floats.txt";
            writeData(String.valueOf(aFloat), pathOutFile);
            statistic.statFloat(aFloat);
            return;
        } catch (NumberFormatException e) {
            System.out.println("Это не дробное число число!");
        }

        String pathOutFile = pathOutput + prefix + "strings.txt";
        writeData(line, pathOutFile);
        statistic.statString(line);
    }

    public void writeData(String data, String pathOutFile) {

        File file = new File(pathOutFile);

        try {
            if (file.createNewFile()) {
                System.out.println("Файл создан");
            } else {
                System.out.println("Файл уже существует");
            }
        } catch (IOException exception) {
            System.out.println("При создании файла произошла ошибка - путь не существует или еще что-то там");
        }

        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(data + "\r\n");
        } catch (IOException exception) {
            System.out.println("Ошибка при записи в файл");
        }
    }
}