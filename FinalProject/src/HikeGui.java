import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;


/**
 * GUI class creates panel with options to choose
 * settings for hike suggestions.
 * Final list shows up on new panel as a list
 * Hikes can be viewed for more info by clicking on them
 * More info pops up new window with hike info
 */
public class HikeGui extends HikeMainGUI{

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
    private JList listOfHikes;
    private HikeNode[] finalList;
    private String difficultyField;



        public HikeGui() {
            /**
             * Detects if the button is clicked after preferences for
             * hikes are selected
             */
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
                    difficultyField = "e";
                } else {
                    difficulty = "Difficult";
                    difficultyField = "d";
                }


                double latitude = Double.parseDouble(latitudeText.getText());
                double longitude = Double.parseDouble(longitudeText.getText());

                // creates list of hikes for user
                try {
                    finalList = HikeMainGUI.createList(region, difficulty, latitude, longitude);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                // fills in JList with chosen hikes
                fillJList();

                /**
                 * Shows the pop up menu when more info button is clicked
                 */
                moreInfoButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        showMenu();

                    }
                });



            }
        });
    }

    /**
     * Initialized root panel with main frame to show preferences
     * for hikes
     */

    public void showGui()  {
        JFrame frame = new JFrame("Take A Hike!");
        frame.setContentPane(new HikeGui().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(800,600);


    }


    /**
     * adds list of hikes to final array as
     * string to be displayed in Jlist
     */
    private void fillJList() {
        String[] arrOfStrings = new String[5];
        for (int i = 0; i < arrOfStrings.length; i++) {
            arrOfStrings[i] = finalList[i].getData().name();
        }
        listOfHikes.setListData(arrOfStrings);
    }


    /**
     * Initializes new frame as pop up window to display
     * more information about selected hike
     */
    public void showMenu() {

         JFrame frame = new JFrame("PopUp");
            frame.setSize(400, 400);
            JPanel panel = new JPanel();
            JTextArea textArea = new JTextArea();
            textArea.setPreferredSize(new Dimension(400, 400));
            String text = finalList[listOfHikes.getSelectedIndex()].hikeDescription(difficultyField);
            textArea.setText(text);
            textArea.setLineWrap(true);
            panel.add(textArea);
            Popup popup = new Popup();
            moreInfoButton.addActionListener(popup);
            frame.add(panel);
            frame.setVisible(true);



    }




}
