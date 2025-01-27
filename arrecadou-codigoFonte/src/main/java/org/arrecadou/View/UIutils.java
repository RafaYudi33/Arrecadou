package org.arrecadou.View;

import javax.swing.*;
import java.awt.*;


public class UIutils {
    public static void styleButton(JButton button) {
        button.putClientProperty("JButton.arc", 15);
        button.setPreferredSize(new Dimension(150, 30));
        button.setFont(new Font("SansSerif", Font.BOLD, 13));
        button.setBackground(new Color(0x042C41));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
    }

}
