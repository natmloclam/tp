package seedu.finbro;

import org.junit.jupiter.api.Test;
import seedu.finbro.exception.FinbroException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    public void parse_addValidExpense_expenseAdded() throws FinbroException {
        ExpenseList expenses = new ExpenseList();
        Ui ui = new Ui();

        Parser.parse("add 12.50 food 15/03/2026", expenses, ui);

        assertEquals(1, expenses.size());
    }

    @Test
    public void parse_addInvalidAmount_exceptionThrown() {
        ExpenseList expenses = new ExpenseList();
        Ui ui = new Ui();

        FinbroException exception = assertThrows(FinbroException.class, () ->
                Parser.parse("add abc food 15/03/2026", expenses, ui));

        assertEquals("Amount must be a number.", exception.getMessage());
    }
}