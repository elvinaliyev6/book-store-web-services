package az.company.bookstore.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestUser {
    private String name;

    private String surname;

    private String email;

    private String username;

    private String password;

    private Long userTypeId;

}
