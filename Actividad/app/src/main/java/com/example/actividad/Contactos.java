package com.example.actividad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class Contactos extends AppCompatActivity  implements Asynchtask {
    TextView txtsaludo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saludo);

        txtsaludo = (TextView)findViewById(R.id.txtsaludo);
        Map<String, String> datos = new HashMap<String, String>();


        WebService ws= new WebService("https://api.androidhive.info/contacts/", datos, Contactos.this, Contactos.this);
        ws.execute("");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        String id="";
        String name="";
        String email="";
        String address="";
        String texto="";
        String mobile="";
        Log.i("processFinish", result);
       // txtsaludo.setText(result);
        ArrayList<HashMap<String, String>>contactList = null;

        JSONObject jsonObj = new JSONObject(result);
        JSONArray contacts = jsonObj.getJSONArray("contacts");
        for(int i=0;i<contacts.length();i++) {
            JSONObject c = contacts.getJSONObject(i);
            id = c.getString("id");
            name = c.getString("name");
            email = c.getString("email");
            address = c.getString("address");

            JSONObject phone = c.getJSONObject("phone");
            mobile = phone.getString("mobile");

            texto = "ID : "+id +"\n NAME:"+name +"\n EMAIL :"+ email+"\n DIRECCION : "+address +"\n MOVIL : "+ mobile;
            texto = txtsaludo.getText() + texto +"\n \n \n";
            txtsaludo.setText(texto);

        }
        txtsaludo.setMovementMethod(new ScrollingMovementMethod());
    }
}
