package IvanBarrera_20240536.IvanBarrera_20240536.Service;

import IvanBarrera_20240536.IvanBarrera_20240536.Entities.LibrosEntity;
import IvanBarrera_20240536.IvanBarrera_20240536.Exceptions.ExceptionLibroNoEncontradro;
import IvanBarrera_20240536.IvanBarrera_20240536.Exceptions.ExceptionLibrosNoRegistrado;
import IvanBarrera_20240536.IvanBarrera_20240536.Model.DTO.LibrosDTO;
import IvanBarrera_20240536.IvanBarrera_20240536.Respositories.LibrosRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LibrosService {
    //Inyectanis el reposritory sobre el service
    @Autowired
    LibrosRepository repo;{}

    public List<LibrosDTO>obtenerLibros(){
        List<LibrosEntity> lista = repo.findAll();
        return lista.stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    public LibrosDTO insertarLibros(LibrosDTO data) {
        if (data == null || data.getTitulo() == null || data.getAutor_id() == null){
            throw new IllegalArgumentException("Titulo o autor no pueden ser nullos");
        }try{
            LibrosEntity entity = ConvertirAEntity(data);
            LibrosEntity libroGuardado = repo.save(entity);
            return convertirADTO(libroGuardado);
        } catch (Exception e){
            log.error("Error al registrar el libro: " + e.getMessage());
            throw new ExceptionLibrosNoRegistrado("Error al registrar el libro");
        }
    }
     /**
      *
      * @Param data
      * @return
      */
     private LibrosEntity ConvertirAEntity(LibrosDTO data){
         LibrosEntity entity = new LibrosEntity();
         entity.setTitulo(data.getTitulo());
         entity.setIsbn(data.getIsbn());
         entity.setGenero(data.getGenero());
         entity.setAño_publicacion(data.getAño_publicacion());
         entity.setAutor_id(data.getAutor_id());
         return entity;
     }

    /**
     *
     * @param librosEntity
     * @return
     */
     private LibrosDTO convertirADTO(LibrosEntity librosEntity){
         LibrosDTO dto = new LibrosDTO();
         dto.setTitulo(librosEntity.getTitulo());
         dto.setIsbn(librosEntity.getIsbn());
         dto.setGenero(librosEntity.getGenero());
         dto.setAño_publicacion(librosEntity.getAño_publicacion());
         dto.setAutor_id(librosEntity.getAutor_id());
         return dto;
     }

     public LibrosDTO actualizarLibro(Long id, LibrosDTO json) {
         LibrosEntity existente = repo.findById(id).orElseThrow(() ->new ExceptionLibroNoEncontradro("Libro no encontrado"));
         existente.setTitulo(json.getTitulo());
         existente.setIsbn(json.getIsbn());
         existente.setGenero(json.getGenero());
         existente.setAño_publicacion(json.getAño_publicacion());
         existente.setAutor_id(json.getAutor_id());
         LibrosEntity libroActualizado = repo.save(existente);
         return convertirADTO(libroActualizado);
     }

    public boolean removerLibro(Long id) {
         try{
             LibrosEntity existente = repo.findById(id).orElse(null);
             if (existente != null){
                 repo.deleteById(id);
                 return true;
             } else{
                 return false;
             }
         } catch (EmptyResultDataAccessException e){
             throw new EmptyResultDataAccessException("No se encontro usario con id: " + id + " para eliminar.", 1);
         }
    }
}
