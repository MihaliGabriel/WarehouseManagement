package Presentation;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class EditProductView{
    private JLabel listaproduse;
    DefaultListModel listModel;
    private JList jcomp2;
    private JButton detaliibutton;
    private JButton jcomp4;
    private JFrame frame;
    private JButton deletebutton;

    public EditProductView() {
        listModel = new DefaultListModel();
        frame = new JFrame("Edit");
        listaproduse = new JLabel ("Lista produse");
        jcomp2 = new JList (listModel);
        detaliibutton = new JButton ("Vizualizeaza detalii");
        jcomp4 = new JButton ("Editeaza produs");
        deletebutton = new JButton("Sterge produs");

        frame.setSize(new Dimension(682, 482));
        frame.setLayout(null);
        frame.setVisible(true);

        frame.add(listaproduse);
        frame.add(jcomp2);
        frame.add(detaliibutton);
        frame.add(jcomp4);
        frame.add(deletebutton);

        listaproduse.setBounds (115, 65, 400, 25);
        jcomp2.setBounds (55, 90, 210, 335);
        detaliibutton.setBounds (390, 160, 150, 40);
        jcomp4.setBounds (390, 270, 150, 40);
        deletebutton.setBounds(390, 360, 150, 40);

    }
    public void addListenerDeleteProduct(ActionListener c) {
        deletebutton.addActionListener(c);
    }

    public void addListenerEditProduct(ActionListener b) {
        jcomp4.addActionListener(b);
    }
    public void addList(String x) {
        listModel.addElement(x);
    }

    public void addListenerDetailsProduct(ActionListener a) {
        detaliibutton.addActionListener(a);
    }
    public void closeView() {
        frame.setVisible(false);
    }

    public String getItem() {
        return (String)jcomp2.getSelectedValue();
    }
}
