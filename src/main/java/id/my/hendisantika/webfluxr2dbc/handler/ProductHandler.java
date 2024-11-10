package id.my.hendisantika.webfluxr2dbc.handler;

import id.my.hendisantika.webfluxr2dbc.model.Product;
import id.my.hendisantika.webfluxr2dbc.service.ProductService;
import id.my.hendisantika.webfluxr2dbc.validator.AbstractValidationHandler;
import id.my.hendisantika.webfluxr2dbc.validator.RequestValidator;
import org.springframework.stereotype.Component;

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
}
