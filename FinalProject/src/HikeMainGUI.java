import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Nora Osmanova, Kyle Williams
 * @date 06/09/2023
 * @class CS143
 *
 * Guides user through supplying information to create a suggested list of hikes for them.
 * Creates a linked list of hikes from a file. Grabs either the first half or latter half
 * of the linked list and puts it into a TreeMap organized by distance from user.
 * Takes and shuffles the closest 5 hikes and presents them to the user.
 */
public class HikeMainGUI {
    public static final String EASTERN_WASHINGTON = "EasternHikes.csv";
    public static final String WESTERN_WASHINGTON = "WesternHikes.csv";

    /**
     * Constructor that creates initial gui panel.
     * @param args ""
     */
    public static void main(String[] args) {
        HikeGui GUI = new HikeGui();
        GUI.showGui();
    }

    /**
     * Creates and returns a finalized list of 5 nodes that are either easy or difficult,
     * closest of the available hikes to your location, and randomized in order.
     * @param region Chosen region, Eastern or Western.
     * @param difficulty Chosen difficulty, Easy or Difficult.
     * @param latitude User's latitude.
     * @param longitude User's longitude.
     * @return Finalized list of hikes.
     * @throws FileNotFoundException If hike file is not found.
     */
    public static HikeNode[] createList(String region, String difficulty, double latitude, double longitude) throws FileNotFoundException {
        Scanner in;
        SuggestionList suggestionList;
        List<String[]> hikes = new ArrayList<>();

        if (region.equals("Eastern")) {
            in = new Scanner(new File(EASTERN_WASHINGTON));
        } else {
            in = new Scanner(new File(WESTERN_WASHINGTON));
        }

        writeFile(in, hikes);

        HikeList list = new HikeList(hikes, latitude, longitude);
        System.out.println(list);

        if (difficulty.equals("Easy")) {
            suggestionList = new SuggestionList(list.getEasy(), latitude, longitude);
        } else {
            suggestionList = new SuggestionList(list.getDifficult(), latitude, longitude);
        }

        HikeNode[] finalHikeList = new HikeNode[5];
        int count = 0;
        for (double key : suggestionList.getMap().keySet()) {
            finalHikeList[count] = suggestionList.getMap().get(key);
            count++;
        }

        return finalHikeList;
    }


    /**
     * Writes the information from the hike file into a list of Arrays of Strings.
     * @param in Scanner of hike file.
     * @param list List of Arrays of Strings.
     */
    public static void writeFile(Scanner in, List<String[]> list) {
        while (in.hasNextLine()) {
            String[] hike = in.nextLine().split(",");
            list.add(hike);
        }
    }
}
