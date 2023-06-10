import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
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
    private JPanel listPanel;
    private JButton moreInfoButton;
    private JList list1;
    private HikeNode[] finalList;
    private JPopupMenu popupMenu;
    public HikeGui() {
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String region;
                if (easternRadioButton.isSelected()) {
                    region = "Eastern";
                } else {
                    region = "Western";
                }

                String difficulty;
                if (easyRadioButton.isSelected()) {
                    difficulty = "Easy";
                } else {
                    difficulty = "Difficult";
                }

                double latitude = Double.parseDouble(latitudeText.getText());
                double longitude = Double.parseDouble(longitudeText.getText());

                try {
                    finalList = createList(region, difficulty, latitude, longitude);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                fillJList();




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

    private void fillJList() {
        String[] arrOfStrings = new String[5];
        for (int i = 0; i < arrOfStrings.length; i++) {
            arrOfStrings[i] = finalList[i].getData().name();
        }
        list1.setListData(arrOfStrings);
    }

}
