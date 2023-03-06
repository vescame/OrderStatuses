package vescame.orderstatuses.usecases.item.persistence;

public interface ItemRepository {

    boolean existsItemById(Long itemId);
}
