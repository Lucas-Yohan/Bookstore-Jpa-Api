package com.lyalencar.demo_spring_rev_jpa.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "posts")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false, length = 65, unique = true)
    private String titulo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_autor", nullable = false)
    private Autor autor;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "data_publicacao")
    private LocalDate dataPublicacao;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "posts_tem_categorias",
            joinColumns = @JoinColumn(name = "id_post"),
            inverseJoinColumns = @JoinColumn(name = "id_categoria")
    )
    private List<Categoria> categorias;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

