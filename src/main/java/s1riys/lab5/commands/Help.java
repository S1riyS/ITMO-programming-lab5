package s1riys.lab5.commands;

import s1riys.lab5.commands.utils.ExitCode;
import s1riys.lab5.console.IConsole;
import s1riys.lab5.managers.CommandManager;

public class Help extends Command {
    private CommandManager commandManager;

    public Help(IConsole console, CommandManager commandManager) {
        super(console, "help", "Выводит справку по доступным командам", "help");
        this.commandManager = commandManager;
    }

    @Override
    public ExitCode execute(String[] args) {
        for (Command command : commandManager.commandsMap.values()) {
            console.println("* " + command.getSignature() + ": " + command.getDescription());
        }
        return ExitCode.OK;
    }
}
