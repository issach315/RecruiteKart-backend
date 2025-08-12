package com.recruitkart.employees.util;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AdultValidator.class) // Link to validator logic
@Target({ ElementType.FIELD }) // Where we can use it (on fields here)
@Retention(RetentionPolicy.RUNTIME) // Keep annotation at runtime
public @interface Adult {

    String message() default "Employee must be at least 18 years old";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
