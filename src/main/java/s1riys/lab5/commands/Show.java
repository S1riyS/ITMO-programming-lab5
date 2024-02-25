package s1riys.lab5.commands;

import s1riys.lab5.commands.utils.ExitCode;
import s1riys.lab5.console.IConsole;
import s1riys.lab5.managers.CollectionManager;
import s1riys.lab5.models.Product;

import java.util.Collection;

public class Show extends Command {
    private final CollectionManager collectionManager;

    public Show(IConsole console, CollectionManager collectionManager) {
        super(
                console,
                "show",
                "Выводит в стандартный поток вывода все элементы коллекции в строковом представлении",
                "show"
        );
        this.collectionManager = collectionManager;
    }

    @Override
    public ExitCode execute(String[] args) {
        Collection<Product> collection = collectionManager.getDefaultCollection().values();
        if (collection.isEmpty()) {
            console.println("Коллекция пуста");
        } else {
            for (Product product : collectionManager.getDefaultCollection().values()) {
                console.println(product);
            }
        }
        return ExitCode.OK;
    }
}
