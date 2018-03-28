/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.udb.connection.DBConection;
import sv.edu.udb.library.Pais;

/**
 *
 * @author jasso
 */
public class Pais_Model {

    public static List<Pais> obtenerPaises() {
        List<Pais> _Ulist = new ArrayList();
        PreparedStatement obtenerSQL = DBConection.getStatement("SELECT * FROM pais;");
        try {
            ResultSet data = obtenerSQL.executeQuery();
            while (data.next()) {
                _Ulist.add(new Pais(data.getString("idPais"), data.getString("nombre")));
            }
            return _Ulist;
        } catch (SQLException ex) {
            Logger.getLogger(TipoUsuario_Model.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
