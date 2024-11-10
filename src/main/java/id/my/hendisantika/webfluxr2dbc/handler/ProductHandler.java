package id.my.hendisantika.webfluxr2dbc.handler;

import id.my.hendisantika.webfluxr2dbc.model.Product;
import id.my.hendisantika.webfluxr2dbc.service.ProductService;
import id.my.hendisantika.webfluxr2dbc.validator.AbstractValidationHandler;
import id.my.hendisantika.webfluxr2dbc.validator.RequestValidator;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-webflux-r2dbc-demo
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 10/11/24
 * Time: 07.08
 * To change this template use File | Settings | File Templates.
 */
@Component
public class ProductHandler extends AbstractValidationHandler<Product, RequestValidator> {
    private final ProductService productService;

    public ProductHandler(ProductService productService) {
        super(Product.class, new RequestValidator());
        this.productService = productService;
    }

    public Mono<ServerResponse> getAllProducts(ServerRequest request) {
        //We can manage pagination
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(productService.getAllProducts(), Product.class);
    }

    public Mono<ServerResponse> getProductById(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(productService.getProductById(Long.parseLong(request.pathVariable("id"))), Product.class);
    }

    @Override
    protected Mono<ServerResponse> addProduct(Product validBody, ServerRequest originalRequest) {
        Mono<Product> productMono = Mono.just(validBody)
                .flatMap(productService::addProduct);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(productMono, Product.class);
    }

    @Override
    protected Mono<ServerResponse> updateProduct(Product validBody, ServerRequest originalRequest) {
        Mono<Product> productMono = Mono.just(validBody)
                .flatMap(p -> productService.updateProduct(p, Long.parseLong(originalRequest.pathVariable("id"))));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(productMono, Product.class);
    }
}
