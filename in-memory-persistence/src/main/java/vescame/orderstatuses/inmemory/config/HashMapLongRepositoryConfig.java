package vescame.orderstatuses.inmemory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vescame.orderstatuses.inmemory.item.persistence.stub.ItemInMemoryStub;
import vescame.orderstatuses.inmemory.hashmap.HashMapLongRepository;
import vescame.orderstatuses.persistence.PersistenceRepository;
import vescame.orderstatuses.persistence.item.ItemEntity;
import vescame.orderstatuses.persistence.order.OrderEntity;
import vescame.orderstatuses.persistence.order.OrderLineEntity;

@Configuration
public class HashMapLongRepositoryConfig {

    @Bean
    public PersistenceRepository<Long, OrderEntity> orderRepository() {
        return new HashMapLongRepository<>();
    }

    @Bean
    public PersistenceRepository<Long, OrderLineEntity> orderLineRepository() {
        return new HashMapLongRepository<>();
    }

    @Bean
    public PersistenceRepository<Long, ItemEntity> itemEntityRepository() {
        PersistenceRepository<Long, ItemEntity> storage = new HashMapLongRepository<>();
        populateItems(storage);
        return storage;
    }

    private void populateItems(PersistenceRepository<Long, ItemEntity> persistenceRepository) {
        if (!batchCreateRan) {
            for (ItemEntity item : ItemInMemoryStub.getItems()) {
                persistenceRepository.add(item);
            }
            batchCreateRan = !batchCreateRan;
        }
    }

    private static boolean batchCreateRan = false;
}
