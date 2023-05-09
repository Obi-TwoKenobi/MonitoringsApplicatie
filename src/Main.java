import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame mainScreenFrame = new JFrame();
        mainScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainScreenFrame.setResizable(false);
        mainScreenFrame.setTitle("Hoofdscherm");

        MainScreen mainScreen = new MainScreen(mainScreenFrame);
        mainScreenFrame.add(mainScreen);
        mainScreenFrame.pack();

        mainScreenFrame.setLocationRelativeTo(null);
        mainScreenFrame.setVisible(true);

        System.out.println("tesy");
    }
}