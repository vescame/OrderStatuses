package vescame.orderstatuses.persistence.storage.map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HashMapLongRawStorageTest {

    private HashMapLongRawStorage<DummyEntity> STORAGE = new HashMapLongRawStorage<>();

    @Test
    public void shouldAddNewEntity() {
        var entity = new DummyEntity(1L);
        assertDoesNotThrow(() -> STORAGE.add(entity));
    }

    @Test
    public void shouldUpdateEntity() {
        var dummyId = 1L;
        var expectedName = "new name";
        var entity = new DummyEntity(dummyId);

        STORAGE.add(entity);

        var updatedEntity = new DummyEntity(dummyId, expectedName);

        assertDoesNotThrow(() -> STORAGE.update(updatedEntity));

        var actual = STORAGE.getById(dummyId);

        assertEquals(expectedName, actual.getName());
    }

    @Test
    public void shouldThrowWhenTryingToUpdateAnNonexistentEntity() {
        var dummyId = 1L;
        var expectedName = "new name";

        var updatedEntity = new DummyEntity(dummyId, expectedName);

        assertThrows(IllegalArgumentException.class, () -> STORAGE.update(updatedEntity));
    }

    @Test
    public void shouldDeleteEntity() {
        var dummyId = 1L;

        var entity = new DummyEntity(dummyId);

        STORAGE.add(entity);

        assertDoesNotThrow(() -> STORAGE.delete(entity));
    }

    @Test
    public void shouldThrowWhenTryingToRemoveAnNonExistentEntity() {
        var dummyId = 1L;

        var entity = new DummyEntity(dummyId);

        assertThrows(IllegalArgumentException.class, () -> STORAGE.delete(entity));
    }

    @Test
    public void shouldGetEntityById() {
        var dummyId = 1L;
        var entity = new DummyEntity(dummyId);

        STORAGE.add(entity);

        var actual = STORAGE.getById(dummyId);

        assertEquals(entity, actual);
    }

    @Test
    public void shouldReturnNullWhenGetByIdIsANonExistentEntity() {
        var dummyId = 1L;

        var actual = STORAGE.getById(dummyId);

        assertNull(actual);
    }

    @Test
    public void shouldGetAll() {
        var dummyId = 1L;
        var entity = new DummyEntity(dummyId);

        var expected = List.of(entity);

        assertDoesNotThrow(() -> STORAGE.add(entity));

        var actual = STORAGE.getAll().stream().toList();

        assertEquals(expected, actual);
    }

    @AfterEach
    public void cleanUpStorage() {
        this.STORAGE = new HashMapLongRawStorage<>();
    }
}