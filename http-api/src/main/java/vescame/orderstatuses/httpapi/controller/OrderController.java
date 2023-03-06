package vescame.orderstatuses.httpapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import vescame.orderstatuses.entity.order.Order;
import vescame.orderstatuses.entity.order.OrderLine;
import vescame.orderstatuses.httpapi.item.ItemResponse;
import vescame.orderstatuses.httpapi.order.request.UpdateOrderStatusRequest;
import vescame.orderstatuses.httpapi.order.request.CreateOrderRequest;
import vescame.orderstatuses.httpapi.order.response.CreateOrderResponse;
import vescame.orderstatuses.httpapi.order.response.OrderLineResponse;
import vescame.orderstatuses.httpapi.order.response.OrderResponse;
import vescame.orderstatuses.usecases.item.exception.InvalidItemException;
import vescame.orderstatuses.usecases.order.exception.InvalidOrderException;
import vescame.orderstatuses.usecases.service.OrderService;
import static org.springframework.http.HttpStatus.NOT_FOUND;


@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateOrderResponse createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        var orderId = orderService.createNewOrder(
                createOrderRequest.customerId(),
                createOrderRequest.orderLines()
                        .stream()
                        .map(orderLine -> new OrderLine(orderLine.itemId(), orderLine.quantity()))
                        .toList()
        );

        return new CreateOrderResponse(orderId);
    }

    @PutMapping("/{orderId}/status")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateOrderStatus(@PathVariable Long orderId, @RequestBody UpdateOrderStatusRequest orderStatus) {
        orderService.updateStatus(orderId, orderStatus.status());
    }


    @GetMapping("/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public OrderResponse getOrderById(@PathVariable Long orderId) {
        Order order = orderService.getOrderById(orderId);
        return new OrderResponse(
                order.id(),
                order.customerId(),
                order.orderLines()
                        .stream()
                        .map(orderLine -> new OrderLineResponse(
                                        new ItemResponse(
                                                orderLine.item().id(),
                                                orderLine.item().name(),
                                                orderLine.item().details(),
                                                orderLine.item().price()
                                        ),
                                        orderLine.quantity()
                                )
                        ).toList(),
                order.status()
        );
    }

    @ExceptionHandler(InvalidOrderException.class)
    public ResponseEntity<ErrorResponse> handleInvalidOrderId(InvalidOrderException ex, WebRequest web) {
        return new ResponseEntity<>(ErrorResponse.create(ex, NOT_FOUND, ex.getMessage()), NOT_FOUND);
    }

    @ExceptionHandler(InvalidItemException.class)
    public ResponseEntity<ErrorResponse> handleInvalidItemId(InvalidItemException ex, WebRequest web) {
        return new ResponseEntity<>(ErrorResponse.create(ex, NOT_FOUND, ex.getMessage()), NOT_FOUND);
    }
}
