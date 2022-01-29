package com.example.tareamarcadoresengooglemaps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.tareamarcadoresengooglemaps.Adaptador.AdaptadorInfoWindow;
import com.example.tareamarcadoresengooglemaps.modelo.Marcador;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    //Declarando atributos

    private Button cargar;


    private GoogleMap gMaps;
    private RequestQueue requestQue;
    private String url= "https://my-json-server.typicode.com/AndyBlu/marcadores/Marcadores";
    List<Marcador> listMarcadores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cargar=(Button)findViewById(R.id.cargarMarcadoresUteq);

        //Se consume el servicio web donde se encuentran las facultades
        obtenerMarcadores();


        SupportMapFragment mapFragment= (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    public void cargarMarcadores(View view){

        //Agregamos los marcadores alojados en la lista
        for(Marcador marcadorUteq : listMarcadores) {

            LatLng ltFacultad= new LatLng(Float.parseFloat(marcadorUteq.getLatV())
                    ,Float.parseFloat(marcadorUteq.getLatV1()));

            gMaps.addMarker(new MarkerOptions()
                    .position(ltFacultad)
                    .title(marcadorUteq.getIdMarcador()));
        }

        //Se aplica el diseño del diseno info window.xml
        gMaps.setInfoWindowAdapter(new AdaptadorInfoWindow(MainActivity.this,listMarcadores));


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMaps=googleMap;

        LatLng ltUteq= new LatLng(-1.0118506915092784, -79.46892724061738);


        //Activar los controles del Zoom
        gMaps.getUiSettings().setZoomControlsEnabled(true);

        //Enfocar el zoom del mapa en la UTEQ
        CameraUpdate camUpdUteq= CameraUpdateFactory.newLatLngZoom(ltUteq,17 );
        gMaps.moveCamera(camUpdUteq);


    }

    private void  obtenerMarcadores(){
        JsonArrayRequest requestJson = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        enlistarMarcadores(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error al conectarse:" + error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

        );
        requestQue = Volley.newRequestQueue(this);
        requestQue.add(requestJson);
    }

    private List<Marcador> enlistarMarcadores (JSONArray jArray){

        for (int i=0;i<jArray.length();i++){
            try {
                //Pasamos al objeto json los valores de el primer usuario
                JSONObject objetoJson=new JSONObject(jArray.get(i).toString());

                //Agregamos al objeto Usuario los datos recogidos
                listMarcadores.add(new Marcador(objetoJson.getString("idFacultad"),
                        objetoJson.getString("nomFacultad"),
                        objetoJson.getString("ubicacion"),
                        objetoJson.getString("nomDecano"),
                        objetoJson.getString("latitudV0"),
                        objetoJson.getString("latitudV1"),
                        objetoJson.getString("imgLogo"))
                );


            }
            catch (JSONException e){
                Toast.makeText(this,"Error al cargar los datos al objeto: "+e.getMessage(),Toast.LENGTH_LONG).show();
            }
        }
        return listMarcadores;
    }


}