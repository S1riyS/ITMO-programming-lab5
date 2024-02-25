package s1riys.lab5.commands;

import s1riys.lab5.commands.utils.ExitCode;
import s1riys.lab5.console.IConsole;
import s1riys.lab5.exceptions.NotFoundException;
import s1riys.lab5.exceptions.InvalidFormException;
import s1riys.lab5.managers.CollectionManager;
import s1riys.lab5.models.Product;
import s1riys.lab5.models.forms.ProductForm;

public class Update extends Command {
    private CollectionManager collectionManager;

    public Update(IConsole console, CollectionManager collectionManager) {
        super(
                console,
                "update",
                "Обновляет значение элемента коллекции, id которого равен заданному",
                "update <id> {element}"
        );
        this.collectionManager = collectionManager;
    }

    @Override
    public ExitCode execute(String[] args) {
        try {
            Long id = Long.parseLong(args[0]);
            if (!collectionManager.getDefaultCollection().containsKey(id)) throw new NotFoundException();

            Product product = new ProductForm(console).build();
            collectionManager.update(id, product);
            console.println("Продукт обновлен! (чтобы посмотреть список всех продуктов, используйте 'show')");
        } catch (NotFoundException e) {
            console.printError("Продукт с заданным ключом не найден");
        } catch (NumberFormatException exception) {
            console.printError("id должен быть представлен числом");
        } catch (InvalidFormException e) {
            console.printError("Не удалось обновить продукт");
        }
        return ExitCode.OK;
    }
}
