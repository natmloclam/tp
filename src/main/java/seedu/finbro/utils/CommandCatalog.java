package seedu.finbro.utils;

import seedu.finbro.commands.*;
import seedu.finbro.commands.HelpCommand;

import java.util.List;

public final class CommandCatalog {
    private static final List<Command> SUPPORTED_COMMANDS = List.of(
            new HelpCommand(),
            new AddCommand(),
            new DeleteCommand(),
            new EditLimitCommand(),
            new SetLimitCommand(),
            new ViewCommand()
    );

    public static List<Command> getSupportedCommands() {
        return SUPPORTED_COMMANDS;
    }
}