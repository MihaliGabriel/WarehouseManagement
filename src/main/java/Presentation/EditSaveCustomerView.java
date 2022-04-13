package Presentation;

import Model.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class EditSaveCustomerView {
    private JLabel jcomp1;
    private JTextField numetext;
    private JLabel nume;
    private JTextField prenumetext;
    private JLabel prenume;
    private JTextField adresatext;
    private JTextField emailtext;
    private JLabel adresa;
    private JLabel email;
    private JButton savebutton;
    private JFrame frame;
    public Customer customerUpdate;

    public EditSaveCustomerView(Customer c) {

        this.customerUpdate = c;
        frame = new JFrame("Modifica client");
        jcomp1 = new JLabel ("Modifica un client!");
        numetext = new JTextField (5);
        nume = new JLabel ("Nume");
        prenumetext = new JTextField (5);
        prenume = new JLabel ("Prenume");
        adresatext = new JTextField (5);
        emailtext = new JTextField (5);
        adresa = new JLabel ("Adresa");
        email = new JLabel ("Email");
        savebutton = new JButton ("Salveaza");


        frame.setSize(new Dimension (682, 500));
        frame.setLayout(null);
        frame.setVisible(true);

        frame.add(jcomp1);
        frame.add(numetext);
        frame.add(nume);
        frame.add(prenumetext);
        frame.add(prenume);
        frame.add(adresatext);
        frame.add(emailtext);
        frame.add(adresa);
        frame.add(email);
        frame.add(savebutton);


        jcomp1.setBounds (255, 35, 130, 35);
        numetext.setBounds (75, 160, 145, 30);
        nume.setBounds (130, 140, 100, 25);
        prenumetext.setBounds (415, 160, 145, 30);
        prenume.setBounds (455, 140, 100, 25);
        adresatext.setBounds (70, 260, 310, 30);
        emailtext.setBounds (70, 345, 310, 30);
        adresa.setBounds (75, 230, 100, 25);
        email.setBounds (75, 320, 100, 25);
        savebutton.setBounds (280, 415, 100, 25);

    }

    public String getNumetext() {
        return numetext.getText();
    }

    public void setNumetext(String nume) {
        numetext.setText(nume);
    }

    public String getPrenumetext() {
        return prenumetext.getText();
    }

    public void setPrenumetext(String prenume) {
        prenumetext.setText(prenume);
    }

    public String getAdresatext() {
        return adresatext.getText();
    }

    public void setAdresatext(String adresa) {
        adresatext.setText(adresa);
    }

    public String getEmailtext() {
        return emailtext.getText();
    }

    public void setEmailtext(String email) {
        emailtext.setText(email);
    }

    public void saveCustomer(ActionListener e) {
        savebutton.addActionListener(e);
    }

    public void closeView() {
        frame.setVisible(false);
    }
}
