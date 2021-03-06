package ba.unsa.rpr.tutorijal7;

import java.io.Serializable;

public class Drzava implements Serializable { /*Da bi se klasa mogla serijalizirati u XML datoteku,
potrebno je da slijedi strožiju JavaBean specifikaciju.
To znači da mora imati konstruktor bez parametara i da svi atributi moraju posjedovati settere i gettere po specifikaciji.
 */
    private String naziv = "";
    private int brojStanovnika;
    private double povrsina;
    private String jedinicaPovrsine = "";
    private Grad glavniGrad = new Grad();

    public Drzava(){}

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

    public double getPovrsina() {
        return povrsina;
    }

    public void setPovrsina(double povrsina) {
        this.povrsina = povrsina;
    }

    public String getJedinicaPovrsine() {
        return jedinicaPovrsine;
    }

    public void setJedinicaPovrsine(String jedinicaPovrsine) {
        this.jedinicaPovrsine = jedinicaPovrsine;
    }

    public Grad getGlavniGrad() {
        return glavniGrad;
    }

    public void setGlavniGrad(Grad glavniGrad) {
        this.glavniGrad = glavniGrad;
    }
}
