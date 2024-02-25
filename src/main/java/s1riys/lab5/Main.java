package s1riys.lab5;

import s1riys.lab5.commands.*;
import s1riys.lab5.console.StandardConsole;
import s1riys.lab5.managers.CollectionManager;
import s1riys.lab5.managers.CommandManager;
import s1riys.lab5.managers.DumpManager;
import s1riys.lab5.managers.RuntimeManager;

public class Main {
    public static void main(String[] args) {
        StandardConsole console = new StandardConsole();

        if (args.length == 0) {
            console.println("Введите имя загружаемого файла как аргумент командной строки");
            System.exit(1);
        } else if (args.length > 1) {
            console.println("Программа принимает 1 аргумент командной строки - имя файла (передано " + args.length + ")");
            System.exit(1);
        }

        DumpManager dumpManager = new DumpManager(args[0], console);
        CollectionManager collectionManager = new CollectionManager(dumpManager);
        CommandManager commandManager = new CommandManager();

        commandManager.register(
                new Help(console, commandManager),
                new Info(console, collectionManager),
                new Show(console, collectionManager),
                new Insert(console, collectionManager),
                new Update(console, collectionManager),
                new RemoveKey(console, collectionManager),
                new Clear(console, collectionManager),
                new Save(console, collectionManager),
                new ExecuteScript(console),
                new Exit(console),
                new RemoveGreater(console, collectionManager),
                new RemoveLower(console, collectionManager),
                new RemoveGreaterKey(console, collectionManager),
                new MaxByCreationDate(console, collectionManager),
                new FilterByManufacturer(console, collectionManager),
                new PrintDescending(console, collectionManager)
        );

        RuntimeManager runtimeManager = new RuntimeManager(console, commandManager);
        console.println("Добро пожаловать! Чтобы посмотреть список доступных команд, введите 'help'");
        runtimeManager.interactiveMode();
    }
}