package s1riys.lab5.commands;

import s1riys.lab5.commands.utils.ExitCode;
import s1riys.lab5.console.IConsole;

public class Exit extends Command {
    public Exit(IConsole console) {
        super(console, "exit", "Завершает программу (без сохранения в файл)", "exit");
    }

    @Override
    public ExitCode execute(String[] args) {
        return ExitCode.EXIT;
    }
}
