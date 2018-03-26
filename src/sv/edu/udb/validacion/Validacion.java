/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.validacion;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author Leonardo
 */
public class Validacion {
    public static boolean validar(String rgx, String campo, String mensaje, String apartado){
        boolean response = false;
        Pattern pat = Pattern.compile(rgx);
        Matcher mat = pat.matcher(campo);
        
        if(mat.matches()){
            response = true;
        }else{
            JOptionPane.showMessageDialog(null, mensaje, apartado, JOptionPane.ERROR_MESSAGE);
        }
        return response;
    }
}
