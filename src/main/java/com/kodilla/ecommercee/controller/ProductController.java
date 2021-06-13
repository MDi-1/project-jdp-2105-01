package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;
    private final ProductMapper productMapper;


    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createProduct(@RequestBody ProductDto productDto) {
        Product product = productMapper.mapToProduct(productDto);
        service.saveProduct(product);

    }

    @RequestMapping(method = RequestMethod.GET, value = "{/id}")
    public ProductDto getProduct(@PathVariable Long id) throws ProductNotFoundException {
        return productMapper.mapToProductDto(
                service.getProductById(id).orElseThrow(ProductNotFoundException::new));

    }

    @RequestMapping(method = RequestMethod.PUT)
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        Product product = productMapper.mapToProduct(productDto);
        Product savedProduct = service.saveProduct(product);
        return productMapper.mapToProductDto(savedProduct);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteProduct(@PathVariable Long id) {
        service.deleteProduct(id);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public List<ProductDto> getProducts() {
        List<Product> products = service.getAllProducts();
        return productMapper.mapToProductDtoList(products);
    }
}

