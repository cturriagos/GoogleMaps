package com.example.tareamarcadoresengooglemaps.modelo;

public class Marcador {

     String idMarcador;
     String nombreFacultad;
     String ubiFacultad;
     String decano;
     String latV;
     String latV1;
     String img;

    public Marcador() {
    }

    public Marcador(String idMarcador, String nombreFacultad, String ubiFacultad, String decano, String latV, String latV1, String img) {
        this.idMarcador = idMarcador;
        this.nombreFacultad = nombreFacultad;
        this.ubiFacultad = ubiFacultad;
        this.decano = decano;
        this.latV = latV;
        this.latV1 = latV1;
        this.img = img;
    }

    public String getIdMarcador() {
        return idMarcador;
    }

    public void setIdMarcador(String idMarcador) {
        this.idMarcador = idMarcador;
    }

    public String getNombreFacultad() {
        return nombreFacultad;
    }

    public void setNombreFacultad(String nombreFacultad) {
        this.nombreFacultad = nombreFacultad;
    }

    public String getUbiFacultad() {
        return ubiFacultad;
    }

    public void setUbiFacultad(String ubiFacultad) {
        this.ubiFacultad = ubiFacultad;
    }

    public String getDecano() {
        return decano;
    }

    public void setDecano(String decano) {
        this.decano = decano;
    }

    public String getLatV() {
        return latV;
    }

    public void setLatV(String latV) {
        this.latV = latV;
    }

    public String getLatV1() {
        return latV1;
    }

    public void setLatV1(String latV1) {
        this.latV1 = latV1;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}

