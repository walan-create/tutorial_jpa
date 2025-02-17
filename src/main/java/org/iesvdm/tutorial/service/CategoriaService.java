package org.iesvdm.tutorial.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.iesvdm.tutorial.domain.Categoria;
import org.iesvdm.tutorial.exception.CategoriaNotFoundException;
import org.iesvdm.tutorial.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CategoriaService {
    
    private CategoriaRepository categoriaRepository;

    @PersistenceContext
    EntityManager entityManager;

    public List<Categoria> all() {
        return this.categoriaRepository.findAll();
    }


    @Transactional
    public Categoria save(Categoria categoria) {

        this.categoriaRepository.save(categoria);

        this.entityManager.refresh(categoria);

        return categoria;
    }

    public Categoria one(Long id) {
        return this.categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNotFoundException(id));
    }

    public Categoria replace(Long id, Categoria categoria) {

        return this.categoriaRepository.findById(id).map( p -> (id.equals(categoria.getId())  ?
                        this.categoriaRepository.save(categoria) : null))
                .orElseThrow(() -> new CategoriaNotFoundException(id));

    }

    public void delete(Long id) {
        this.categoriaRepository.findById(id).map(p -> {this.categoriaRepository.delete(p);
                    return p;})
                .orElseThrow(() -> new CategoriaNotFoundException(id));
    }
    
}
