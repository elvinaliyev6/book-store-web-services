package az.company.bookstore.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseBook {

    private Long id;
    private String name;
    private String description;
    private ResponseUser author;
    private ResponseUser publisher;


}
