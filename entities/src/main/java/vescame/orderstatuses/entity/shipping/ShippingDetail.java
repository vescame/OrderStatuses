package vescame.orderstatuses.entity.shipping;

public record ShippingDetail(
        Long id,
        String description,
        ShippingStatus status
) { /* empty */ }
