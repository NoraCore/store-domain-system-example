package by.issoft.krivonos.validators;

import by.issoft.krivonos.domains.Item;
import by.issoft.krivonos.exceptions.WrongValueException;
import org.junit.jupiter.api.Test;

import static by.issoft.krivonos.data.ItemTestDataFactory.anyValidItem;
import static by.issoft.krivonos.data.ItemTestDataFactory.createTestItem;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ItemValidatorTest {

    private final ItemValidator itemValidator = new ItemValidator(5, 100, 35);

    @Test
    public void validateWithValidItemTest() throws WrongValueException {
        Item validItem = anyValidItem();
        boolean result = itemValidator.validate(validItem);
        assertThat(result, is(equalTo(true)));
    }

    @Test
    public void validateWithNullNameTest() {
        Item invalidItem = createTestItem(null, 6);
        assertThrows(WrongValueException.class, () -> itemValidator.validate(invalidItem));
    }

    @Test
    public void validateWithEmptyNameTest() {
        Item invalidItem = createTestItem("", 7);
        assertThrows(WrongValueException.class, () -> itemValidator.validate(invalidItem));
    }

    @Test
    public void validateWithNameOverThanMaxTest() {
        String nameOverThanMaxLength = "this is name for testing method of ItemValidator. ValidateWithNameOverThanMaxTest";
        Item invalidItem = createTestItem(nameOverThanMaxLength, 7);
        assertThrows(WrongValueException.class, () -> itemValidator.validate(invalidItem));
    }

    @Test
    public void validateWithCostLessThanMinTest() {
        Item invalidItem = createTestItem("TestChoco11", 1);
        assertThrows(WrongValueException.class, () -> itemValidator.validate(invalidItem));
    }

    @Test
    public void validateWithCostOverThanMaxTest() {
        Item invalidItem = createTestItem("TestChoco11", 10_000);
        assertThrows(WrongValueException.class, () -> itemValidator.validate(invalidItem));
    }


}