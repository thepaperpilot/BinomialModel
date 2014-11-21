import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GUI {
private JTextField probability;
private JTextField trials;
private JPanel panel1;
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
    frame.setMinimumSize(new Dimension(400, 400));
    frame.pack();
    frame.setVisible(true);
}

private GUI() {
    probability.getDocument().addDocumentListener(new DocumentListener() {
        public void changedUpdate(DocumentEvent e) {
            update();
        }

        public void removeUpdate(DocumentEvent e) {
            update();
        }

        public void insertUpdate(DocumentEvent e) {
            update();
        }
    });
    trials.getDocument().addDocumentListener(new DocumentListener() {
        public void changedUpdate(DocumentEvent e) {
            update();
        }
        public void removeUpdate(DocumentEvent e) {
            update();
        }
        public void insertUpdate(DocumentEvent e) {
            update();
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

    /* Did you know the combo bux has a known bug preventing the border from being set? It's a shame, really */
    /* Credit goes to https://stackoverflow.com/questions/776893/remove-border-from-jcombobox for this code */
    for (int i = 0; i < compare.getComponentCount(); i++)
    {
        if (compare.getComponent(i) instanceof JComponent) {
            ((JComponent) compare.getComponent(i)).setBorder(BorderFactory.createLineBorder(new Color(187, 187, 187)));
        }


        if (compare.getComponent(i) instanceof AbstractButton) {
            ((AbstractButton) compare.getComponent(i)).setBorderPainted(false);
        }
    }
}

private void update() {
    try {
        BinomialModel.success_probability = Double.parseDouble(probability.getText());
        BinomialModel.trials = Integer.parseInt(trials.getText());
        standardDeviation.setText(BinomialModel.getStandardDeviation());
        numResults.setText(BinomialModel.getNumberResults());
        sOut.setText(BinomialModel.getSNumber(compare.getSelectedIndex(), (Integer) sNum.getValue()));
        probDist.setText(BinomialModel.getProbabilityDistribution());
        probDistPane.getVerticalScrollBar().setValue(0);
    } catch (NumberFormatException ignored) {
    }
}
}
