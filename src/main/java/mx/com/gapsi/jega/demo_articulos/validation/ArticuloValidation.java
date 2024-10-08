package mx.com.gapsi.jega.demo_articulos.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import mx.com.gapsi.jega.demo_articulos.entities.Articulo;

@Component
public class ArticuloValidation implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Articulo.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Articulo articulo = (Articulo) target;

        if(articulo.getDescripcion() == null || articulo.getDescripcion().isBlank() || articulo.getDescripcion().length() > 200){
            errors.rejectValue("descripcion", null, "es invalido");
        }

        if(articulo.getModelo() == null || articulo.getModelo().isBlank() || articulo.getModelo().length() > 10){
            errors.rejectValue("modelo", null, "es invalido");
        }
    }

}
