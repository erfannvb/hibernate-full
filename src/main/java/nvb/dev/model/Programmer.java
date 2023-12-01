package nvb.dev.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "programmingLanguage cannot be null")
    @NotEmpty(message = "programmingLanguage cannot be empty")
    @Column(name = "programming_language", nullable = false)
    private String programmingLanguage;

    @NotNull(message = "level cannot be null")
    @NotEmpty(message = "level cannot be empty")
    @Column(name = "level", nullable = false)
    private String level;

}
