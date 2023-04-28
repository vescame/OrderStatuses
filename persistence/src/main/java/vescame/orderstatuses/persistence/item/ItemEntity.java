package vescame.orderstatuses.persistence.item;

import vescame.orderstatuses.persistence.PersistableEntity;
import java.math.BigDecimal;

public class ItemEntity implements PersistableEntity<Long> {
    Long id;
    String name;
    String details;
    BigDecimal price;

    public ItemEntity(String name, String details, BigDecimal price) {
        this.id = null;
        this.name = name;
        this.details = details;
        this.price = price;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
