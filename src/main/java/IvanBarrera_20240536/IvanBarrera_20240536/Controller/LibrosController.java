package IvanBarrera_20240536.IvanBarrera_20240536.Controller;

import IvanBarrera_20240536.IvanBarrera_20240536.Exceptions.ExceptionDatosDuplicados;
import IvanBarrera_20240536.IvanBarrera_20240536.Model.DTO.LibrosDTO;
import IvanBarrera_20240536.IvanBarrera_20240536.Service.LibrosService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/apiLibros")
public class LibrosController {

    //Inyectamos el service sobre el controller
    @Autowired
    LibrosService service;

    //Metodo para obtener datos
    @GetMapping ("/consultarLibros")
    public List<LibrosDTO> obtenerLibros(){
        return service. obtenerLibros();
    }

    //Metodo para Insertar datos
    @PostMapping("/insertarLibros")
    public ResponseEntity<?> nuevoLibro(@Valid @RequestBody LibrosDTO json, HttpServletRequest request){
        try{
            LibrosDTO respuesta = service.insertarLibros(json);
            if(respuesta == null){
                return ResponseEntity.badRequest().body(Map.of(
                   "status", "Inserción fallida",
                   "errorType", "VALIDATION_ERROR",
                   "message", "Los datos no pudieron ser registrados"
                ));
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "status", "success",
                    "data", respuesta
            ));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status", "error",
                    "message", "Err0r no controlado al ingresar usuario",
                    "detail", e.getMessage()
            ));
        }
    }

    //Metodo para actualizar datos
    @PutMapping ("/editarLibro/{id}")
    public ResponseEntity<?> modificarLibro(@PathVariable Long id, @Valid @RequestBody LibrosDTO json, BindingResult bindingResult){
    if (bindingResult.hasErrors()){
        Map<String, String> errores = new HashMap<>();
        bindingResult.getFieldErrors().forEach(error -> errores.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errores);
    }try{
        LibrosDTO dto = service.actualizarLibro(id, json);
        return ResponseEntity.ok(dto);
        }catch (ExceptionDatosDuplicados e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                "Error", "Datos duplicados",
                "Campo", e.getCampoDuplicado()
        ));
        }
    }

}
