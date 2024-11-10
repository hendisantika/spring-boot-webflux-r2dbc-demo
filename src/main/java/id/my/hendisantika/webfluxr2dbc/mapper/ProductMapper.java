package id.my.hendisantika.webfluxr2dbc.mapper;

import id.my.hendisantika.webfluxr2dbc.domain.ProductEntity;
import id.my.hendisantika.webfluxr2dbc.model.Product;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-webflux-r2dbc-demo
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 10/11/24
 * Time: 07.07
 * To change this template use File | Settings | File Templates.
 */
@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductEntity mapToEntity(Product product);

    Product mapToModel(ProductEntity productEntity);
}
