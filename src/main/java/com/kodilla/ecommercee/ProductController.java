package com.kodilla.ecommercee;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/v1/product")
@RequiredArgsConstructor
public class ProductController {


    @RequestMapping(method = RequestMethod.POST, value = "createProduct", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        return productDto;

    }

    @GetMapping("getProduct/{id}")
    public ProductDto getProduct(@PathVariable Long id) {
        return new ProductDto(id, "Product1", "product1", 22.2);

    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateProduct")
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return productDto;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteProduct/{id}")
    public ProductDto deleteProduct(@PathVariable Long id) {
        return new ProductDto(id, "product", "product", 22);

    }

    @RequestMapping(method = RequestMethod.GET, value = "getProducts")
    public List<ProductDto> getProducts() {
        return new ArrayList<>();
    }
}

