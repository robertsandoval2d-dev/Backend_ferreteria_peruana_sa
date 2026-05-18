package com.ferreteriapsa.logistica.trabajador.dto.response;

public class TrabajadorResponse {
    private Long trabajadorId;
    private String rol;
    private String username;
    private Boolean cuentaActiva;
    private String nombre;
    private String dni;
    private Long tiendaId;
    private String nombreTienda;
    private Long lineaId;
    private String nombreLinea;

    public TrabajadorResponse() {
    }
    public TrabajadorResponse(Long trabajadorId,String rol, String username, Boolean cuentaActiva, String nombre, String dni) {
        this.trabajadorId = trabajadorId;
        this.rol = rol;
        this.username = username;
        this.cuentaActiva = cuentaActiva;
        this.nombre = nombre;
        this.dni = dni;
    }
    public TrabajadorResponse(Long trabajadorId,String rol, String username,Boolean cuentaActiva, String nombre, String dni,
        Long tiendaId, String nombreTienda, Long lineaId, String nombreLinea
    ) {
        this.trabajadorId = trabajadorId;
        this.rol = rol;
        this.username = username;
        this.cuentaActiva = cuentaActiva;
        this.nombre = nombre;
        this.dni = dni;
        this.tiendaId = tiendaId;
        this.nombreTienda = nombreTienda;
        this.lineaId = lineaId;
        this.nombreLinea = nombreLinea;
    }

    public Long getTrabajadorId(){
        return trabajadorId;
    }
    public void setTrabajadorId(Long trabajadorId){
        this.trabajadorId = trabajadorId;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }
    public String getDni(){
        return dni;
    }
    public void setDni(String dni){
        this.dni = dni;
    }
    public Long getTiendaId(){
        return tiendaId;
    }
    public String getNombreTienda(){
        return nombreTienda;
    }
    public Long getLineaId(){
        return lineaId;
    }
    public Boolean getCuentaActiva(){
        return cuentaActiva;
    }
    public String getNombreLinea(){
        return nombreLinea;
    }
}
