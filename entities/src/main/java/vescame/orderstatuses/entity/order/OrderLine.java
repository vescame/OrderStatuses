package vescame.orderstatuses.entity.order;

import vescame.orderstatuses.entity.item.Item;
import vescame.orderstatuses.entity.shipping.ShippingDetail;

public record OrderLine(
        Long id,
        Order order,
        Item item,
        Integer quantity,
        ShippingDetail shippingDetail
) { /* empty */ }
