import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GUI {
private JTextField probability;
private JTextField trials;
private JPanel panel1;
private JButton updateButton;
private JTextField standardDeviation;
private JTextField numResults;
private JTextPane probDist;
private JScrollPane probDistPane;
private JComboBox compare;
private JSpinner sNum;
private JTextField sOut;

public static void main(String[] args) {
    JFrame frame = new JFrame("GUI");
    frame.setContentPane(new GUI().panel1);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
}

private GUI() {
    updateButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            BinomialModel.success_probability = Double.parseDouble(probability.getText());
            BinomialModel.trials = Integer.parseInt(trials.getText());
            standardDeviation.setText(BinomialModel.getStandardDeviation());
            numResults.setText(BinomialModel.getNumberResults());
            sOut.setText(BinomialModel.getSNumber(compare.getSelectedIndex(), (Integer) sNum.getValue()));
            probDist.setText(BinomialModel.getProbabilityDistribution());
            probDistPane.getVerticalScrollBar().setValue(0);
        }
    });
    compare.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            sOut.setText(BinomialModel.getSNumber(compare.getSelectedIndex(), (Integer) sNum.getValue()));
        }
    });
    sNum.addChangeListener(new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent changeEvent) {
            sOut.setText(BinomialModel.getSNumber(compare.getSelectedIndex(), (Integer) sNum.getValue()));
        }
    });

    probability.setBorder(BorderFactory.createLineBorder(new Color(187, 187, 187)));
    trials.setBorder(BorderFactory.createLineBorder(new Color(187, 187, 187)));
    standardDeviation.setBorder(BorderFactory.createEmptyBorder());
    numResults.setBorder(BorderFactory.createEmptyBorder());
    sOut.setBorder(BorderFactory.createEmptyBorder());
}
}
