package s1riys.lab5.commands;

import s1riys.lab5.commands.utils.ExitCode;
import s1riys.lab5.console.IConsole;
import s1riys.lab5.managers.CollectionManager;

public class Save extends Command {
    private final CollectionManager collectionManager;

    public Save(IConsole console, CollectionManager collectionManager) {
        super(console, "save", "Сохраняет коллекцию в файл", "save");
        this.collectionManager = collectionManager;
    }

    @Override
    public ExitCode execute(String[] args) {
        collectionManager.writeToCSV();
        return ExitCode.OK;
    }
}
