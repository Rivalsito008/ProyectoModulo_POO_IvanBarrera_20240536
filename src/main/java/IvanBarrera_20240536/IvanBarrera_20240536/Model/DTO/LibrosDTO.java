package IvanBarrera_20240536.IvanBarrera_20240536.Model.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Getter @Setter @ToString @EqualsAndHashCode
public class LibrosDTO {

    private Long id;

    @NotBlank
    private String titulo;
    @NotBlank
    private String isbn;
    @NotBlank
    private String año_publicacion;
    @NotBlank
    private String genero;
    @NotBlank
    @Positive(message = "El id debe de ser positivo")
    private String autor_id;
}
