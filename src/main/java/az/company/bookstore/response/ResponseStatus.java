package az.company.bookstore.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseStatus {

    private static final Integer SUCCESS_CODE = 1;
    private static final String SUCCESS_MESSAGE = "success";
    private Integer code;
    private String message;

    public static ResponseStatus getSuccessMessage() {
        return new ResponseStatus(SUCCESS_CODE, SUCCESS_MESSAGE);
    }

}
