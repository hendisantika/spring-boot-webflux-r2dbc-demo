package id.my.hendisantika.webfluxr2dbc.validator;

import id.my.hendisantika.webfluxr2dbc.domain.ProductEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-webflux-r2dbc-demo
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 10/11/24
 * Time: 07.01
 * To change this template use File | Settings | File Templates.
 */
public class RequestValidator implements Validator {

    private static final double MINIMUM_PRICE = 1.00;
    private static final int MINIMUM_QUANTITY = 1;

    @Override
    public boolean supports(Class<?> clazz) {
        return ProductEntity.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productName", "Product Name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productType", "Product Type is required");
        ProductEntity request = (ProductEntity) target;
        if (request.getPrice() == null && request.getQuantity() == null) {
            errors.rejectValue("price", "404",
                    new Object[]{MINIMUM_QUANTITY}, "The price might be minimum [" + MINIMUM_QUANTITY + "]");
            errors.rejectValue("quantity", "404",
                    new Object[]{MINIMUM_QUANTITY}, "The quantity might be minimum [" + MINIMUM_QUANTITY + "]");
        }
    }
}
