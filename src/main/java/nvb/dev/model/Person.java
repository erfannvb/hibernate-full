package nvb.dev.model;

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

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

}
