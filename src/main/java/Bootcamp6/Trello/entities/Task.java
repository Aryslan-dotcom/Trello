package Bootcamp6.Trello.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tasks")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private int status;
    @ManyToOne(fetch = FetchType.EAGER)
    private Folders folder;
}
