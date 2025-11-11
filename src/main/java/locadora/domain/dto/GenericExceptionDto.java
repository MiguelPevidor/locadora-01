package locadora.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.FieldError;

@Getter@Setter
public class GenericExceptionDto {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String field;
    private String message;

    public GenericExceptionDto(FieldError fieldError) {
        this.field = fieldError.getField();
        this.message = fieldError.getDefaultMessage();
    }

    public GenericExceptionDto(String message) {
        this.message = message;
    }

}
