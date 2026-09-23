package com.lyalencar.demo_spring_rev_jpa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.util.Lazy;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "autores")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Autor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_autor", nullable = false)
    private Long id;

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "sobrenome", nullable = false, length = 50)
    private String sobrenome;

    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "info_autor_id_info")
    private InfoAutor infoAutor;

    // EQUALS HASCODE
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Autor autor = (Autor) o;
        return Objects.equals(id, autor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                '}';
    }
}
