package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HandlerArgs {
    private final List<String> files = new ArrayList<>();
    private boolean isShortStats = false;
    private boolean isFullStats = false;
    private boolean isAddInFile = false;
    private boolean isPathOutput = false;
    private boolean isPrefix = false;
    private String prefix = "";
    private String pathOutput = FileManager.PATH_INPUT_DEFAULT;

    public boolean cultivationInputArgs(String[] args) {

        for (int i = 0; i < args.length; i++) {

            switch (args[i]) {
                case "-o":

                    if (isPathOutput) {
                        System.out.println("Ключ " + args[i] + " уже был!");
                        return false;
                    }

                    isPathOutput = true;
                    pathOutput = args[i + 1] + "/";

                    File catalog = new File(pathOutput);

                    if (!catalog.exists()) {
                        System.out.println("Путь '" + catalog + "' не существует!");
                        return false;
                    }

                    i++;
                    break;
                case "-p":

                    if (isPrefix) {
                        System.out.println("Ключ " + args[i] + " уже был!");
                        return false;
                    }

                    isPrefix = true;
                    prefix = args[i + 1];
                    i++;
                    break;
                case "-a":

                    if (isAddInFile) {
                        System.out.println("Ключ " + args[i] + " уже был!");
                        return false;
                    }

                    isAddInFile = true;
                    break;
                case "-s":

                    if (isShortStats) {
                        System.out.println("Ключ " + args[i] + " уже был!");
                        return false;
                    } else if (isFullStats) {
                        System.out.println("Уже выбрана полная статистика!");
                        return false;
                    }

                    isShortStats = true;
                    break;
                case "-f":

                    if (isFullStats) {
                        System.out.println("Ключ " + args[i] + " уже был!");
                        return false;
                    } else if (isShortStats) {
                        System.out.println("Уже выбрана короткая статистика!");
                        return false;
                    }

                    isFullStats = true;
                    break;
                default:

                    if (args[i].contains(".txt")) {
                        files.add(args[i]);
                    } else {
                        System.out.println("Ошибка ввода параметров: " + args[i]);
                        return false;
                    }

                    break;
            }
        }

        return true;
    }

    public List<String> getFiles() {
        return files;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getPathOutput() {
        return pathOutput;
    }

    public boolean isAddInFile() {
        return isAddInFile;
    }

    public boolean isShortStats() {
        return isShortStats;
    }
}