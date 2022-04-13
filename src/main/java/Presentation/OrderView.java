package Presentation;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class OrderView {
    private JList jlistclient;
    private JTextField cantitatetext;
    private JLabel clientselect;
    private JList jlistprodus;
    private JLabel cantitate;
    private JTextField prettext;
    private JLabel pret;
    private JButton comandabutton;
    private JLabel produselect;
    DefaultListModel listModel;
    DefaultListModel listModel2;
    JFrame frame;

    public OrderView() {
        //construct preComponents
        listModel = new DefaultListModel();
        listModel2 = new DefaultListModel();

        //construct components
        frame = new JFrame ("Order");
        jlistclient = new JList (listModel);
        cantitatetext = new JTextField (5);
        clientselect = new JLabel ("Selecteaza client:");
        jlistprodus = new JList (listModel2);
        cantitate = new JLabel ("Cantitatea produsului");
        prettext = new JTextField (5);
        pret = new JLabel ("Pret comanda");
        comandabutton = new JButton ("Plaseaza comanda");
        produselect = new JLabel ("Selecteaza produs:");

        //adjust size and set layout
        frame.setSize(752, 450);
        frame.setLayout(null);
        frame.setVisible(true);
        prettext.setEditable(false);
        //add components
        frame.add(jlistclient);
        frame.add(cantitatetext);
        frame.add(clientselect);
        frame.add(jlistprodus);
        frame.add(cantitate);
        frame.add(prettext);
        frame.add(pret);
        frame.add(comandabutton);
        frame.add(produselect);

        //set component bounds (only needed by Absolute Positioning)
        jlistclient.setBounds (55, 80, 195, 290);
        cantitatetext.setBounds (325, 125, 100, 25);
        clientselect.setBounds (55, 55, 100, 25);
        jlistprodus.setBounds (495, 85, 195, 285);
        cantitate.setBounds (310, 105, 130, 25);
        prettext.setBounds (325, 235, 100, 25);
        pret.setBounds (330, 215, 100, 25);
        comandabutton.setBounds (315, 360, 140, 30);
        produselect.setBounds (500, 60, 130, 25);
    }

    public void addListenerAddOrder(ActionListener a) {
        comandabutton.addActionListener(a);
    }
    public void addList(String x) {
        listModel.addElement(x);
    }
    public void addList2(String x) {
        listModel2.addElement(x);
    }
    public void closeView() {
        frame.setVisible(false);
    }
    public String getValue1() {
        return String.valueOf(jlistclient.getSelectedValue());
    }
    public String getValue2() {
        return String.valueOf(jlistprodus.getSelectedValue());
    }
    public String getCantitate() {
       return cantitatetext.getText();
    }

    public void setPret(String pret) {
        prettext.setText(pret);
    }
}
