package IvanBarrera_20240536.IvanBarrera_20240536.Entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "LIBROS")
@Getter @Setter @ToString @EqualsAndHashCode
public class LibrosEntity {

    @Id
    @Column(name = "LIBRO_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_Libros")
    @SequenceGenerator(name = "seq_Libros", sequenceName = "seq_Libros", allocationSize = 1)
    private Long id;

    @Column(name = "TITULO")
    private String titulo;

    @Column(name = "ISBN")
    private String isbn;

    @Column(name = "AÑO_PUBLICACION")
    private Date año_publicacion;

    @Column(name = "GENERO")
    private String genero;

    @Column(name = "AUTOR_ID")
    private Long autor_id;


}
