package com.example.demo.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Categorias")
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String nombre;
    private String descripcion;
    private String tipo;
    private String icono;
    private String imagenNavbar;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "producto_deparments",
            joinColumns = @JoinColumn(name = "deparments_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Producto> productos = new HashSet<>();
}
