package seedu.finbro.parser;

import seedu.finbro.utils.ExpenseList;
import seedu.finbro.storage.Storage;
import seedu.finbro.ui.Ui;
import seedu.finbro.commands.*;
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

        if (input.equals(COMMAND_ADD)) {
            throw new FinbroException("Usage: add <amount> <category> <date>");
        }

        if (input.startsWith(COMMAND_HELP)) {
            return new HelpCommand();
        }

        if (input.startsWith(COMMAND_ADD + " ")) {
            return new AddCommand();
        }

        if (input.startsWith(COMMAND_VIEW)) {
            return new ViewCommand();
        }

        if (input.startsWith(COMMAND_DELETE)) {
            return new DeleteCommand();
        }

        if (input.startsWith(COMMAND_SET_LIMIT)) {
            return new SetLimitCommand();
        }
        if (input.equals(COMMAND_EDIT + " " + COMMAND_SET_LIMIT)) {
            return  new EditLimitCommand();
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
