package s1riys.lab5.commands;

import s1riys.lab5.commands.utils.ExitCode;
import s1riys.lab5.console.IConsole;
import s1riys.lab5.exceptions.InvalidFormException;
import s1riys.lab5.managers.CollectionManager;
import s1riys.lab5.models.Organization;
import s1riys.lab5.models.Product;
import s1riys.lab5.models.forms.OrganizationForm;

import java.util.Objects;

public class FilterByManufacturer extends Command {
    private final CollectionManager collectionManager;

    public FilterByManufacturer(IConsole console, CollectionManager collectionManager) {
        super(
                console,
                "filter_by_manufacturer",
                "Выводит элементы, значение поля manufacturer которых равно заданному",
                "filter_by_manufacturer {manufacturer}"
        );
        this.collectionManager = collectionManager;
    }

    @Override
    public ExitCode execute(String[] args) {
        try {
            Organization targetOrganization = new OrganizationForm(console).build();
            for (Product product : collectionManager.getDefaultCollection().values()) {
                Organization currentOrganisation = product.getManufacturer();
                if (Objects.equals(currentOrganisation, targetOrganization)) console.println(product);
            }
        } catch (InvalidFormException e) {
            console.printError("Не удалось обработать организацию");
        }
        return ExitCode.OK;
    }
}
