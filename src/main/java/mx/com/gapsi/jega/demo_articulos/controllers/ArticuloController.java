package mx.com.gapsi.jega.demo_articulos.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.com.gapsi.jega.demo_articulos.entities.Articulo;
import mx.com.gapsi.jega.demo_articulos.services.ArticuloService;
import mx.com.gapsi.jega.demo_articulos.validation.ArticuloValidation;

@RestController
@RequestMapping("/api/articulos")
public class ArticuloController {

    @Autowired
    private ArticuloService articuloService;

    @Autowired
    private ArticuloValidation articuloValidation;

    @GetMapping("/")
    public List<Articulo> listAll(){
        return this.articuloService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> viewArticle(@PathVariable String id){
        Optional<Articulo> optionalArticulo = this.articuloService.findById(id);
        if(optionalArticulo.isPresent()){
            return ResponseEntity.ok(optionalArticulo.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Articulo articulo, BindingResult result, @PathVariable String id){
        this.articuloValidation.validate(articulo, result);

        if(result.hasErrors()){
            return validation(result);
        }

        Optional<Articulo> articuloDB = this.articuloService.update(id, articulo);
        if(articuloDB.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(articuloDB.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    private ResponseEntity<?> validation(BindingResult result){
        Map<String,String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err-> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
