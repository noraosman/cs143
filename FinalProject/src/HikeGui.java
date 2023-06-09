import javax.swing.*;

public class HikeGui {
    private JPanel rootPanel;
    private JRadioButton eastRadioButton;
    private JRadioButton westRadioButton;
    private JRadioButton easyRadioButton;
    private JRadioButton difficultRadioButton;
    private JTextField textField1;
    private JTextField textField2;


    public HikeGui() {
    }


    public void showGui(){
        JFrame frame = new JFrame("Hiking Suggestions");
        frame.setContentPane(new HikeGui().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(600,600);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here

    }
}
