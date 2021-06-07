package com.kodilla.ecommercee;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/order")
public class OrderController {

    @GetMapping (value = "getOrders")
    public List<String> getOrders() {
        return new ArrayList<>();
    }

    @GetMapping (value = "getOrder")
    public void getOrder() {
    }

    @PostMapping (value = "createOrder", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createOrder() {
    }

    @PutMapping (value = "updateOrder")
    public void updateOrder() {
    }

    @DeleteMapping("deleteOrder")
    public void deleteOrder() {
    }

}
