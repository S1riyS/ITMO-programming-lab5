package s1riys.lab5.commands;

import s1riys.lab5.commands.utils.Executable;
import s1riys.lab5.console.IConsole;

public abstract class Command implements Executable {
    protected final IConsole console;
    private final String keyword;
    private final String description;
    private final String signature;

    public Command(IConsole console, String keyword, String description, String signature) {
        this.console = console;
        this.keyword = keyword;
        this.description = description;
        this.signature = signature;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getDescription() {
        return description;
    }

    public String getSignature() {
        return signature;
    }

    @Override
    public String toString() {
        return "Command{" +
                "name='" + keyword + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
