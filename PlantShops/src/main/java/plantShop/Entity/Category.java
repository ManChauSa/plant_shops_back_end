package plantShop.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    private String categoryName;
    private String categoryDesc;
    private LocalDate createdDate;
    private LocalDate updateDate;

    @ManyToOne
    @JoinColumn(name = "parentId")
    public Category parentCategory;

}
