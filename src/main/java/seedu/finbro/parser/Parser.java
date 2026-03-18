package seedu.finbro.parser;

import seedu.finbro.utils.ExpenseList;
import seedu.finbro.storage.Storage;
import seedu.finbro.ui.Ui;
import seedu.finbro.commands.AddCommand;
import seedu.finbro.commands.Command;
import seedu.finbro.commands.DeleteCommand;
import seedu.finbro.commands.EditLimitCommand;
import seedu.finbro.commands.HelpCommand;
import seedu.finbro.commands.SetLimitCommand;
import seedu.finbro.commands.ViewCommand;
import seedu.finbro.exception.FinbroException;

public class Parser {
    private static final String COMMAND_ADD = "add";
    private static final String COMMAND_VIEW = "view";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_SET_LIMIT = "limit";
    private static final String COMMAND_EDIT = "edit";
    private static final String COMMAND_HELP = "help";


    public static Command parse(String input, ExpenseList expenses, Ui ui, Storage storage) throws FinbroException {
        input = input.trim();
        String[] parts = input.split("\\s+", 2);
        String commandWord = parts[0];

        switch (commandWord) {
        case COMMAND_HELP:
            return new HelpCommand();

        case COMMAND_ADD:
            if (input.equals(COMMAND_ADD)) {
                throw new FinbroException("Usage: add <amount> <category> <date>");
            }
            return new AddCommand();

        case COMMAND_VIEW:
            return new ViewCommand();

        case COMMAND_DELETE:
            return new DeleteCommand();

        case COMMAND_SET_LIMIT:
            return new SetLimitCommand();

        case COMMAND_EDIT:
            if (parts.length > 1 && parts[1].equals(COMMAND_SET_LIMIT)) {
                return new EditLimitCommand();
            }
            break;

        default:
            break;
        }

        throw new FinbroException("Invalid command.");
    }

    public static double parsePositiveAmount(String input) throws FinbroException {
        double amount;
        try {
            amount = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new FinbroException("Monthly spending limit must be a number");
        }

        if (amount < 0) {
            throw new FinbroException("Monthly spending limit must be at least $0");
        }

        return amount;
    }
}
