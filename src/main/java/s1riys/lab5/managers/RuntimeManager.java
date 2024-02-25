package s1riys.lab5.managers;

import s1riys.lab5.commands.Command;
import s1riys.lab5.commands.utils.ExitCode;
import s1riys.lab5.console.IConsole;
import s1riys.lab5.exceptions.ScriptsRecursionException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class RuntimeManager {
    IConsole console;
    CommandManager commandManager;
    private final List<String> scriptStack = new ArrayList<>();

    public RuntimeManager(IConsole console, CommandManager commandManager) {
        this.console = console;
        this.commandManager = commandManager;
    }

    private String[] parseCommand(String input) {
        input += " ";
        String commandKeyword = input.split(" ", 2)[0];
        String commandArgs = input.split(" ", 2)[1].trim();
        return new String[]{commandKeyword, commandArgs};
    }

    /**
    * Handles exit
    */
    private void handleExit() {
        console.println("Работа завершена");
        System.exit(0);
    }

    /**
     * Starts interactive mode
     */
    public void interactiveMode() {
        ScannerManager.setScanner(new Scanner(System.in));
        ScannerManager.setInteractiveMode();
        ExitCode exitCode;
        do {
            console.ps1();
            try {
                String input = ScannerManager.getInput();
                String commandKeyword = parseCommand(input)[0];
                String commandArgs = parseCommand(input)[1];

                commandManager.addToHistory(commandKeyword);
                exitCode = launch(commandKeyword, commandArgs.split(" "));
            } catch (NoSuchElementException exception) {
                exitCode = ExitCode.EXIT;
            }
        } while (!exitCode.equals(ExitCode.EXIT));
        handleExit();
    }

    /**
     * Starts script mode
     * @param filename Name of file with script to execute
     */
    public ExitCode scriptMode(String filename) {
        scriptStack.add(filename);

        try (Scanner scriptScanner = new Scanner(new File(filename))) {
            ExitCode exitCode;
            if (!scriptScanner.hasNext()) throw new NoSuchElementException();

            Scanner previousScanner = ScannerManager.getScanner();
            ScannerManager.setScanner(scriptScanner);
            ScannerManager.setScriptMode();

            do {
                String input = ScannerManager.getInput();
                String commandKeyword = parseCommand(input)[0];
                String[] commandArgs = parseCommand(input)[1].split(" ");

                console.ps1();
                console.println(input);

                if (commandKeyword.equals("execute_script")) {
                    for (String scriptName : scriptStack) {
                        if (scriptName.equals(commandArgs[0])) throw new ScriptsRecursionException(scriptName);
                    }
                }

                exitCode = launch(commandKeyword, commandArgs);
            } while (exitCode == ExitCode.OK && scriptScanner.hasNextLine());

            ScannerManager.setScanner(previousScanner);
            ScannerManager.setInteractiveMode();
            return exitCode;

        } catch (FileNotFoundException exception) {
            console.printError("Файл со скриптом не найден!");
        } catch (NoSuchElementException exception) {
            console.printError("Файл со скриптом пуст!");
        } catch (ScriptsRecursionException exception) {
            StringJoiner stringJoiner = new StringJoiner(" -> ");
            for (String scriptName : scriptStack) {
                stringJoiner.add(scriptName);
            }
            stringJoiner.add(exception.recursionCause);
            console.printError("Вызванный скрипт порождает рекурсию! (" + stringJoiner + ")");
        } finally {
            scriptStack.remove(scriptStack.size() - 1);
        }
        return ExitCode.ERROR;
    }

    /**
     * Launches command
     * @param keyword Keyword of command
     * @param args Arguments of command
     */
    private ExitCode launch(String keyword, String[] args) {
        if (keyword.isEmpty()) return ExitCode.OK;

        Command command = commandManager.getCommand(keyword);
        if (command == null) {
            console.printError("Команда '" + keyword + "' не найдена (используйте 'help' для справки)");
            return ExitCode.ERROR;
        }
        ;
        switch (keyword) {
            case "execute_script" -> {
                if (command.execute(args).equals(ExitCode.ERROR)) return ExitCode.ERROR;
                return scriptMode(args[0]);
            }
            default -> {
                return command.execute(args);
            }
        }
    }
}
