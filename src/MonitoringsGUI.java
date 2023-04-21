import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MonitoringsGUI extends JDialog implements ActionListener {
    public MonitoringsGUI(JFrame frame, boolean modal){
        super(frame, modal);
        setTitle("Monitoring");
        setLayout(null);
        setSize(new Dimension(786,576));
        setBackground(new Color(35, 35, 47));
        this.getContentPane().setBackground(new Color(35, 35, 47));

        String[] columnNames = {"Icon", "Hostnaam", "Beschikbaarheid", "Uptime", "Processorbelasting", "Beschikbare opslag"};
        Object[][] data = {
                {"Paul", "mcmillis", "Project manager", "5", "fale", "oke"},
                {"Paul", "mcmillis", "Project manager", "5", "fale", "ple"}};
        JTable table = new JTable(data, columnNames);
        table.getTableHeader().setBounds(50,220,700,20);
        table.setBounds(50,240,700,200);
        table.setBackground(new Color(35,35,80));
        table.getTableHeader().setBackground(new Color(35,35,80));
        table.setForeground(Color.white);
        table.getTableHeader().setForeground(Color.white);
        this.add(table.getTableHeader());
        this.add(table);

        // Het dialoog zichtbaar maken.
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
