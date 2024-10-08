package mx.com.gapsi.jega.demo_articulos.repositories;

import org.springframework.data.repository.CrudRepository;

import mx.com.gapsi.jega.demo_articulos.entities.Articulo;

public interface ArticuloRepository extends CrudRepository<Articulo, String> {

}
