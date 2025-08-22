package IvanBarrera_20240536.IvanBarrera_20240536.Model.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter @Setter @ToString @EqualsAndHashCode
public class LibrosDTO {

    private Long id;

    @NotBlank
    private String titulo;
    @NotBlank
    private String isbn;

    private Date año_publicacion;
    @NotBlank
    private String genero;

    @Positive(message = "El id debe de ser positivo")
    private Long autor_id;
}
