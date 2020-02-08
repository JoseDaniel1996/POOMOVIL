package com.example.evaluaciongooglemaps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;


public class Mapa extends FragmentActivity implements OnMapReadyCallback {
    GoogleMap mapa;
    String nombre, img, norte, sur, este, oeste;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        SharedPreferences prefs = getSharedPreferences("datos_posicion", Context.MODE_PRIVATE);
        nombre = prefs.getString("nombre", "");
        img = prefs.getString("imagen", "");
        norte = prefs.getString("norte", "");
        sur = prefs.getString("sur", "");
        este = prefs.getString("este", "");
        oeste = prefs.getString("oeste", "");
       // TextView txtNombre = findViewById(R.id.txtNombre);
        ImageView imagen = (ImageView) findViewById(R.id.bandera);
        Glide.with(this).load(img).error(R.drawable.error).into(imagen);
       // txtNombre.setText(nombre);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager() .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapa = googleMap;
        mapa.getUiSettings().setZoomControlsEnabled(true);
        mapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);
      //  CameraUpdate camUpd1 = CameraUpdateFactory.newLatLngZoom(new LatLng(-1.012618, -79.469217), 18);
      //  mapa.moveCamera(camUpd1);

      LatLng punto =  new LatLng(Double.parseDouble(norte), Double.parseDouble(oeste)); mapa.addMarker(new MarkerOptions().position(punto) .title(nombre));

        PolylineOptions lineas = new PolylineOptions()
                .add(new LatLng(Double.parseDouble(norte), Double.parseDouble(oeste)))
                .add(new LatLng(Double.parseDouble(norte), Double.parseDouble(este)))
                .add(new LatLng(Double.parseDouble(sur), Double.parseDouble(este)))
                .add(new LatLng(Double.parseDouble(sur), Double.parseDouble(oeste)))
                .add(new LatLng(Double.parseDouble(norte), Double.parseDouble(oeste)));
        lineas.width(8); lineas.color(Color.RED);
        mapa.addPolyline(lineas);


   //   mapa.addMarker(new MarkerOptions().position(punto)).setIcon(BitmapDescriptorFactory.fromResource( Glide.with(this.getContext()).load(getItem(position).getIso2()).error(R.drawable.error).into(imageView);));

        CameraUpdate movercamara = CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble(norte), Double.parseDouble(oeste)),4 );
        mapa.moveCamera(movercamara);

    }
}
