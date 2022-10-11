package az.company.bookstore;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "BookStore Service API",
                description = "Book store web services",
                version = "v1"
        )
)
public class BookStoreWebServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookStoreWebServicesApplication.class, args);
    }

}
