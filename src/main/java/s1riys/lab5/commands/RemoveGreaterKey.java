package s1riys.lab5.commands;

import s1riys.lab5.commands.utils.ExitCode;
import s1riys.lab5.console.IConsole;
import s1riys.lab5.managers.CollectionManager;

import java.util.ArrayList;
import java.util.List;

public class RemoveGreaterKey extends Command {
    private final CollectionManager collectionManager;

    public RemoveGreaterKey(IConsole console, CollectionManager collectionManager) {
        super(
                console,
                "remove_greater_key",
                "Удаляет из коллекции все элементы, ключ которых превышает заданный",
                "remove_greater_key <id>"
        );
        this.collectionManager = collectionManager;
    }

    @Override
    public ExitCode execute(String[] args) {
        try {
            Long idThreshold = Long.parseLong(args[0]);

            List<Long> toRemove = new ArrayList<>();
            collectionManager.getDefaultCollection().keySet()
                    .stream()
                    .filter(k -> k > idThreshold)
                    .forEach(toRemove::add);

            if (toRemove.isEmpty()) console.println("Продукты, id которых превышает заданный, не найдены");
            else {
                for (Long id : toRemove) collectionManager.remove(id);
                console.println("Все продукты, id которых превышает заданный, удалены");
            }
        } catch (NumberFormatException exception) {
            console.printError("id должен быть представлен числом");
        }
        return ExitCode.OK;
    }
}
