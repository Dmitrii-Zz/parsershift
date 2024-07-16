package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        boolean isShortStats = false;
        boolean isFullStats = false;
        boolean isAddInFile = false;
        boolean isPathOutput = false;
        boolean isPrefix = false;

        List<String> files = new ArrayList<>();
        String prefix = "";
        String pathInput = "";

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-o":

                    if (isPathOutput) {
                        System.out.println("Ключ " + args[i] + " уже был!");
                        return;
                    }

                    isPathOutput = true;
                    pathInput = args[i + 1];
                    System.out.println("при i = " + i + ": " + args[i]);

                    i++;
                    break;
                case "-p":

                    if (isPrefix) {
                        System.out.println("Ключ " + args[i] + " уже был!");
                        return;
                    }

                    isPrefix = true;
                    prefix = args[i + 1];
                    System.out.println("при i = " + i + ": " + args[i]);
                    i++;

                    break;
                case "-a":

                    if (isAddInFile) {
                        System.out.println("Ключ " + args[i] + " уже был!");
                        return;
                    }

                    isAddInFile = true;

                    System.out.println("при i = " + i + ": " + args[i]);
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

                    System.out.println("при i = " + i + ": " + args[i]);
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

                    System.out.println("при i = " + i + ": " + args[i]);
                    break;
                default:
                    if (args[i].contains(".txt")) {
                        System.out.println(i + " следующий файл " + args[i]);
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
        System.out.println(pathInput);
    }
}