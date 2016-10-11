package mx.uach.videoclub.dao.jdbc.helpers;

import java.sql.ResultSet;
import java.sql.SQLException;
import mx.uach.videoclub.modelos.Director;

/**
 * Helper que genera objetos del Dao
 *
 * @author Luis Antonio Ramirez
 */
public class ModelDaoJdbcHelper {

    public final static Model makeModel(ResultSet rs, String table, Model model) throws SQLException {
        Model model = null;
        switch(table){
            case "Director":
                model = new Director(rs.getInt(model.FIELDS[0]),
                        rs.getString(model.FIELDS[1]));
                break;
            case "Socio":
                model = new Socio(rs.getString(model.FIELDS[0]),
                        rs.getString(model.FIELDS[1]));
                break;
            case "Actor":
                model = new Actor(rs.getString(model.FIELDS[0]),
                        rs.getString(model.FIELDS[1]));
                break;
                
            case "Ficha":
                model = new Ficha(rs.getString(model.FIELDS[0]),
                        rs.getString(model.FIELDS[1]));
                break;
            
            case "Lista":
                model = new Lista(rs.getString(model.FIELDS[0]),
                        rs.getString(model.FIELDS[1]));
                break;
                
            case "Cinta":
                model = new Cinta(rs.getString(model.FIELDS[0]),
                        rs.getString(model.FIELDS[1]));
                break;
                
            case "Pelicula":
                model = new Pelicula(rs.getString(model.FIELDS[0]),
                        rs.getString(model.FIELDS[1]));
                break;
            
            case "Prestamo":
                model = new Prestamo(rs.getString(model.FIELDS[0]),
                        rs.getString(model.FIELDS[1]));
                break;
        }

        return model;
    }

}
