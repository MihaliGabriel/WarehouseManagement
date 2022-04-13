package Presentation;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class CustomerDetails{

    private JLabel jcomp1;
    private JTextField numetext;
    private JLabel nume;
    private JTextField prenumetext;
    private JLabel prenume;
    private JTextField adresatext;
    private JTextField emailtext;
    private JLabel adresa;
    private JLabel email;
    private JFrame frame;

    public CustomerDetails() {

        frame = new JFrame("Detalii client");
        jcomp1 = new JLabel ("Detalii client");
        numetext = new JTextField (5);
        nume = new JLabel ("Nume");
        prenumetext = new JTextField (5);
        prenume = new JLabel ("Prenume");
        adresatext = new JTextField (5);
        emailtext = new JTextField (5);
        adresa = new JLabel ("Adresa");
        email = new JLabel ("Email");


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


        jcomp1.setBounds (255, 35, 130, 35);
        numetext.setBounds (75, 160, 145, 30);
        nume.setBounds (130, 140, 100, 25);
        prenumetext.setBounds (415, 160, 145, 30);
        prenume.setBounds (455, 140, 100, 25);
        adresatext.setBounds (70, 260, 310, 30);
        emailtext.setBounds (70, 345, 310, 30);
        adresa.setBounds (75, 230, 100, 25);
        email.setBounds (75, 320, 100, 25);
        numetext.setEditable(false);
        prenumetext.setEditable(false);
        adresatext.setEditable(false);
        emailtext.setEditable(false);


    }


    public void setNumetext(String nume) {
        numetext.setText(nume);
    }

    public void setPrenumetext(String prenume) {
        prenumetext.setText(prenume);
    }

    public void setAdresatext(String adresa) {
        adresatext.setText(adresa);
    }

    public void setEmailtext(String email) {
        emailtext.setText(email);
    }

    public void closeView() {
        frame.setVisible(false);
    }
}
