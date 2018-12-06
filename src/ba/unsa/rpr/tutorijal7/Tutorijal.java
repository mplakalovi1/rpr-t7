package ba.unsa.rpr.tutorijal7;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Tutorijal {

    public Tutorijal() {}

    public static ArrayList<Grad> ucitajGradove() {
        Scanner scan = null;
        ArrayList<Grad> gradovi = new ArrayList<>();
        try {
            scan = new Scanner(new FileReader("mjerenja.txt"));
            String tmp;
            double temperatura;
            int indexGrada = 0, indexTemp = 0;
            scan.useDelimiter("\n");
            while(scan.hasNext()) {
                Grad tmpGrad = new Grad();
                tmp = scan.next();
                String[] parts = tmp.split(",");
                tmpGrad.setNaziv(parts[0]);
                for(int i = 1; i < parts.length; i++) {
                    if (indexTemp < 1000) {
                        tmpGrad.dodajTemperaturu(Double.parseDouble(parts[i]));
                        indexTemp++;
                    }
                }
                indexTemp = 0;
                gradovi.add(tmpGrad);
            }
        } catch (Exception e) {
            System.out.print("Error");
        } finally {
            scan.close();
        }
        return gradovi;
    }

    public static UN ucitajXml( ArrayList<Grad> gradovi)  {
        Document xmldoc = null;
        UN un = new UN();
        try {
            DocumentBuilder docReader = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            xmldoc = docReader.parse(new File("drzave2.xml"));
        } catch (Exception e) {
            System.out.println("drzave.xml nije validan XML dokument");
        }
        try {
            NodeList listaDrzava = xmldoc.getElementsByTagName("drzava");

            for (int i = 0; i < listaDrzava.getLength(); i++) {
                Node drzava = listaDrzava.item(i);
                if(drzava.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) drzava;
                    String nazivGrada ="";
                    int stanovniciGrad = 0;
                    String nazivDr = element.getElementsByTagName("naziv").item(0).getTextContent();
                    String jedinicaPovrs = element.getElementsByTagName("jedinicaPovrsine").item(0).getTextContent();
                    int brStanovnika = Integer.parseInt(element.getElementsByTagName("brojStanovnika").item(0).getTextContent());
                    double povrsina = Double.parseDouble(element.getElementsByTagName("povrsina").item(0).getTextContent());
                    NodeList tempList = element.getElementsByTagName("glavniGrad");
                    Node tempNode = tempList.item(0);
                    if(tempNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element element1 = (Element) tempNode;
                        nazivGrada = element1.getElementsByTagName("naziv").item(0).getTextContent();
                        stanovniciGrad = Integer.parseInt(element1.getElementsByTagName("brojStanovnika").item(0).getTextContent());
                    }
                    Grad tmpGrad = new Grad();
                    Drzava tmpDrzava = new Drzava();
                    tmpGrad.setNaziv(nazivGrada);
                    tmpGrad.setBrojStanovnika(stanovniciGrad);
                    tmpDrzava.setGlavniGrad(tmpGrad);
                    tmpDrzava.setNaziv(nazivDr);
                    tmpDrzava.setJedinicaPovrsine(jedinicaPovrs);
                    tmpDrzava.setBrojStanovnika(brStanovnika);
                    tmpDrzava.setPovrsina(povrsina);
                    un.getListaDrzava().add(tmpDrzava);
                    for(Grad g : gradovi) {
                        if(g.getNaziv().equals(tmpGrad.getNaziv())) {
                            tmpGrad.setTemperature(g.getTemperature());
                            tmpGrad.setBrojTemperatura(g.getBrojTemperatura());
                        }
                    }
                }

            }

        } catch (Exception e) {
            System.out.println("greskaa");
        }


        return un;
    }

    public static void zapisiXML(UN u) {
        try {
            java.beans.XMLEncoder izlaz = new XMLEncoder(new FileOutputStream("un.xml"));
            izlaz.writeObject(u);
            izlaz.close();
        } catch(Exception e) {
            System.out.println("Greška: " + e.getMessage());
        }
    }



    public static void main(String[] args) {

        ArrayList<Grad> gradovi = ucitajGradove();
        UN un = ucitajXml(gradovi);

        ArrayList<Drzava> listaDrzava = un.getListaDrzava();
        for (int i = 0; i < listaDrzava.size(); i++) {
            System.out.println("Naziv drzave: " + listaDrzava.get(i).getNaziv());
            System.out.println("Broj stanovnika: " + listaDrzava.get(i).getBrojStanovnika());
            System.out.println("Povrsina: " + listaDrzava.get(i).getPovrsina());
            System.out.println("Jedinica povrsine: " + listaDrzava.get(i).getJedinicaPovrsine());
            System.out.println("Glavni grad: " + listaDrzava.get(i).getGlavniGrad().getNaziv());
            double[] temperature = listaDrzava.get(i).getGlavniGrad().getTemperature();
            if(listaDrzava.get(i).getGlavniGrad().getBrojTemperatura() > 0)System.out.println("Temperature ");
            for(int j = 0; j < listaDrzava.get(i).getGlavniGrad().getBrojTemperatura(); j++) System.out.println(temperature[j]);

        }

    }
}
