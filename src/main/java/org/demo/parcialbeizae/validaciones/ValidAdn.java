package org.demo.parcialbeizae.validaciones;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Constraint(validatedBy = ValidaAdn.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidAdn {
    String message() default "Secuencia ADN inválida";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}