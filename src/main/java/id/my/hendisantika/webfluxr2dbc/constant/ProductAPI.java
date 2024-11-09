package id.my.hendisantika.webfluxr2dbc.constant;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicate;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-webflux-r2dbc-demo
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 10/11/24
 * Time: 06.54
 * To change this template use File | Settings | File Templates.
 */
public class ProductAPI {
    public static final RequestPredicate ACCEPT_JSON = accept(MediaType.APPLICATION_JSON);
    public static final String BASE_URL = "/api/v1/product";
    public static final String GET_PRODUCTS = BASE_URL;
    public static final String GET_PRODUCT_BY_ID = BASE_URL.concat("/{id}");
    public static final String ADD_PRODUCT = BASE_URL;
    public static final String UPDATE_PRODUCT = BASE_URL.concat("/{id}");
    public static final String DELETE_PRODUCT = BASE_URL.concat("/{id}");
}
