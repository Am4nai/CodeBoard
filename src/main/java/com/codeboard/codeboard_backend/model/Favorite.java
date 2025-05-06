package com.codeboard.codeboard_backend.model;


import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "favorites")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "list_name", nullable = false, length = 100)
    private String listName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "favorite_lists", // Имя связующей таблицы
            joinColumns = @JoinColumn(name = "favorite_id"), // Столбец, ссылающийся на текущую сущность
            inverseJoinColumns = @JoinColumn(name = "post_id") // Столбец, ссылающийся на связанную сущность
    )
    private Set<Post> posts = new HashSet<>();
}
