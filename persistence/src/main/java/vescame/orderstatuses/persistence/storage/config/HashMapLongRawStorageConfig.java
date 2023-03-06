package vescame.orderstatuses.persistence.storage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vescame.orderstatuses.persistence.item.ItemEntity;
import vescame.orderstatuses.persistence.item.persistence.stub.ItemInMemoryStub;
import vescame.orderstatuses.persistence.order.OrderEntity;
import vescame.orderstatuses.persistence.order.OrderLineEntity;
import vescame.orderstatuses.persistence.storage.map.HashMapLongRawStorage;

@Configuration
public class HashMapLongRawStorageConfig {

    @Bean
    public HashMapLongRawStorage<OrderEntity> orderEntityStorage() {
        return new HashMapLongRawStorage<>();
    }

    @Bean
    public HashMapLongRawStorage<OrderLineEntity> orderLineEntityStorage() {
        return new HashMapLongRawStorage<>();
    }

    @Bean
    public HashMapLongRawStorage<ItemEntity> itemEntityStorage() {
        HashMapLongRawStorage<ItemEntity> storage = new HashMapLongRawStorage<>();
        populateItems(storage);
        return storage;
    }

    private void populateItems(HashMapLongRawStorage<ItemEntity> hashMapLongRawStorage) {
        if (!batchCreateRan) {
            for (ItemEntity item : ItemInMemoryStub.getItems()) {
                hashMapLongRawStorage.add(item);
            }
            batchCreateRan = !batchCreateRan;
        }
    }

    private static boolean batchCreateRan = false;
}
