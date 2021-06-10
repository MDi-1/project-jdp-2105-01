package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.OrderDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/order/")
public class OrderController {

    @GetMapping (value = "getOrders")
    public List<String> getOrders() {
        return new ArrayList<>();
    }

    @GetMapping (value = "getOrder")
    public void getOrder(@PathVariable Long id) {
        //do nothing
    }

    @PostMapping (value = "createOrder", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createOrder(@RequestBody OrderDto orderDto) {
        //do nothing
    }

    @PutMapping (value = "updateOrder", consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto updateOrder(@RequestBody OrderDto orderDto) {
        return new OrderDto();
    }

    @DeleteMapping("deleteOrder")
    public void deleteOrder() {
        //do nothing
    }

}
