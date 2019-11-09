package com.example.paises;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AdaptadorPaises extends ArrayAdapter<Paises> {
    public AdaptadorPaises(Context context, ArrayList<Paises> datos) {
        super(context, R.layout.ly_paises, datos);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.ly_paises, null);
        TextView txtTitulo = (TextView)item.findViewById(R.id.lbltitulo);
        txtTitulo.setText(getItem(position).getName());
        ImageView imageView = (ImageView) item.findViewById(R.id.imgNoticia);
         Glide.with(this.getContext()).load(getItem(position).getIso2()).error(R.drawable.error).into(imageView);
        return (item);
    }

}
