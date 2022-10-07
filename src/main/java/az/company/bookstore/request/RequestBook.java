package az.company.bookstore.request;

import lombok.Data;

@Data
public class RequestBook {

    private Long id;

    private String name;

    private String description;

    private Long publisherId;

    private Long authorId;

}
