package az.company.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "author_id",nullable = false)
    private User author;

    @ManyToOne
    @JoinColumn(name = "publisher_id",nullable = false)
    private User publisher;

    @Column(name = "status")
    @ColumnDefault(value = "1")
    private Integer status;

    @Column(name = "insert_date")
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    private Date insertDate;
}
