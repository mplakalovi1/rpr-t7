package ba.unsa.rpr.tutorijal7;

import java.io.Serializable;

public class Grad implements Serializable {
    private String naziv = "";
    private int brojStanovnika;
    private double[] temperature = new double[1000];
    private int brojTemperatura = 0;

    public Grad(){}

    public void dodajTemperaturu(double temp) {
        if(brojTemperatura >= 1000) throw new IllegalArgumentException("Pun niz");
        temperature[brojTemperatura] = temp;
        brojTemperatura++;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getBrojStanovnika() {
        return brojStanovnika;
    }

    public void setBrojStanovnika(int brojStanovnika) {
        this.brojStanovnika = brojStanovnika;
    }

    public double[] getTemperature() {
        return temperature;
    }

    public void setTemperature(double[] temperature) {
        this.temperature = temperature;
    }

    public int getBrojTemperatura() {
        return brojTemperatura;
    }

    public void setBrojTemperatura(int brojTemperatura) {
        this.brojTemperatura = brojTemperatura;
    }

}