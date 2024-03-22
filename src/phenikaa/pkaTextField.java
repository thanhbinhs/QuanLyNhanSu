package phenikaa;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import javax.swing.JFormattedTextField;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import phenikaa.person.*;

public final class pkaTextField {

    private pkaTextField() {
    }

    public static JTextField getTextField(int x, int y, int w, int h) {
        JTextField field = new JTextField();
        field.setBounds(x, y, w, h);
        field.setBackground(new Color(0xF5E8dd));
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setForeground(Color.BLACK);
        return field;
    }

    public static JFormattedTextField getNumberField(int x, int y, int w, int h) {
        JFormattedTextField field = new JFormattedTextField(creNumberFormat());
        field.setBounds(x, y, w, h);
        field.setBackground(new Color(0xF5E8dd));
        field.setForeground(Color.BLACK);
        field.setValue(0);
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DELETE || e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    field.setValue(0);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

            @Override
            public void keyTyped(KeyEvent e) {
                try {
                    field.commitEdit();
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
            }
        });
        return field;
    }

    private static NumberFormatter creNumberFormat() {
        NumberFormatter numberFormat = new NumberFormatter();
        numberFormat.setValueClass(Double.class);
        numberFormat.setMinimum(0.0);
        numberFormat.setMaximum(Double.MAX_VALUE);
        numberFormat.setAllowsInvalid(false);
        numberFormat.setOverwriteMode(true);
        return numberFormat;
    }

    public static JSpinner creDateChooser(int x, int y, int w, int h) {
        SpinnerDateModel model = new SpinnerDateModel();
        JSpinner dateSpinner = new JSpinner(model);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        JFormattedTextField textField = editor.getTextField();
        textField.setBackground(new Color(0xF5E8dd));
        textField.setForeground(Color.BLACK);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        dateSpinner.setBounds(x, y, w, h);
        dateSpinner.setEditor(editor);
        return dateSpinner;
    }

    public static JFormattedTextField getPhoneField(int x, int y, int w, int h) {
        JFormattedTextField phone = new JFormattedTextField(creMask());
        phone.setBackground(new Color(0xF5E8dd));
        phone.setBounds(x, y, w, h);
        phone.setForeground(Color.BLACK);
        phone.setFont(new Font("Arial", Font.PLAIN, 14));
        phone.addKeyListener(new KeyListener() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DELETE || e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    phone.setText("");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

        });
        return phone;
    }

    public static MaskFormatter creMask() {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter("##########");
            formatter.setValidCharacters("0123456789");
            formatter.setAllowsInvalid(false);
            formatter.setOverwriteMode(true);
            formatter.setPlaceholder("0123456789");
        } catch (ParseException e) {
        }
        return formatter;
    }

    public static JList<Employee> creListField(int x, int y, int w, int h) {
        JList<Employee> items = new JList<>();
        items.setBounds(x, y, w, h);
        items.setBackground(new Color(0xF5E8dd));
        items.setSelectionBackground(new Color(0xf5e8dd));
        items.setForeground(Color.BLACK);
        items.setFont(new Font("Arial", Font.PLAIN, 14));
        items.setLayoutOrientation(JList.VERTICAL);
        items.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return items;
    }
}
