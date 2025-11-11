package util;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ButtonEditor extends DefaultCellEditor {
    private JButton button;
    private boolean clicked;
    private int row;
    private JTable table;
    private ButtonHandler handler;

    public interface ButtonHandler {
        void handle(int row); // you can add extra params if needed
    }

    public ButtonEditor(JCheckBox checkBox, String text, ButtonHandler handler) {
        super(checkBox);
        this.handler = handler;
        button = new JButton(text);
        button.setOpaque(true);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped(); // stops editing
                handler.handle(row);
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.row = row;
        this.table = table;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return "Button";
    }

    @Override
    public boolean stopCellEditing() {
        clicked = false;
        return super.stopCellEditing();
    }

    @Override
    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}
