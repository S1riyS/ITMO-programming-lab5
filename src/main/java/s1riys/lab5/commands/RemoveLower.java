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

public class RemoveLower extends Command {
    private final CollectionManager collectionManager;

    public RemoveLower(IConsole console, CollectionManager collectionManager) {
        super(
                console,
                "remove_lower",
                "Удаляет из коллекции все элементы, меньшие, чем заданный",
                "remove_lower {element}"
        );
        this.collectionManager = collectionManager;
    }

    @Override
    public ExitCode execute(String[] args) {
        try {
            Product productToCompare = new ProductForm(console).build();
            SortedSet<Product> higherThanProvided = collectionManager.getSortedCollection().headSet(productToCompare);

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
