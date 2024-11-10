package id.my.hendisantika.webfluxr2dbc.repository;

import id.my.hendisantika.webfluxr2dbc.domain.ProductEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-webflux-r2dbc-demo
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 10/11/24
 * Time: 07.04
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface ProductRepository extends ReactiveCrudRepository<ProductEntity, Long> {
//    @Query("SELECT * FROM product WHERE product_name = :productName")
//    Flux<ProductEntity> findByProductName(String productName);
}
