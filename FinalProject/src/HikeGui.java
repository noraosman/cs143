import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HikeGui {
    private JButton hikeButton;
    private JPanel rootPanel;
    private JLabel header;
    private JTextField location;
    private JLabel eastOrWest;


    public HikeGui() {
        hikeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // only use for actions

            }
        });
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
