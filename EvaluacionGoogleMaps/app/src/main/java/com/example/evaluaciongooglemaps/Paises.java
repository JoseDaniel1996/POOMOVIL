package com.example.evaluaciongooglemaps;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Paises {
    private String name;
    private String iso2;
    private String norte, sur, este, oeste;

    public String getSur() {
        return sur;
    }

    public void setSur(String sur) {
        this.sur = sur;
    }

    public String getEste() {
        return este;
    }

    public void setEste(String este) {
        this.este = este;
    }

    public String getOeste() {
        return oeste;
    }

    public void setOeste(String oeste) {
        this.oeste = oeste;
    }

    public Paises(String name) {
        this.name = name;
    }

    public String getNorte() {
        return norte;
    }

    public void setNorte(String norte) {
        this.norte = norte;
    }

    public Paises(String pname, String iso) {
        name = pname;
        iso2 = "http://www.geognos.com/api/en/countries/flag/" + iso + ".png";
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
        iso2 = "http://www.geognos.com/api/en/countries/flag/" + a.getString("iso2") + ".png";
    }


    public static ArrayList<Paises> JsonObjectsBuild(JSONArray datos) throws JSONException {//revistas.uteq.edu.ec/webservice/noticia/
        ArrayList<Paises> paises = new ArrayList<>();
        for (int i = 0; i < datos.length(); i++) {
            paises.add(new Paises(datos.getJSONObject(i)));
        }
        return paises;
    }

    public Paises() {
    }

    public static ArrayList<Paises> JsonObjectsBuild(JSONObject datos) throws JSONException {
        ArrayList<Paises> paises = new ArrayList<>();
        JSONObject results = datos.getJSONObject("Results");
        JSONArray ListDatos = results.names();
        Paises pais= null;
        for (int i = 0; i < ListDatos.length(); i++) {
            pais = new Paises();
            String nombresListar = ListDatos.getString(i);
            JSONObject obtenerNombres = results.getJSONObject(nombresListar);
            String nombrePais = obtenerNombres.getString("Name");
            JSONObject countCod = obtenerNombres.getJSONObject("CountryCodes");
            String iso2 = countCod.getString("iso2");
            JSONObject latitudes = obtenerNombres.getJSONObject("GeoRectangle");
            pais.setName(obtenerNombres.getString("Name"));
            pais.setNorte(latitudes.getString("North"));
            pais.setSur(latitudes.getString("South"));
            pais.setEste(latitudes.getString("East"));
            pais.setOeste(latitudes.getString("West"));
            pais.setIso2("http://www.geognos.com/api/en/countries/flag/" + iso2 + ".png");
            paises.add(pais);
        }
        return paises;
    }

}
