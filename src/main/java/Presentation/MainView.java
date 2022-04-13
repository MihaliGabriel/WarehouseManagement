package Presentation;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class MainView{
    private JButton adaugaclientbutton;
    private JButton adaugaprodusbutton;
    private JButton editprodusbutton;
    private JButton editclientbutton;
    private JButton tableclientbutton;
    private JButton tableproductbutton;
    private JButton plaseazacomandabutton;
    private JFrame frame;
    public MainView() {
        //construct components
        frame = new JFrame("Main");
        adaugaclientbutton = new JButton("Adauga Client");
        adaugaprodusbutton = new JButton("Adauga Produs");
        editprodusbutton = new JButton("Editeaza produse");
        editclientbutton = new JButton("Editeaza clienti");
        tableclientbutton = new JButton("Vizualizeaza clienti");
        tableproductbutton = new JButton("Vizualizeaza produse");
        plaseazacomandabutton = new JButton("Comanda");


        frame.setSize(new Dimension(682, 500));
        frame.setLayout(null);
        frame.setVisible(true);

        frame.add(adaugaclientbutton);
        frame.add(adaugaprodusbutton);
        frame.add(editprodusbutton);
        frame.add(editclientbutton);
        frame.add(tableclientbutton);
        frame.add(tableproductbutton);
        frame.add(plaseazacomandabutton);
        adaugaclientbutton.setBounds(95, 105, 145, 50);
        adaugaprodusbutton.setBounds(425, 105, 145, 50);
        editprodusbutton.setBounds(95, 205, 145, 50);
        editclientbutton.setBounds(425, 205, 145, 50);
        tableclientbutton.setBounds(425, 305, 145, 50);
        tableproductbutton.setBounds(95, 305, 160, 50);
        plaseazacomandabutton.setBounds(260, 40, 160, 50);

    }
        public void addComandaListener(ActionListener g) {
            plaseazacomandabutton.addActionListener(g);
        }
        public void addProdusListener(ActionListener a) {
            adaugaprodusbutton.addActionListener(a);
        }

        public void addClientListener(ActionListener b) {
            adaugaclientbutton.addActionListener(b);
        }

        public void addTableClientListener(ActionListener e) {
            tableclientbutton.addActionListener(e);
        }

        public void addTableProductListener(ActionListener f) {
            tableproductbutton.addActionListener(f);
        }

        public void addEditListener(ActionListener c) {
            editprodusbutton.addActionListener(c);
        }

        public void addEditCustomerListener(ActionListener d) {
            editclientbutton.addActionListener(d);
        }

}
