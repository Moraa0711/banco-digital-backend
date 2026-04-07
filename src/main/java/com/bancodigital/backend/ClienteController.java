package com.bancodigital.backend;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Date;
import java.util.List;

// --- MODELO ---
@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Integer id;
    
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

// --- REPOSITORIO ---
interface ClienteRepository extends JpaRepository<Cliente, Integer> {}

// --- CONTROLADOR CON DOCUMENTACIÓN PROFESIONAL ---
@RestController
@RequestMapping("/clientes")
@Tag(name = "Módulo de Clientes", description = "Operaciones CRUD para la gestión de usuarios del Banco Digital")
class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @Operation(summary = "Listar todos los clientes", description = "Retorna una lista completa de los clientes registrados en Neon.")
    @GetMapping
    public List<Cliente> listarTodos() {
        return repository.findAll();
    }

    @Operation(summary = "HU-1: Registrar Cliente", description = "Crea un nuevo cliente y lo activa por defecto.")
    @PostMapping
    public Cliente registrar(@RequestBody Cliente cliente) {
        cliente.setEstado(true); 
        if (cliente.getFechaRegistro() == null) cliente.setFechaRegistro(new Date());
        return repository.save(cliente);
    }

    @Operation(summary = "HU-2: Consultar por ID", description = "Obtiene los detalles de un cliente específico mediante su ID numérico.")
    @GetMapping("/{id}")
    public Cliente consultar(@PathVariable Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
    }

    @Operation(summary = "Actualizar Cliente", description = "Permite modificar nombre, apellidos y teléfono de un cliente existente.")
    @PutMapping("/{id}")
    public Cliente actualizar(@PathVariable Integer id, @RequestBody Cliente datosNuevos) {
        Cliente clienteExistente = repository.findById(id).orElseThrow();
        clienteExistente.setNombre(datosNuevos.getNombre());
        clienteExistente.setApellidos(datosNuevos.getApellidos());
        clienteExistente.setTelefono(datosNuevos.getTelefono());
        clienteExistente.setEmail(datosNuevos.getEmail());
        return repository.save(clienteExistente);
    }

    @Operation(summary = "Eliminar Cliente", description = "Borra físicamente el registro del cliente de la base de datos.")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}