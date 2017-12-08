package com.itla.mudat.Entity;

import java.io.Serializable;

public class Usuario implements Serializable{

 public static final String nomtableUsuario="Usuario";
 public static final String nomid="id";
 public static final String nomnombre="nombre";
 public static final String nomtipousuario="tipousuario";
 public static final String nomidentificacion="identificacion";
 public static final String nomemail="email";
 public static final String nomtelefono="telefono";
 public static final String nomclave="clave";
 public static final String nomstatus= "status";


 private Integer id;
 private String nombre;
 private TipoUsuario tipousuario;
 private String identificacion;
 private String email;
 private String telefono;
 private String clave;
 private Boolean status;

 public Integer getId() {
  return id;
 }

 public void setId(Integer id) {
  this.id = id;
 }

 public String getNombre() {
  return nombre;
 }

 public void setNombre(String nombre) {
  this.nombre = nombre;
 }

 public TipoUsuario getTipousuario() {
  return tipousuario;
 }

 public void setTipousuario(TipoUsuario tipousuario) {
  this.tipousuario = tipousuario;
 }

 public String getIdentificacion() {
  return identificacion;
 }

 public void setIdentificacion(String identificacion) {
  this.identificacion = identificacion;
 }

 public String getEmail() {
  return email;
 }

 public void setEmail(String email) {
  this.email = email;
 }

 public String getTelefono() {
  return telefono;
 }

 public void setTelefono(String telefono) {
  this.telefono = telefono;
 }

 public String getClave() {
  return clave;
 }

 public void setClave(String clave) {
  this.clave = clave;
 }

 public Boolean getStatus() {
  return status;
 }

 public void setStatus(Boolean status) {
  this.status = status;
 }

 @Override
 public String toString() {
  return "Usuario{" +
          "id=" + id +
          ", nombre='" + nombre + '\'' +
          ", tipousuario=" + tipousuario +
          ", identificacion='" + identificacion + '\'' +
          ", email='" + email + '\'' +
          ", telefono='" + telefono + '\'' +
          ", clave='" + clave + '\'' +
          ", status=" + status +
          '}';
 }
}
