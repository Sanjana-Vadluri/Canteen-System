package com.canteen.canteensystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)         // name is required
    private String name;

    private String description;       // e.g. “Cheesy double patty”

    @Column(nullable = false)
    private Double price;             // store in INR for now

    private String imageUrl;          // link to item photo

    private Boolean available = true; // allow hiding out‑of‑stock

    /* ---------- relation ---------- */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    @JsonBackReference                // avoids infinite JSON loops
    private Category category;
}
