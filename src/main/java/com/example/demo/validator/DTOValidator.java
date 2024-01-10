package com.example.demo.validator;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.lang.reflect.Method;
import java.util.Set;
@Component
public class DTOValidator {

    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public <T> void validate(T dto) throws MethodArgumentNotValidException {
        Set<ConstraintViolation<T>> violations = validator.validate(dto);

        if (!violations.isEmpty()) {
            String objectName = dto.getClass().getSimpleName();
            BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(dto, objectName);

            violations.forEach(violation -> {
                String field = violation.getPropertyPath().toString();
                String errorCode = objectName + "." + field;
                Object rejectedValue;
                try {
                    rejectedValue = dto.getClass().getDeclaredField(field).get(dto);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    rejectedValue = null;
                }
                bindingResult.addError(new FieldError(objectName, field, rejectedValue, false,
                        new String[]{errorCode}, null, violation.getMessage()));
            });

            Method validate = null;
            try {
                validate = this.getClass().getDeclaredMethod("validate", Object.class);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            throw new MethodArgumentNotValidException(new MethodParameter(validate, -1), bindingResult);

        }
    }
}
