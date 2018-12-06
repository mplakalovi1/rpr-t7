package ba.unsa.rpr.tutorijal7;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UN implements Serializable {
    ArrayList<Drzava> listaDrzava = new ArrayList<>();

    public UN() {}

    public ArrayList<Drzava> getListaDrzava() {
        return listaDrzava;
    }

    public void setListaDrzava(ArrayList<Drzava> listaDrzava) {
        this.listaDrzava = listaDrzava;
    }
}