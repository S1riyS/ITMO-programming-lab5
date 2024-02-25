package s1riys.lab5.commands;

import s1riys.lab5.commands.utils.ExitCode;
import s1riys.lab5.console.IConsole;
import s1riys.lab5.managers.CollectionManager;
import s1riys.lab5.models.Product;

public class PrintDescending extends Command {
    private final CollectionManager collectionManager;

    public PrintDescending(IConsole console, CollectionManager collectionManager) {
        super(console, "print_descending", "Выводит элементы коллекции в порядке убывания", "print_descending");
        this.collectionManager = collectionManager;
    }

    @Override
    public ExitCode execute(String[] args) {
        for (Product product : collectionManager.getSortedCollection().descendingSet()) {
            console.println(product);
        }
        return ExitCode.OK;
    }
}
