package IvanBarrera_20240536.IvanBarrera_20240536.Exceptions;

import lombok.Getter;

public class ExceptionDatosDuplicados extends RuntimeException{
    @Getter
    private String campoDuplicado;

    public ExceptionDatosDuplicados(String message, String campoDuplicado){
        super(message);
        this.campoDuplicado = campoDuplicado;
    }
    public ExceptionDatosDuplicados(String campoDuplicado){
        this.campoDuplicado = campoDuplicado;
    }
}
