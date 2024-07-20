package org.example;

import java.io.*;
import java.util.List;

public class FileManager {
    public static final String PATH_INPUT_DEFAULT = "./";
    private final HandlerArgs handlerArgs;
    private final Parser parser = new Parser(this);

    public FileManager(HandlerArgs handlerArgs) {
        this.handlerArgs = handlerArgs;
    }

    public void checkAddDataInFile(boolean isAddInFile, String pathOutput, String  prefix) {

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
                System.out.println("Ошибка при записи в файл.");
            }
        }
    }

    public void readFiles(List<String> files) {
        for (String nameFiles : files) {
            extractionDataFromFile(nameFiles);
        }
    }

    private void extractionDataFromFile(String nameFile) {

        File file = new File(nameFile);

        if (!file.exists()) {
            System.out.println("Указанный файл '" + file + "' не существует!");
        }

        try (Reader fileReader = new FileReader(PATH_INPUT_DEFAULT + file)) {

            BufferedReader br = new BufferedReader(fileReader);
            String line;

            while ((line = br.readLine()) != null) {
                parser.parseLine(line, handlerArgs.getPathOutput(), handlerArgs.getPrefix());
            }

            br.close();
        } catch (IOException ignored) { }
    }

    public boolean writeData(String data, String pathOutFile) {

        File file = new File(pathOutFile);

        try {
            file.createNewFile();
        } catch (IOException exception) {
            System.out.println("При создании файла " + file + " произошла ошибка. Указанный путь не существует!");
        }

        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(data + "\r\n");
            return true;
        } catch (IOException exception) {
            System.out.println("Ошибка при записи в файл " + file + ". Файл или путь не существуют!");
            return false;
        }
    }

    public Parser getParser() {
        return parser;
    }
}
