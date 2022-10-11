package az.company.bookstore.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RequestBook {

    private Long id;

    @NotBlank
    private String name;

    private String description;

    private Long publisherId;

    private Long authorId;

}
