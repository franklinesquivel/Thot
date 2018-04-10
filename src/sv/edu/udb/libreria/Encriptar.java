/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.libreria;

import java.util.Random;

/**
 *
 * @author Leonardo
 */
public class Encriptar {
    private static String[] espacio = {"-", "*", "/", "!", "?" }; //5
    private static String[] letras = {"a", "b", "c", "d","e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "ñ", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z","A", "B", "C", "D","E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "Ñ", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"}; //53
    private static String[] numeros = {"e", "l", "a", "y", "A", "L", "F", "R","o", "p"}; //9
    
    private static boolean verificarEspacio(String caracter){
        for (int i = 0; i < Encriptar.espacio.length ; i++) {
            if (caracter.equals(Encriptar.espacio[i])) {
                return true;
            }
        }
        return false;
    }
    
    private static int verificarLetra(String caracter){
        for (int i = 0; i < Encriptar.letras.length ; i++) {
            if (caracter.equals(Encriptar.letras[i])) {
                return i;
            }
        }
        return -1;
    }
    
    private static String verificarNumero(String caracter){
        for (int i = 0; i < Encriptar.numeros.length ; i++) {
            if (caracter.equals(String.valueOf(i))) {
                return  Encriptar.numeros[i];
            }
        }
        return "-1";
    }
    
    public static String desencriptar(String password){
        String desencriptada = "", word = "";

       for (int i = 0; i < password.length() ; i++) {
            if (Encriptar.verificarEspacio(String.valueOf(password.charAt(i)))) {
                int longitudPalabra = Integer.parseInt(word.substring(word.length() - 1));
                String AscciCaracter = word.substring(0, longitudPalabra);
                desencriptada += (char) Integer.parseInt(AscciCaracter);
                word = "";
            }else{
                word += password.charAt(i);
            }
        }

        return desencriptada;
    }
    
    public static String encriptar(String password){
        String encriptar = "";
        for (int i=0; i < password.length(); i++) {
            Random rnd = new Random();
            int AscciCaracter = (int) password.charAt(i);
            int longitudCaracter = String.valueOf(AscciCaracter).length();
            int randEspacio = rnd.nextInt(4);
            
            String verificarNumero = Encriptar.verificarNumero(String.valueOf(password.charAt(i)));
            int verificarLetra = Encriptar.verificarLetra(String.valueOf(password.charAt(i)));
            
            if (!verificarNumero.equals("-1")) {
                encriptar += String.valueOf(AscciCaracter) + verificarNumero + longitudCaracter + Encriptar.espacio[randEspacio];
            }else if(verificarLetra != -1){
                encriptar += String.valueOf(AscciCaracter) + String.valueOf(verificarLetra) + longitudCaracter + Encriptar.espacio[randEspacio];
            }else{
                encriptar += String.valueOf(AscciCaracter) + longitudCaracter + Encriptar.espacio[randEspacio];
            }
        }
        return encriptar;
    }
}
