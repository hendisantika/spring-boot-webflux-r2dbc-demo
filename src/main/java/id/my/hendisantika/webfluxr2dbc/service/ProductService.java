package id.my.hendisantika.webfluxr2dbc.service;

import id.my.hendisantika.webfluxr2dbc.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-webflux-r2dbc-demo
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 10/11/24
 * Time: 07.03
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Flux<Product> getAllProducts() {
        return productRepository.findAll()
                .switchIfEmpty(Mono.error(new RuntimeException("Product not found")))
                .map(productMapper::mapToModel);
    }

    public Mono<Product> getProductById(Long id) {
        return productRepository.findById(id).map(productMapper::mapToModel)
                .switchIfEmpty(Mono.error(new RuntimeException("Product not found")));
    }
}
