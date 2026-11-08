package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Department;
import com.example.demo.model.Producto;
import com.example.demo.repo.categoriaRepo;
import com.example.demo.repo.productoRepo;

@RestController
public class CategoriaController {

    @Autowired
    categoriaRepo repo;

    @Autowired
    productoRepo repoProducto;

    @GetMapping("categorias")
    public List<Department> findAllCategoria() {
        return this.repo.findAll();
    }

    @PostMapping("categoria")
    public Department addCategoria(@RequestBody Department categoria) {
        return repo.save(categoria);
    }

    @PostMapping("addProductoCategoria/{productId}/{categoriaId}")
    public Department addProductoCategoria(@PathVariable String productId, @PathVariable String categoriaId) {
        Optional<Producto> p = repoProducto.findById(productId);
        Optional<Department> d = repo.findById(categoriaId);

        if (d.isPresent() && p.isPresent()) {
            d.get().getProductos().add(p.get());
            return repo.save(d.get());
        }

        throw new RuntimeException("Producto o categoría no encontrados");
    }

    @GetMapping("categoria/producto/{productId}")
    public Set<Department> getDepartmentsByProduct(@PathVariable String productId) {
        Optional<Producto> p = repoProducto.findById(productId);
        return p.map(Producto::getDeparments).orElse(null);
    }

    @GetMapping("categoria/{id}")
    public Optional<Department> getById(@PathVariable String id) {
        return repo.findById(id);
    }

    @PutMapping("categoria/{id}")
    public Department putDepartment(@PathVariable String id, @RequestBody Department categoria) {
        categoria.setId(id);
        return repo.save(categoria);
    }

    @DeleteMapping("categoria/{id}")
    public String deleteDepartment(@PathVariable String id) {
        repo.deleteById(id);
        return "{\"success\":true}";
    }
}
