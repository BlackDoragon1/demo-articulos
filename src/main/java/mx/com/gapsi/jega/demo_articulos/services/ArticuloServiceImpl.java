package mx.com.gapsi.jega.demo_articulos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.gapsi.jega.demo_articulos.entities.Articulo;
import mx.com.gapsi.jega.demo_articulos.repositories.ArticuloRepository;

@Service
public class ArticuloServiceImpl implements ArticuloService {

    @Autowired
    private ArticuloRepository articuloRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Articulo> findAll() {
        return (List<Articulo>) this.articuloRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Articulo> findById(String id) {
        return this.articuloRepository.findById(id);
    }

    @Transactional
    @Override
    public Articulo save(Articulo articulo) {
        return this.articuloRepository.save(articulo);
    }

    @Override
    public Optional<Articulo> update(String id, Articulo articulo) {
        Optional<Articulo> optionalArticulo = this.articuloRepository.findById(id);
        if(optionalArticulo.isPresent()){
            Articulo articuloDB = optionalArticulo.get();
            articuloDB.setDescripcion(articulo.getDescripcion());
            articuloDB.setModelo(articulo.getModelo());
            return Optional.of(this.articuloRepository.save(articuloDB));
        }
        return optionalArticulo;
    }

}
