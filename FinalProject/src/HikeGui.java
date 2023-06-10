import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HikeGui extends HikeMain2{

    private JButton continueButton;
    private JPanel rootPanel;
    private JLabel cardinalDirectionLabel;
    private JLabel latitudeLabel;
    private JLabel longitudeLabel;
    private JLabel difficultyLabel;
    private JRadioButton easternRadioButton;
    private JRadioButton westernRadioButton;
    private JRadioButton easyRadioButton;
    private JRadioButton difficultRadioButton;
    private JTextField longitudeText;
    private JTextField latitudeText;
    private JLabel logo;

    public HikeGui() {
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // only use for actions
                System.out.println("hi");

            }
        });
    }

    public void showGui() throws IOException {
        JFrame frame = new JFrame("Take A Hike!");
        frame.setContentPane(new HikeGui().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(800,600);


    }

    private void createUIComponents() throws IOException {
        BufferedImage myPic = ImageIO.read(new File("logo.png"));
        this.logo = new JLabel(new ImageIcon(myPic));

    }
}
