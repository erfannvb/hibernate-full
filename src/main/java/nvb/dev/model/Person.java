package nvb.dev.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import nvb.dev.base.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_person")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Person extends BaseEntity<Long> {

    @NotNull(message = "firstName cannot be null")
    @NotEmpty(message = "firstName cannot be empty")
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @NotNull(message = "lastName cannot be null")
    @NotEmpty(message = "lastName cannot be empty")
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

}
