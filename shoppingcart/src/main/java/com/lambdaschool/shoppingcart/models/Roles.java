package com.lambdaschool.shoppingcart.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Roles extends Auditable
{
    // auto generated ID
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long roleid;

    // role name
    @Column(nullable = false,
            unique = true)
    private String name;

    // relationship to UserRoles join table
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = "role", allowSetters = true)
    private Set<UserRoles> users = new HashSet<>();
}
