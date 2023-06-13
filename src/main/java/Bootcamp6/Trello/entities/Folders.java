package Bootcamp6.Trello.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity
@Table(name = "folders")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Folders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<TaskCategories>categories;
}
