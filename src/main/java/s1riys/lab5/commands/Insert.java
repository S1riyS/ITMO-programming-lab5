package s1riys.lab5.commands;

import s1riys.lab5.commands.utils.ExitCode;
import s1riys.lab5.console.IConsole;
import s1riys.lab5.exceptions.InvalidFormException;
import s1riys.lab5.managers.CollectionManager;
import s1riys.lab5.models.Product;
import s1riys.lab5.models.forms.ProductForm;

public class Insert extends Command {
    private final CollectionManager collectionManager;

    public Insert(IConsole console, CollectionManager collectionManager) {
        super(console, "insert", "Добавить новый элемент", "insert {element}");
        this.collectionManager = collectionManager;
    }

    @Override
    public ExitCode execute(String[] args) {
        try {
            Product product = new ProductForm(console).build();
            collectionManager.add(product);
            console.println("Продукт создан! (чтобы посмотреть список всех продуктов, используйте 'show')");
        } catch (InvalidFormException e) {
            console.printError("Не удалось добавить продукт");
        }
        return ExitCode.OK;
    }
}
