package mx.uach.videoclub.modelos.genericos;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Modelo general de todo el sistema.
 *
 * @author Luis Antonio Ramirez Martinez
 * @version 1.0
 *
 */
public class Model {

    public static final String Q_WHRE_ID = "WHERE id = ";

    public static final String Q_WHERE = "WHERE";

    public static final String INSERT = "INSERT INTO";
    
    public static final String UPDATE = "UPDATE";
    
    public static final String DELETE = "DELETE FROM";

    public static final String ID = "id";

    protected  String tabla = "";

    protected  String[] fields = {};

    public static final String Q = String.format("SELECT %s FROM %s",
            fieldsToQuery(FIELDS, Boolean.FALSE), TABLA);

    protected String queryInstert =
           "";

    public static final String UPDATE_DIRECTOR =
            String.format("%s %s SET %s WHERE %s = ?",
                    Model.UPDATE, TABLA, paramsToStatementToCreate(FIELDS, Boolean.TRUE),
                    ID);

    public static final String DELETE_DIRECTOR =
            String.format("%s %s %s ?", Model.DELETE, TABLA, Model.Q_WHRE_ID);

    private Integer id;

    public Model() {
    }

    public Model(Integer id) {
        this.id = id;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Convierte de un arreglo de campos a un String para un query.
     *
     * @param fields son los atributos de la tabla.
     * @param noId si es verdadero excluye el campo id.
     * @return attr1, attr2, ... attrn
     */
    public static String fieldsToQuery(String[] fields, Boolean noId) {
        String campos = "";
        List<String> fieldsList = Arrays.asList(fields);
        fieldsList = noId
                ? fieldsList.stream()
                .filter(field -> !field.equals(ID))
                .collect(Collectors.toList()) : fieldsList;
        for (String field : fieldsList) {
            campos = String.format("%s, %s", campos, field);
        }
        return campos.substring(1);
    }

    public static String paramsToStatement(String[] fields, Boolean noId) {
        String campos = "";
        List<String> fieldsList = Arrays.asList(fields);
        fieldsList = noId
                ? fieldsList.stream()
                .filter(field -> !field.equals(ID))
                .collect(Collectors.toList()) : fieldsList;
        for (String field : fieldsList) {
            campos = String.format("%s, ?", campos);
        }
        return campos.substring(1);
    }
    
    public static String paramsToStatementToCreate(String[] fields, Boolean noId) {
        String campos = "";
        List<String> fieldsList = Arrays.asList(fields);
        fieldsList = noId
                ? fieldsList.stream()
                .filter(field -> !field.equals(ID))
                .collect(Collectors.toList()) : fieldsList;
        for (String field : fieldsList) {
            campos = String.format("%s, %s = ?", campos, field);
        }
        return campos.substring(1);
    }

}
