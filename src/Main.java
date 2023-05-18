import javax.swing.*;

import data.InfrastructureDesign;
import data.OptimalInfrastructureDesignGenerator;

public class Main {
    public static void main(String[] args) {

        // test code voor backtracking
        OptimalInfrastructureDesignGenerator dg = new OptimalInfrastructureDesignGenerator();
        InfrastructureDesign ids = dg.generateOptimizedDesign(0.99);
        System.out.println(ids);
        System.out.println(ids.calculateAvailabilityPercentage());
        System.out.println(ids.calculateTotalPrice());

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