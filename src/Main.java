import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame hoofdscherm = new JFrame();
        hoofdscherm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hoofdscherm.setResizable(false);
        hoofdscherm.setTitle("Hoofdscherm");

        Panel panel = new Panel(hoofdscherm);
        hoofdscherm.add(panel);
        hoofdscherm.pack();

        hoofdscherm.setLocationRelativeTo(null);
        hoofdscherm.setVisible(true);
    }

}