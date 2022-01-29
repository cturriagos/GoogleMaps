package com.example.tareamarcadoresengooglemaps.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.tareamarcadoresengooglemaps.MarkerCallBack;
import com.example.tareamarcadoresengooglemaps.R;
import com.example.tareamarcadoresengooglemaps.modelo.Marcador;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdaptadorInfoWindow implements GoogleMap.InfoWindowAdapter {

    private View ventana;
    private Context contx;
    private List<Marcador> marcadores;


    public AdaptadorInfoWindow(Context contx, List<Marcador> marcadores) {
        this.contx = contx;
        this.marcadores = marcadores;
        ventana= LayoutInflater.from(contx).inflate(R.layout.diseno_info_window,null);
    }

    private void insertarInformacion (Marker marker, View view){

        TextView facultad, ubicacion, decano;
        ImageView img;

        facultad=(TextView)view.findViewById(R.id.txtFacultad);
        ubicacion=(TextView)view.findViewById(R.id.txtUbicacion);
        decano=(TextView)view.findViewById(R.id.txtDecano);
        img=(ImageView)view.findViewById(R.id.imgFacultad);

        //Cargar la información de las facultades
        try {
            for (Marcador marcadorFacultades : marcadores) {

                if (marcadorFacultades.getIdMarcador().equals(marker.getTitle())) {

                    facultad.setText(marcadorFacultades.getNombreFacultad());
                    ubicacion.setText(marcadorFacultades.getUbiFacultad());
                    decano.setText(marcadorFacultades.getDecano());

                    Picasso.get()
                            .load(marcadorFacultades.getImg().toString())
                            .into(img, new MarkerCallBack(marker));

                }

            }

        }
        catch (Exception e){
            Toast.makeText(contx,"Error al validar el usuario: "+e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public View getInfoWindow(Marker marker) {
        insertarInformacion(marker,ventana);
        return ventana;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return ventana;
    }
}


