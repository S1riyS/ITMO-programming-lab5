package s1riys.lab5.commands;

import s1riys.lab5.commands.utils.ExitCode;
import s1riys.lab5.console.IConsole;
import s1riys.lab5.managers.CollectionManager;
import s1riys.lab5.models.Product;

import java.util.Date;

public class MaxByCreationDate extends Command {
    private final CollectionManager collectionManager;

    public MaxByCreationDate(IConsole console, CollectionManager collectionManager) {
        super(
                console,
                "max_by_creation_date",
                "Выводит объект из коллекции, значение поля creationDate которого является максимальным",
                "max_by_creation_date"
        );
        this.collectionManager = collectionManager;
    }

    @Override
    public ExitCode execute(String[] args) {
        Product productWithMaxDate = null;
        for (Product currentProduct : collectionManager.getDefaultCollection().values()) {
            if (productWithMaxDate == null) {
                productWithMaxDate = currentProduct;
                continue;
            }
            Date currentCreationDate = currentProduct.getCreationDate();
            Date maxCreationDate = productWithMaxDate.getCreationDate();
            if (currentCreationDate.after(maxCreationDate)) productWithMaxDate = currentProduct;
        }
        console.println("Последний созданный продукт: " + productWithMaxDate);
        return ExitCode.OK;
    }
}
