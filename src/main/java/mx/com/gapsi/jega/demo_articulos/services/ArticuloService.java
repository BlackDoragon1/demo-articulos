package mx.com.gapsi.jega.demo_articulos.services;

import java.util.List;
import java.util.Optional;

import mx.com.gapsi.jega.demo_articulos.entities.Articulo;

public interface ArticuloService {

    List<Articulo> findAll();

    Optional<Articulo> findById(String id);

    Articulo save(Articulo articulo);

    Optional<Articulo> update(String id, Articulo articulo);
}
