package s1riys.lab5.commands;

import s1riys.lab5.commands.utils.ExitCode;
import s1riys.lab5.console.IConsole;
import s1riys.lab5.exceptions.InvalidFormException;
import s1riys.lab5.managers.CollectionManager;
import s1riys.lab5.models.Product;
import s1riys.lab5.models.forms.ProductForm;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

public class RemoveGreater extends Command {
    private final CollectionManager collectionManager;

    public RemoveGreater(IConsole console, CollectionManager collectionManager) {
        super(
                console,
                "remove_greater",
                "Удаляет из коллекции все элементы, превышающие заданный",
                "remove_greater {element}"
        );
        this.collectionManager = collectionManager;
    }

    @Override
    public ExitCode execute(String[] args) {
        try {
            Product productToCompare = new ProductForm(console).build();
            SortedSet<Product> higherThanProvided = collectionManager.getSortedCollection().tailSet(productToCompare);

            List<Long> toRemove = new ArrayList<>();
            for (Product product : higherThanProvided) {
                toRemove.add(product.getId());
            }
            for (Long id : toRemove) {
                collectionManager.remove(id);
            }

            if (toRemove.isEmpty()) console.println("Подходящих для удаления продуктов нет");
            else console.println("Продукты удалены! (чтобы посмотреть список всех продуктов, используйте 'show')");
        } catch (InvalidFormException e) {
            console.printError("Не удалось обработать продукт");
        }
        return ExitCode.OK;
    }
}
