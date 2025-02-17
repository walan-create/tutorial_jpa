package org.iesvdm.tutorial.domain;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;
import org.iesvdm.tutorial.enums.ClasificacionEnum;
import org.iesvdm.tutorial.serializer.PeliculaSerializer;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = Pelicula.class)
@JsonSerialize(using = PeliculaSerializer.class)
public class Pelicula {

    public Pelicula(long id, String titulo, Idioma idioma) {
        this.id = id;
        this.titulo = titulo;
        this.idioma = idioma;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;

    private String titulo;

    private Integer anyoLanzamiento;

    // Lo que está a la izquierda siempre hace referencia a la clase actual
    // En este caso Many hace referencia a Pelicula y One a Idioma
    // (En Idioma será al revés osea @OneToMany)
    @ManyToOne
    @ToString.Exclude
    private Idioma idioma;

    @ManyToOne
    @ToString.Exclude
    private Idioma idiomaOriginal;

    private Integer duracionAlquiler;

    @Column(precision = 4, scale = 2)
    private BigDecimal rentalRate;

    private Short duracion;

    @Column(precision = 5, scale = 2)
    private BigDecimal replacementCost;

    private ClasificacionEnum clasificacion;

    @ManyToMany(mappedBy = "peliculas")
    private Set<CaracteristicaEspecial> caracteristicasEspeciales;

    private LocalDateTime ultimaActualizacion;

    /*Tenemos 2 maneras de hacer el ManyToMany
    * 1. */
    @OneToMany(mappedBy = "pelicula")
    @JsonIgnore
    private Set<PeliculaCategoria> categorias;

    @ManyToMany(mappedBy = "peliculas")
    private Set<Actor> actores;

}
