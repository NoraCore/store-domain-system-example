package by.issoft.krivonos.persistences;

import by.issoft.krivonos.domains.Item;
import by.issoft.krivonos.exceptions.ValueIsBusyException;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static by.issoft.krivonos.data.ItemTestDataFactory.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


class ItemInMemoryStorageImplTest {

    private final ItemStorage itemStorage = new ItemInMemoryStorageImpl();

    @Test
    public void savePositiveTest() {
        //given
        Item item1 = anyValidItem();

        //when
        final Item item = itemStorage.save(item1);
        assertThat(item, notNullValue());

        final Item loaded = itemStorage.get(item.getId().toString()).get();
        assertThat(loaded, is(equalTo(item)));
    }

    @Test
    public void saveNullItemTest() {
        assertThrows(NullPointerException.class, () -> itemStorage.save(null));
    }


    @Test
    public void saveItemWithTheSameNameTest() {
        //given
        Item anyItemTest = anyItem();
        Item theSameItemTest = anyItem();

        //when
        itemStorage.save(anyItemTest);
        final Item loaded = itemStorage.get(anyItemTest.getId().toString()).get();

        assertThat(loaded, notNullValue());
        assertThat(loaded, is(equalTo(theSameItemTest)));

        //then
        assertThrows(ValueIsBusyException.class, () -> itemStorage.save(theSameItemTest));
    }

    @Test
    public void deleteTest() {
        //given
        Item validItem = anyValidItem();

        //when
        itemStorage.save(validItem);
        final Item loaded = itemStorage.get(validItem.getId().toString()).get();
        assertThat(loaded, notNullValue());
        itemStorage.delete(loaded);

        //then
        assertThat(loaded, nullValue());
    }

    @Test
    public void updateTest() {
        //given
        Item validItemForUpdate = anyValidItem();
        Item validItemAfterUpdate = anyValidItem();

        //when
        itemStorage.save(validItemForUpdate);
        final Item loaded = itemStorage.get(validItemForUpdate.getId().toString()).get();
        assertThat(loaded, notNullValue());

        final Item itemAfterUpdate = itemStorage.update(loaded, validItemAfterUpdate);

        //then
        assertThat(loaded.getId(), is(equalTo(itemAfterUpdate.getId())));
    }

    @Test
    public void findByCostsTest() {
        //given
        Item anyItem = anyValidItem();

        //when
        itemStorage.save(anyItem);
        final Optional<Item> loaded = itemStorage.get(anyItem.getId().toString());
        assertThat(loaded.get(), notNullValue());

        final List<Optional<Item>> itemsByCosts = itemStorage.findByCosts(loaded.get().getCost());

        //then
        assertThat(itemsByCosts, hasItem(loaded));
    }

    @Test
    public void findByNamesTest() {
        //given
        Item anyItem = anyValidItem();

        //when
        itemStorage.save(anyItem);
        final Optional<Item> loaded = itemStorage.get(anyItem.getId().toString());
        assertThat(loaded.get(), notNullValue());

        final List<Optional<Item>> itemsByNames = itemStorage.findByNames(loaded.get().getNameItem());

        //then
        assertThat(itemsByNames, hasItem(loaded));
    }

}

