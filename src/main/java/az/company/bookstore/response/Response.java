package az.company.bookstore.response;

import lombok.Data;

@Data
public class Response<T> {

    private T response;

}
