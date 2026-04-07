package com.bancodigital.backend;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

// --- MODELO (Debe coincidir con tu tabla 'cliente' de Neon) ---
@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private Integer id_tipo_documento;
    private String numero_documento;
    private String nombre;
    private String apellidos;
    private String email;
    private String telefono;
    private Boolean estado;
    
    @Column(name = "fecha_registro", updatable = false)
    private Date fechaRegistro = new Date();
    
    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento = new Date();
}

// --- REPOSITORIO (La magia que conecta con la DB) ---
interface ClienteRepository extends JpaRepository<Cliente, Integer> {}

// --- CONTROLADOR (Tus Endpoints para la Demo) ---
@RestController
@RequestMapping("/clientes")
class ClienteController {

    @Autowired
    private ClienteRepository repository;

    // HU-1: Registrar Cliente
    @PostMapping
    public Cliente registrar(@RequestBody Cliente cliente) {
        cliente.setEstado(true); // Valor por defecto
        return repository.save(cliente);
    }

    // HU-2: Consultar Cliente
    @GetMapping("/{id}")
    public Cliente consultar(@PathVariable Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }
}