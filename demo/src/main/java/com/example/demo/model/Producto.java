package com.example.demo.model;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="producto")
@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter 
    private String id;

    private String nombre;
  
    private String descripcion;
    private String lote;
    private float precioUnitario;
    private LocalDate fechaCaducidad;

    @JsonManagedReference
    @OneToMany(mappedBy = "producto",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Comment> comentarios;

    @JsonManagedReference
    @OneToMany(mappedBy = "producto",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Setter 
    private Set<Image> imagenes;

    @ManyToMany(mappedBy = "productos", fetch = FetchType.EAGER)
    @JsonIgnore
    @Setter
    private Set<Department> deparments = new HashSet<>();
}
