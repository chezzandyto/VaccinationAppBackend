package com.kruger.vaccineInventory.demo.persistence.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue
    private Integer id;

    private Long cedula;

    private String nombres;

    private String apellidos;

    private String correo;

    @Column(name = "nombre_usuario")
    private String nombreUsuario;

    private String contrasena;

    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;

    private String direccion;

    private Long telefono;

    @Column(name = "estado_vacunacion")
    private Boolean estadoVacunacion;

    @Column(name = "id_rol")
    private Integer idRol;

    @OneToMany(mappedBy = "usuario")
    private List<RegistroVacuna> registroVacunas;

    @ManyToOne
    @JoinColumn(name = "id_rol", insertable = false, updatable = false)
    private Rol rol;

    public Long getCedula() {
        return cedula;
    }

    public void setCedula(Long cedula) {
        this.cedula = cedula;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public Boolean getEstadoVacunacion() {
        return estadoVacunacion;
    }

    public void setEstadoVacunacion(Boolean estadoVacunacion) {
        this.estadoVacunacion = estadoVacunacion;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public List<RegistroVacuna> getRegistroVacunas() {
        return registroVacunas;
    }

    public void setRegistroVacunas(List<RegistroVacuna> registroVacunas) {
        this.registroVacunas = registroVacunas;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
