package org.iesvdm.tutorial.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static javax.management.openmbean.SimpleType.STRING;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Entity
public class Heroe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;

    private String nombre;

    private LocalDateTime fechaNac;

    @ManyToOne
    @ToString.Exclude
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Mision mision;

    @ManyToMany(mappedBy = "heroes")
    @Builder.Default
    private Set<Poder> poderes = new HashSet<>();

    public void addPoder (Poder poder){
        poderes.add(poder);
        poder.getHeroes().add(this);
    }
    public void removePoder(Poder poder){
        poderes.remove(poder);
        poder.getHeroes().remove(this);
    }

}
