package com.example.listview_clase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class MainActivity extends AppCompatActivity  implements Asynchtask {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://api.androidhive.info/contacts/", datos, this, this);
        ws.execute("");



    }
    ArrayList<HashMap<String, String>> contactList;
    public  Noticias[] noticias;
    //DataSource
 //   public Noticias[] noticias = new Noticias[]
  //          {
   //                 new Noticias("Noticia 1", "SubNoticia Contenido  Contenido Contenido Contenido 1"),
   //                 new Noticias("Noticia 2", "SubNoticia Contenido  Contenido Contenido Contenido 2"),
   //                 new Noticias("Noticia 3", "SubNoticia Contenido  Contenido Contenido Contenido 3"),
   //                 new Noticias("Noticia 4", "SubNoticia Contenido  Contenido Contenido Contenido 4")
  //};

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
        ArrayList<HashMap<String, String>> contactList = null;

        JSONObject jsonObj = new JSONObject(result);
        JSONArray contacts = jsonObj.getJSONArray("contacts");
        noticias = new Noticias[13];
        for(int i=0;i<contacts.length();i++) {
            JSONObject c = contacts.getJSONObject(i);
            id = c.getString("id");
            name = c.getString("name");
            email = c.getString("email");
            address = c.getString("address");

            JSONObject phone = c.getJSONObject("phone");
            mobile = phone.getString("mobile");
           noticias[i]= new Noticias(name,email);
         //   texto = "ID : "+id +"\n NAME:"+name +"\n EMAIL :"+ email+"\n DIRECCION : "+address +"\n MOVIL : "+ mobile;
          //  texto = txtsaludo.getText() + texto +"\n \n \n";
          //  txtsaludo.setText(texto);

            //contactList.add();
          //  noticias = new Noticias[]{new Noticias(name,email),};
        }
        AdaptadorNoticias adaptadornoticias = new AdaptadorNoticias(this, noticias);
        ListView lsOpciones = (ListView)findViewById(R.id.lstLista);
        //View header = getLayoutInflater().inflate( R.layout.ly_encabezado , null);
       // lsOpciones.addHeaderView(header);
        lsOpciones.setAdapter(adaptadornoticias);
    }
}
