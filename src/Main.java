import javax.swing.*;

import data.InfrastructureDesign;
import data.OptimalInfrastructureDesignGenerator;

public class Main {
    public static void main(String[] args) throws Exception {

        // test code voor backtracking
        /*OptimalInfrastructureDesignGenerator dg = new OptimalInfrastructureDesignGenerator();
        System.out.println(dg.generateOptimizedDesign(0.9999));
        System.out.println(dg);*/

        try {
            UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) { }

        JFrame mainScreenFrame = new JFrame();
        mainScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainScreenFrame.setResizable(false);
        mainScreenFrame.setTitle("Hoofdscherm");

        MainScreen mainScreen = new MainScreen(mainScreenFrame);
        mainScreenFrame.add(mainScreen);
        mainScreenFrame.pack();

        mainScreenFrame.setLocationRelativeTo(null);
        mainScreenFrame.setVisible(true);
    }
}