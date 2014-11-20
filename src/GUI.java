import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    private JTextField probability;
    private JTextField trials;
    private JTextPane out;
    public JPanel panel1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("GUI");
        frame.setContentPane(new GUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public GUI() {
        probability.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BinomialModel.success_probability = Double.parseDouble(((JTextField) e.getSource()).getText());
                out.setText(BinomialModel.calc());
            }
        });
        trials.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BinomialModel.trials = Integer.parseInt(((JTextField) e.getSource()).getText());
                out.setText(BinomialModel.calc());
            }
        });
    }

}
