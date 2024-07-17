package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ParserFiles {
    private static final String PATH_INPUT_DEFAULT = "./src/main/resources/";
    private boolean isShortStats = false;
    private boolean isFullStats = false;
    private boolean isAddInFile = false;
    private boolean isPathOutput = false;
    private boolean isPrefix = false;
    private final List<String> files = new ArrayList<>();
    private String prefix = "";

    private String pathOutput = "./src/main/resources/";

    public void cultivationInputArgs(String [] args) {

        for (int i = 0; i < args.length; i++) {

            switch (args[i]) {
                case "-o":

                    if (isPathOutput) {
                        System.out.println("Ключ " + args[i] + " уже был!");
                        return;
                    }

                    isPathOutput = true;
                    pathOutput = args[i + 1];
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

        System.out.println(files);
        System.out.println(prefix);
        System.out.println(PATH_INPUT_DEFAULT);

        readFiles();
    }

    private void readFiles() {
        for (String nameFiles : files) {
            extractionDataFromFile(nameFiles);
        }
    }

    private void extractionDataFromFile(String nameFile) {

        File file = new File(nameFile);

        try (Reader fileReader = new FileReader(PATH_INPUT_DEFAULT + file)){

            BufferedReader br = new BufferedReader(fileReader);
            String line;

            while((line = br.readLine()) != null) {
                parseLine(line);
            }

            br.close();
        } catch (IOException ignored) { }
    }

    public void parseLine(String line) {

        try {
            int integer = Integer.parseInt(line);
            System.out.println("Парсим целое число - " + integer);
            String pathOutFile = pathOutput + prefix + "integers.txt";

            writeData(String.valueOf(integer), pathOutFile);
            return;
        } catch (NumberFormatException e) {
            System.out.println("Это не целое число!");
        }

        try {
            float aFloat = Float.parseFloat(line);
            System.out.println("Парсим дробное число- " + aFloat);
            String pathOutFile = pathOutput + prefix + "floats.txt";

            writeData(String.valueOf(aFloat), pathOutFile);
            return;
        } catch (NumberFormatException e) {
            System.out.println("Это не дробное число число!");
        }

        System.out.println("Осталась строка - " + line);
        String pathOutFile = pathOutput + prefix + "strings.txt";
        writeData(line, pathOutFile);
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

        if (!isAddInFile) {
            try (FileWriter writer = new FileWriter(file);){
                writer.write("");
            } catch (IOException exception) {
                System.out.println("Ошибка при записи в файл");
            }

            isAddInFile = true;
        }

        try (FileWriter writer = new FileWriter(file, true);){
            writer.write(data + "\r\n");
        } catch (IOException exception) {
            System.out.println("Ошибка при записи в файл");
        }

    }
}
