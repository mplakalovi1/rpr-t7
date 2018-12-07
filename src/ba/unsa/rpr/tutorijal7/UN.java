package ba.unsa.rpr.tutorijal7;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UN implements Serializable {/*Da bi se klasa mogla serijalizirati u XML datoteku,
potrebno je da slijedi strožiju JavaBean specifikaciju.
To znači da mora imati konstruktor bez parametara i da svi atributi moraju posjedovati settere i gettere po specifikaciji.
 */
    ArrayList<Drzava> listaDrzava = new ArrayList<>();

    public UN() {}

    public ArrayList<Drzava> getListaDrzava() {
        return listaDrzava;
    }

    public void setListaDrzava(ArrayList<Drzava> listaDrzava) {
        this.listaDrzava = listaDrzava;
    }
}