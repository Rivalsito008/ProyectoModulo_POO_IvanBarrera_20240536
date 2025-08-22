package IvanBarrera_20240536.IvanBarrera_20240536.Service;

import IvanBarrera_20240536.IvanBarrera_20240536.Entities.LibrosEntity;
import IvanBarrera_20240536.IvanBarrera_20240536.Model.DTO.LibrosDTO;
import IvanBarrera_20240536.IvanBarrera_20240536.Respositories.LibrosRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
        if (data == null || data.getTitulo() == null || data.getAutor_id().isEmpty()){
            throw new IllegalArgumentException("Titulo o autor no pueden ser nullos");
        }
    }
}
