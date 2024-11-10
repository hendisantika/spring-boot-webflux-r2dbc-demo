package id.my.hendisantika.webfluxr2dbc.validator;

import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.xml.validation.Validator;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-webflux-r2dbc-demo
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 10/11/24
 * Time: 06.59
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractValidationHandler<T, U extends Validator> {

    private final Class<T> validationClass;

    private final U validator;

    protected AbstractValidationHandler(Class<T> clazz, U validator) {
        this.validationClass = clazz;
        this.validator = validator;
    }

    abstract protected Mono<ServerResponse> addProduct(T validBody, final ServerRequest originalRequest);

    abstract protected Mono<ServerResponse> updateProduct(T validBody, final ServerRequest originalRequest);

    public final Mono<ServerResponse> handleRequest(final ServerRequest request) {
        return request.bodyToMono(this.validationClass).flatMap(body -> {
            Errors errors = new BeanPropertyBindingResult(body, this.validationClass.getName());
            this.validator.validate(body, errors);
            if (errors == null || errors.getAllErrors().isEmpty()) {
                Long productId = Long.valueOf(request.pathVariable("id"));
                if (productId != null) {
                    return updateProduct(body, request);
                } else {
                    return addProduct(body, request);
                }
            } else {
                return onValidationErrors(errors, body, request);
            }
        });
    }
}
