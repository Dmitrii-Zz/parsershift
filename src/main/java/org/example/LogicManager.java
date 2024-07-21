package org.example;

public class LogicManager {
    private final HandlerArgs handlerArgs = new HandlerArgs();
    private final FileManager fileManager = new FileManager(handlerArgs);

    public void start(String[] args) {

        if (!handlerArgs.cultivationInputArgs(args)) {
            return;
        }

        fileManager.checkAddDataInFile(handlerArgs.isAddInFile(),
                                       handlerArgs.getPathOutput(),
                                       handlerArgs.getPrefix());

        fileManager.readFiles(handlerArgs.getFiles());

        if (handlerArgs.isShortStats()) {
            fileManager.getParser().getStatistic().printShortStat();
        } else {
            fileManager.getParser().getStatistic().printFullStat();
        }
    }
}
