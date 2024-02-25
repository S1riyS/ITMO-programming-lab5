package s1riys.lab5.commands;

import s1riys.lab5.commands.utils.ExitCode;
import s1riys.lab5.console.IConsole;
import s1riys.lab5.managers.CollectionManager;

public class Info extends Command {
    private CollectionManager collectionManager;

    public Info(IConsole console, CollectionManager collectionManager) {
        super(
                console,
                "info",
                "Выводит в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)",
                "info"
        );
        this.collectionManager = collectionManager;
    }

    @Override
    public ExitCode execute(String[] args) {
        console.println("Тип коллекции: " + collectionManager.getCollectionType());
        console.println("Количество элементов: " + collectionManager.getCollectionSize());
        console.println("Дата инициализации: " + collectionManager.getInitDate());
        console.println("Дата сохранения: " + collectionManager.getSaveTime());
        return ExitCode.OK;
    }
}
