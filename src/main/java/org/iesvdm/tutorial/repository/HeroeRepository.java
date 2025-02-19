package org.iesvdm.tutorial.repository;

import org.iesvdm.tutorial.domain.Heroe;
import org.iesvdm.tutorial.domain.Pelicula;
import org.iesvdm.tutorial.domain.Poder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeroeRepository extends JpaRepository<Heroe,Long>{


}