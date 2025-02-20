package org.iesvdm.tutorial;

import org.hibernate.mapping.Any;
import org.iesvdm.tutorial.domain.Heroe;
import org.iesvdm.tutorial.domain.Mision;
import org.iesvdm.tutorial.domain.Poder;
import org.iesvdm.tutorial.repository.HeroeRepository;
import org.iesvdm.tutorial.repository.MisionRepository;
import org.iesvdm.tutorial.repository.PoderRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class )
@SpringBootTest
public class HeroeTest {


    @Autowired
    private PoderRepository poderRepository;
    @Autowired
    private MisionRepository misionRepository;
    @Autowired
    private HeroeRepository heroeRepository;

    @Test
    void crearHeroeTest(){

        //Poderes
        Poder poder1 = Poder.builder()
                .nombre("Poder 1")
                .build();

        Poder poder2 = Poder.builder()
                .nombre("Poder 2")
                .build();

        //Mision
        Mision mision = Mision.builder()
                .descripcion("Subirse a un delfin y nadar")
                .build();
        mision = misionRepository.save(mision);

        //Heroe
        Heroe heroe = Heroe.builder()
                .nombre("Aquaman")
                .mision(mision)
                .build();

        //Guardo los poder1 en el heroe
        heroe.getPoderes().add(poder1);
        //Guardo el heroe en ambos poderes
        poder1.getHeroes().add(heroe);
        //Con el metodo bidireccional guardo poder2 y heroe a la vez
        heroe.addPoder(poder2);
        //Guardo la mision en el heroe
        heroe.setMision(mision);

        //Salvo los cambios en la bd
        heroe = heroeRepository.save(heroe);
        poder1 = poderRepository.save(poder1);
        poder2 = poderRepository.save(poder2);
        heroe = heroeRepository.save(heroe);


    }
}
