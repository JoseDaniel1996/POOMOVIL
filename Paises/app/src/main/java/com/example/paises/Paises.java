package com.example.paises;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Paises {
    private String name;
    private String iso2;

    public Paises(String name) {
        this.name = name;
    }

    public Paises(String pname, String iso) {
        name = pname;
        iso2 = "http://www.geognos.com/api/en/countries/flag/"+iso+".png";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIso2() {
        return iso2;
    }

    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }
    public Paises(JSONObject a) throws JSONException {
        name = a.getString("Name").toString();
        iso2 = "http://www.geognos.com/api/en/countries/flag/"+a.getString("iso2")+".png";
    }


    public static ArrayList<Paises> JsonObjectsBuild(JSONArray datos) throws JSONException {//revistas.uteq.edu.ec/webservice/noticia/
        ArrayList<Paises> paises = new ArrayList<>();
        for (int i = 0; i < datos.length(); i++) {
            paises.add(new Paises(datos.getJSONObject(i)));
        }
        return paises;
    }

    public static ArrayList<Paises> JsonObjectsBuild(JSONObject datos) throws JSONException {
        ArrayList<Paises> paises = new ArrayList<>();
        JSONObject results=datos.getJSONObject("Results");
        JSONArray ListDatos =results.names();
        for (int i = 0; i < ListDatos.length(); i++) {
            String nombresListar = ListDatos.getString(i);
            JSONObject obtenerNombres=results.getJSONObject(nombresListar);
            String nombrePais= obtenerNombres.getString("Name");
            JSONObject countCod =obtenerNombres.getJSONObject("CountryCodes");
            String iso2=countCod.getString("iso2");
            paises.add(new Paises(nombrePais,iso2));
        }

        return paises;
    }

}
