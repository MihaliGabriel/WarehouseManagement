package Presentation;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class EditCustomerView{
    private JLabel listaclienti;
    DefaultListModel listModel;
    private JList jcomp2;
    private JButton detaliibutton;
    private JButton jcomp4;
    private JFrame frame;
    private JButton deletebutton;

    public EditCustomerView() {
        listModel = new DefaultListModel();
        frame = new JFrame("Edit");
        listaclienti = new JLabel ("Lista clienti");
        jcomp2 = new JList (listModel);
        detaliibutton = new JButton ("Vizualizeaza detalii");
        jcomp4 = new JButton ("Editeaza client");
        deletebutton = new JButton("Sterge client");

        frame.setSize(new Dimension(682, 482));
        frame.setLayout(null);
        frame.setVisible(true);

        frame.add(listaclienti);
        frame.add(jcomp2);
        frame.add(detaliibutton);
        frame.add(jcomp4);
        frame.add(deletebutton);

        listaclienti.setBounds (115, 65, 400, 25);
        jcomp2.setBounds (55, 90, 210, 335);
        detaliibutton.setBounds (390, 160, 150, 40);
        jcomp4.setBounds (390, 270, 150, 40);
        deletebutton.setBounds(390, 360, 150, 40);

    }
    public void addListenerDeleteCustomer(ActionListener c) {
        deletebutton.addActionListener(c);
    }
    public void addListenerEditCustomer(ActionListener b) {
        jcomp4.addActionListener(b);
    }
    public void addListenerDetailsCustomer(ActionListener a) {
        detaliibutton.addActionListener(a);
    }

    public void addList(String x) {
        listModel.addElement(x);
    }

    public String getItem() {
        return (String)jcomp2.getSelectedValue();
    }
    public void closeView() {
        frame.setVisible(false);
    }
}
