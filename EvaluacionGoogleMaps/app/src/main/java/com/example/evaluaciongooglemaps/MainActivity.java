package com.example.evaluaciongooglemaps;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Switch;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask, AdapterView.OnItemClickListener {
    String metodo = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws = new WebService("http://www.geognos.com/api/en/countries/info/all.json", datos, this, this);
        metodo = "paises";
        ws.execute("");
        ListView listPais = (ListView) findViewById(R.id.listaPaises);
        listPais.setOnItemClickListener(this);


    }

    @Override
    public void processFinish(String result) throws JSONException {

        JSONObject paises = new JSONObject(result);
        ArrayList<Paises> listaArticulos;
        listaArticulos = Paises.JsonObjectsBuild(paises);
        PaisesAdaptador adaptadornoticias = new PaisesAdaptador(this, listaArticulos);
        ListView lsOpciones = (ListView) findViewById(R.id.listaPaises);
        lsOpciones.setAdapter(adaptadornoticias);

    }

    Paises pais = null;

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        pais = (Paises) adapterView.getItemAtPosition(position);
        SharedPreferences datospaises = getSharedPreferences("datos_posicion", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = datospaises.edit();
        editor.putString("norte", pais.getNorte());
        editor.putString("sur", pais.getSur());
        editor.putString("este", pais.getEste());
        editor.putString("oeste", pais.getOeste());
        editor.putString("nombre", pais.getName());
        editor.putString("imagen", pais.getIso2());
        editor.commit();
        startActivity(new Intent(getApplicationContext(), Mapa.class));
    }
}
