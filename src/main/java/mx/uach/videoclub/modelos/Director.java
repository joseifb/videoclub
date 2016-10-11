package mx.uach.videoclub.modelos;

import mx.uach.videoclub.modelos.genericos.Model;

/**
 * Modelo para mappear los directores de las Peliculas del VideoClub
 *
 * @author Luis Antonio Ramirez Martinez
 * @version 1.0
 *
 */
public class Director extends Model {

    private String nombre;

    public Director() {
        this(null, null);
    }

    public Director(String nombre) {
        this(null, nombre);
    }

    public Director(Integer id, String nombre) {
        this(nombre);
        this.setId(id);
        tabla = "Director";
        fields = {"id", "nombre"};
        queryInstert = String.format("%s %s (%s) VALUES (%s);",
                Model.INSERT, TABLA, fieldsToQuery(FIELDS, Boolean.TRUE),
                paramsToStatement(FIELDS, Boolean.TRUE))
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
