package com.kodilla.ecommercee.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "GROUPS")
public class Group {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(
            mappedBy = "group",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Product> products = new ArrayList<>();

    public Group(@NotNull String name) {
        this.name = name;
    }
}
