package az.company.bookstore.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ErrorResponse {

    Integer code;
    String message;

}
