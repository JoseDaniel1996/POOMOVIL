/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoogleFCM;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONObject;



/**
 *
 * @author josed
 */
public class FirebaseMessagingSnippets {

    public final static String AUTH_KEY_FCM = "Clave del servidor de Firebase";
    public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";
    public void send_FCM_Notification(String tokenId) {

        try {
// Create URL instance.
            URL url = new URL(API_URL_FCM);
// create connection.
            HttpURLConnection conn;
            conn = (HttpURLConnection) url.openConnection();
            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
//set method as POST or GET
            conn.setRequestMethod("POST");
//pass FCM server key
            conn.setRequestProperty("Authorization", "key=" + AUTH_KEY_FCM);
//Specify Message Format
            conn.setRequestProperty("Content-Type", "application/json");
//Create JSON Object & pass value
            JSONObject infoJson = new JSONObject();

            infoJson.put("title", "Notificación");
            infoJson.put("body", "Bienvenido a la aplicación de notificaciones");

            JSONObject json = new JSONObject();
            json.put("to", tokenId.trim());
            json.put("notification", infoJson);

            System.out.println("json :" + json.toString());
            System.out.println("infoJson :" + infoJson.toString());
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(json.toString());
            wr.flush();
            int status = 0;
            if (null != conn) {
                status = conn.getResponseCode();
            }
            if (status != 0) {

                if (status == 200) {
//Enviar mensaje
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    System.out.println("Respuesta de notificación de Android : " + reader.readLine());
                } else if (status == 401) {
//Error del lado del cliente
                    System.out.println("Respuesta de notificación: TokenId: "+ tokenId +" Se produjo un error:");
                } else if (status == 501) {
//Error del lado del servidor
                    System.out.println("Respuesta de notificación: [errorCode = ServerError] TokenId:" + tokenId);
                } else if (status == 503) {
//Error del servidor
                    System.out.println("Respuesta de notificación: El servicio FCM no está disponible TokenId:" + tokenId);
                }
            }
        } catch (MalformedURLException mlfexception) {
// Error de protocolo
            System.out.println("¡Se produjo un error al enviar la notificación! .." + mlfexception.getMessage());
        } catch (Exception mlfexception) {
            System.out.println("Lectura de URL, ¡Error al enviar la notificación! .." + mlfexception.getMessage());
        }
    }
}

