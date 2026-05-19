package de.hsrm.mi.web.webprojekt.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GutesPasswortValidator.class)
@Documented
public @interface GutesPasswort {
    String message() default "{benutzer.fehler.passwortungeeignet}";
    Class<?>[] payload() default { };
    Class<?>[] groups() default { };
}
