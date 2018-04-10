/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.libreria;

import javax.mail.Session;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**@author Leonardo */
public class Email {
    private String receptor;
    private String emisor = "";
    private String contrasenna = "";
    private String nombre = "Thot"; //Nombre de la compañía.
    private String smtpServidor = "smtp.gmail.com"; //Servidor SMTP de gmail
    private int puerto = 587;
    /**
     * @return the receptor
     */
    public String getReceptor() {
        return receptor;
    }

    /**
     * @param receptor the receptor to set
     */
    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }

    /**
     * @return the emisor
     */
    public String getEmisor() {
        return emisor;
    }

    /**
     * @param emisor the emisor to set
     */
    public void setEmisor(String emisor) {
        this.emisor = emisor;
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

    /**
     * @return the smtServidor
     */
    public String getSmtpServidor() {
        return smtpServidor;
    }

    /**
     * @param smtServidor the smtServidor to set
     */
    public void setSmtpServidor(String smtServidor) {
        this.smtpServidor = smtServidor;
    }
    
    public Email(String receptor){ //Contructor
        this.receptor = receptor;
    }
    
    public boolean enviar(String contenido, String titulo){
        boolean respuesta = true;
        
        Properties props = System.getProperties();
        props.put("mail.smtp.host", this.smtpServidor);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", this.puerto);
        String emisor = this.emisor;
        String contrasenna = this.contrasenna;
        
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(emisor, contrasenna);
            }
        });
        Message mensaje = new MimeMessage(session);
        
        try{
            mensaje.setFrom(new InternetAddress(this.emisor));
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(this.receptor));
            mensaje.setSubject(titulo);
            mensaje.setContent(contenido, "text/html; charset=utf-8");
            Transport.send(mensaje);
        }catch(MessagingException msg){
            respuesta = false;
        }
        return respuesta;
    }//FIN método de enviar
}

