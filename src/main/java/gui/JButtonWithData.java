package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Function;

@FunctionalInterface
interface Temp {
    void performAction(int y, int x);
}


public class JButtonWithData extends JButton {
    public int y, x;

    public JButtonWithData(int y, int x, Temp temp) {
        this.y = y;
        this.x = x;
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                temp.performAction(y, x);
            }
        });
    }
}
