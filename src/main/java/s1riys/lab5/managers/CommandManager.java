package s1riys.lab5.managers;

import s1riys.lab5.commands.Command;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * The CommandManager class manages the registration and retrieval of commands, as well as command history.
 */
public class CommandManager {
    public LinkedHashMap<String, Command> commandsMap = new LinkedHashMap<>();
    private final List<String> commandHistory = new ArrayList<>();

    /**
     * Registers one or more commands.
     *
     * @param commands The commands to be registered.
     */
    public void register(Command... commands) {
        for (Command command : commands) {
            commandsMap.put(command.getKeyword(), command);
        }
    }

    /**
     * Retrieves a command based on the provided keyword.
     *
     * @param keyword The keyword of the command to retrieve.
     * @return The Command object associated with the keyword, or null if not found.
     */
    public Command getCommand(String keyword) {
        return commandsMap.get(keyword);
    }

    /**
     * Retrieves the command history.
     *
     * @return The list of commands in the command history.
     */
    public List<String> getCommandHistory() {
        return commandHistory;
    }

    /**
     * Adds a command keyword to the command history.
     *
     * @param keyword The keyword of the command to add to the history.
     */
    public void addToHistory(String keyword) {
        commandHistory.add(keyword);
    }
}
