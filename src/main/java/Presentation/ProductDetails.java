package Presentation;
import Model.Product;

import java.awt.*;
import java.awt.event.*;
import java.sql.CallableStatement;
import javax.swing.*;
import javax.swing.event.*;

public class ProductDetails{
    private JLabel jcomp1;
    private JTextField numetext;
    private JLabel nume;
    private JTextField prettext;
    private JLabel pret;
    private JFrame frame;
    private JLabel stoc;
    private JTextField stoctext;

    public ProductDetails() {

        stoc = new JLabel("Stoc");
        stoctext = new JTextField(5);
        frame = new JFrame("Detalii produs");
        jcomp1 = new JLabel ("Detalii produs");
        numetext = new JTextField (5);
        nume = new JLabel ("Nume");
        prettext = new JTextField (5);
        pret = new JLabel ("Pret");


        frame.setSize(new Dimension (682, 500));
        frame.setLayout(null);
        frame.setVisible(true);


        frame.add(jcomp1);
        frame.add(numetext);
        frame.add(nume);
        frame.add(prettext);
        frame.add(pret);
        frame.add(stoc);
        frame.add(stoctext);


        jcomp1.setBounds (255, 35, 140, 35);
        numetext.setBounds (50, 150, 270, 35);
        nume.setBounds (155, 125, 100, 25);
        prettext.setBounds (415, 150, 165, 35);
        pret.setBounds (480, 125, 100, 25);
        stoctext.setBounds(290, 225, 100, 25);
        stoc.setBounds(320, 200, 100, 25);
        numetext.setEditable(false);
        prettext.setEditable(false);
        stoctext.setEditable(false);
    }

    public String getStoctext() {
        return stoctext.getText();
    }

    public void setStoctext(String stoc) {
        stoctext.setText(stoc);
    }

    public void setNumetext(String nume) {
        numetext.setText(nume);
    }

    public void setPrettext(String pret) {
        prettext.setText(pret);
    }

    public void closeView() {
        frame.setVisible(false);
    }
}
