package guru.springframework.others;

import guru.springframework.recipe.Recipe;
import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @OneToOne
    private Recipe recipe;

    @Lob
    private String recipeNotes;

}
