package vescame.orderstatuses.httpapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vescame.orderstatuses.httpapi.item.ItemResponse;
import vescame.orderstatuses.usecases.item.ItemService;

import java.util.Collection;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @GetMapping
    public Collection<ItemResponse> getAll() {
        return service.getAll()
                .stream()
                .map(
                        item -> new ItemResponse(
                                item.id(),
                                item.name(),
                                item.details(),
                                item.price()
                        )
                ).toList();
    }
}
