package id.my.hendisantika.webfluxr2dbc.model;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-webflux-r2dbc-demo
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 10/11/24
 * Time: 07.06
 * To change this template use File | Settings | File Templates.
 */
public record Product(Long id,
                      String productName,
                      String productType,
                      Double price,
                      Integer quantity) {
}
