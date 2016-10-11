package mx.uach.videoclub.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.uach.videoclub.conexiones.Conexion;
import mx.uach.videoclub.dao.VideoDao;
import mx.uach.videoclub.dao.enums.CRUD;
import mx.uach.videoclub.dao.jdbc.helpers.VideoDaoJdbcHelper;
import mx.uach.videoclub.modelos.Director;

/**
 *
 * @author luis
 */
public class VideoDaoJDBC implements VideoDao {

    public VideoDaoJDBC() {
    }

    /**
     * Regresa un director basado en un id del registro de la base de datos.
     *
     * @param id entero que identifica la entidad.
     * @return null si el id no se encuentra en la base de datos ó un
     * {@code Director} si el id es valido.
     */
    @Override
    public Model getById(Integer id, Model model) {
        Statement st = Conexion.getInstance().getCon().createStatement();

        try {
            ResultSet rs = st.executeQuery(String.format("%s %s %s ", Model.Q,
                    Model.Q_WHRE_ID, id));
            Model obj = null;
            while (rs.next()) {
                obj = ModelDaoJdbcHelper.makeModel(rs, model.getTable());
            }
            return obj;
        } catch (SQLException ex) {

        }
        return null;
    }

    @Override
    public List<Model> getByCriteria(String criterio, Model model) {
        List<Model> objects = new ArrayList<>();
        try {
            Statement st = Conexion.getInstance().getCon().createStatement();
            ResultSet rs = st.executeQuery(String.format("%s %s %s ", Director.Q,
                    criterio.isEmpty() ? "" : Director.Q_WHERE, criterio));
            Model obj = null;
            while (rs.next()) {
                obj = ModelDaoJdbcHelper.makeModel(rs, model.gettable());
                objects.add(obj);
            }

        } catch (SQLException ex) {

        }
        return objects;
    }

    @Override
    public void Process(CRUD crud, Model model) {
        try {
            PreparedStatement ps = null;
            switch (crud) {
                case CREATE:
                    ps = Conexion.getInstance().
                    getCon().prepareStatement(model.queryInstert);

                    for(int i = 0; i < model.fields.lenght; i++){
                        ps.setString(i+1, "") // Nombre o socio id u otro campo?;

                    }
                    break;
                case UPDATE:
                    //UPDATE TABLA SET()
                    ps = Conexion.getInstance().
                    getCon().prepareStatement(Director.UPDATE_DIRECTOR);
                    ps.setString(1, director.getNombre());
                    ps.setInt(2, director.getId());
                    break;
                case DELETE:
                    ps = Conexion.getInstance().
                    getCon().prepareStatement(Director.DELETE_DIRECTOR);
                    ps.setInt(1, director.getId());
                    break;
                default:
                    break;
            }
            
            Boolean result = ps.execute();            
            
        } catch (SQLException ex) {
            System.out.println( ex.getMessage());
        }
        
    }

}
