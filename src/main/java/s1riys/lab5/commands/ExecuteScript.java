package s1riys.lab5.commands;

import s1riys.lab5.commands.utils.ExitCode;
import s1riys.lab5.console.IConsole;

public class ExecuteScript extends Command {
    public ExecuteScript(IConsole console) {
        super(
                console,
                "execute_script",
                "Считывает и исполняет скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.",
                "execute_script <file_name>");
    }

    @Override
    public ExitCode execute(String[] args) {
        if (args[0].isEmpty()) {
            console.printError("Аргумент file_name не может быть пустым");
            return ExitCode.ERROR;
        }
        console.println("Выполнение скрипта '" + args[0] + "'...");
        return ExitCode.OK;
    }
}
