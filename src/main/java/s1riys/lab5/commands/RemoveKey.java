package s1riys.lab5.commands;

import s1riys.lab5.commands.utils.ExitCode;
import s1riys.lab5.console.IConsole;
import s1riys.lab5.exceptions.NotFoundException;
import s1riys.lab5.managers.CollectionManager;

public class RemoveKey extends Command {
    private final CollectionManager collectionManager;

    public RemoveKey(IConsole console, CollectionManager collectionManager) {
        super(
                console,
                "remove_key",
                "Удаляет элемент из коллекции по его ключу",
                "remove_key <id>"
        );
        this.collectionManager = collectionManager;
    }

    @Override
    public ExitCode execute(String[] args) {
        try {
            Long id = Long.parseLong(args[0]);
            if (!collectionManager.getDefaultCollection().containsKey(id)) throw new NotFoundException();

            collectionManager.remove(id);
            console.println("Продукт удален! (чтобы посмотреть список всех продуктов, используйте 'show')");
        } catch (NotFoundException e) {
            console.printError("Продукт с заданным ключом не найден");
        } catch (NumberFormatException exception) {
            console.printError("id должен быть представлен числом");
        }
        ;
        return ExitCode.OK;
    }
}
