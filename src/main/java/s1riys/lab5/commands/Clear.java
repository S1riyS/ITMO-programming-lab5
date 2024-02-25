package s1riys.lab5.commands;

import s1riys.lab5.commands.utils.ExitCode;
import s1riys.lab5.console.IConsole;
import s1riys.lab5.managers.CollectionManager;

public class Clear extends Command {
    private final CollectionManager collectionManager;

    public Clear(IConsole console, CollectionManager collectionManager) {
        super(console, "clear", "Очищает коллекцию", "clear");
        this.collectionManager = collectionManager;
    }

    @Override
    public ExitCode execute(String[] args) {
        collectionManager.clear();
        console.println("Коллекция очищена");
        return ExitCode.OK;
    }
}
