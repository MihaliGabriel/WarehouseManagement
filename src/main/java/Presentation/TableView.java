package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TableView {

    private JTable table;
    private JFrame frame;

    public TableView(Object[] columns, Object[][] data) {

        frame = new JFrame("Tabel");
        table = new JTable(data, columns);
        table.setBounds(30, 40, 600, 300);

        JScrollPane sp = new JScrollPane(table);

        frame.add(sp);
        frame.setSize(600, 500);
        frame.setVisible(true);

    }
    public void closeView() {
        frame.setVisible(false);
    }
}
