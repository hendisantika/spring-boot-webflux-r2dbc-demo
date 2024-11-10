package id.my.hendisantika.webfluxr2dbc.service;

import id.my.hendisantika.webfluxr2dbc.domain.ProductEntity;
import id.my.hendisantika.webfluxr2dbc.exception.EntityNotFoundException;
import id.my.hendisantika.webfluxr2dbc.mapper.ProductMapper;
import id.my.hendisantika.webfluxr2dbc.model.Product;
import id.my.hendisantika.webfluxr2dbc.repository.ProductRepository;
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
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public Flux<Product> getAllProducts() {
        return productRepository.findAll()
                .switchIfEmpty(Mono.error(new RuntimeException("Product not found")))
                .map(productMapper::mapToModel);
    }

    public Mono<Product> getProductById(Long id) {
        return productRepository.findById(id).map(productMapper::mapToModel)
                .switchIfEmpty(Mono.error(new RuntimeException("Product not found")));
    }

    public Mono<Product> addProduct(Product product) {
        Mono<ProductEntity> productEntity = productRepository.save(productMapper.mapToEntity(product))
                .switchIfEmpty(Mono.error(new RuntimeException("Product not found")))
                .doOnError(e -> log.error("Add product getting exception {}", e.getMessage()));
        return productEntity.map(productMapper::mapToModel);
    }

    public Mono<Product> updateProduct(Product product, Long id) {
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new EntityNotFoundException("Product not found")))
                .flatMap(currentProduct -> {
                    ProductEntity productEntity = productMapper.mapToEntity(product);
                    productEntity.setId(currentProduct.getId());
                    return productRepository.save(productEntity).map(productMapper::mapToModel);
                }).doOnError(e -> log.error("Update product getting exception {}", e.getMessage()));
    }

    public Mono<Void> deleteProduct(Long id) {
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new EntityNotFoundException("Product not found")))
                .flatMap(currentProduct -> {
                    return productRepository.deleteById(currentProduct.getId());
                });
    }
}
