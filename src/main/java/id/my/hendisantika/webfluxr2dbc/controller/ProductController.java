package id.my.hendisantika.webfluxr2dbc.controller;

import id.my.hendisantika.webfluxr2dbc.model.Product;
import id.my.hendisantika.webfluxr2dbc.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-webflux-r2dbc-demo
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 10/11/24
 * Time: 07.10
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v2")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/product")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/product/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Product> getProductById(@PathVariable("id") Long id) {
        return productService.getProductById(id);
    }
}
