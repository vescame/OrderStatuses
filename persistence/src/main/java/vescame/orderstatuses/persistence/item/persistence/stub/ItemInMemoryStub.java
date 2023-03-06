package vescame.orderstatuses.persistence.item.persistence.stub;

import vescame.orderstatuses.persistence.item.ItemEntity;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public class ItemInMemoryStub {

    public static Collection<ItemEntity> getItems() {
        return itemList;
    }

    private static final Collection<ItemEntity> itemList = List.of(
            new ItemEntity(
                    "pencil",
                    "Pencil",
                    BigDecimal.valueOf(0.99)
            ),
            new ItemEntity(
                    "mug",
                    "Black detailed mug",
                    BigDecimal.valueOf(5.50)
            ),
            new ItemEntity(
                    "Soda",
                    "Flavored soda",
                    BigDecimal.valueOf(4.00)
            ),
            new ItemEntity(
                    "Plate",
                    "Common kitchen plate",
                    BigDecimal.valueOf(2.39)
            )
    );
}
