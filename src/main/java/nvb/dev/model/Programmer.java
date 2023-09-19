package nvb.dev.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tbl_programmer")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "id")),
        @AttributeOverride(name = "firstName", column = @Column(name = "firstName")),
        @AttributeOverride(name = "lastName", column = @Column(name = "lastName"))
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Programmer extends Person {

    @Column(name = "programming_language")
    private String programmingLanguage;

    @Column(name = "level")
    private String level;

}
