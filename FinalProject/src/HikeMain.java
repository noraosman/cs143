import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HikeMain {
    public static final String EASTERN_WASHINGTON = "EasternHikes.csv";
    public static final String WESTERN_WASHINGTON = "WesternHikes.csv";

    //Way overworked main that is in desperate need of consolidation and encapsulation.
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        Scanner in;
        List<String[]> hikes = new ArrayList<>();

        //Inquires where the user would like to hike.
        System.out.println("Would you like hikes in Eastern or Western Washington? ");
        String area = input.nextLine().toLowerCase();

        //Soft validation of whether the user entered east or west.
        if (area.charAt(0) == 'e') {
            in = new Scanner(new File(EASTERN_WASHINGTON));
            createList(in, hikes);
        } else if (area.charAt(0) == 'w') {
            in = new Scanner(new File(WESTERN_WASHINGTON));
            createList(in, hikes);
        } else {
            throw new IllegalArgumentException("Incorrect area argument. Expecting string starting with E or W");
        }

        //Gets the user's latitude.
        System.out.print("Enter your latitude: ");
        String latitude = input.nextLine();
        double userLatitude = parseDouble(latitude);

        //Gets the user's longitude
        System.out.print("Enter your longitude: ");
        String longitude = input.nextLine();
        double userLongitude = parseDouble(longitude);

        HikeList list = new HikeList(hikes, userLatitude, userLongitude);

        //Gets the user's desired hike difficulty.
        System.out.print("Would you like easy or difficult hikes? ");
        String difficulty = input.nextLine().toLowerCase();

        //Builds a suggestion list based on the user's desired difficulty. Errors if soft validation fails.
        SuggestionList suggestionList;
        String difficultyCheck;
        if(difficulty.charAt(0) == 'e'){
            difficultyCheck = "e";
            suggestionList = new SuggestionList(list.getEasy(), userLatitude, userLongitude);

        }
        else if (difficulty.charAt(0) == 'd'){
            difficultyCheck = "d";
            suggestionList = new SuggestionList(list.getDifficult(), userLatitude, userLongitude);
        } else {
            throw new IllegalArgumentException("Invalid difficulty argument. " +
                    "Expecting string starting with E or D");
        }

        //Places the hikes from the suggestion list into an array for more accurate data access.
        HikeNode[] finalHikeList = new HikeNode[5];
        int count = 0;
        for (double key : suggestionList.getMap().keySet()) {
            finalHikeList[count] = suggestionList.getMap().get(key);
            count++;
        }

        boolean proceed = true;

        //Provides a menu of the suggested hikes.
        System.out.println("Suggested hikes nearest to you:\n" +
                "  1. " + finalHikeList[0].getData().name() + "\n" +
                "  2. " + finalHikeList[1].getData().name() + "\n" +
                "  3. " + finalHikeList[2].getData().name() + "\n" +
                "  4. " + finalHikeList[3].getData().name() + "\n" +
                "  5. " + finalHikeList[4].getData().name());
        System.out.println();


        //Repeatedly allows the user to access more information about each hike until they choose to exit.
        do {
            System.out.println();
            System.out.print("Enter a hike's number to get more information, or enter 'Q' to quit: ");
            String hikeChoice = input.nextLine();

            if (hikeChoice.equals("1")) {
                finalHikeList[0].printHikeDescription(difficultyCheck);
            } else if (hikeChoice.equals("2")) {
                finalHikeList[1].printHikeDescription(difficultyCheck);
            } else if (hikeChoice.equals("3")) {
                finalHikeList[2].printHikeDescription(difficultyCheck);
            } else if (hikeChoice.equals("4")) {
                finalHikeList[3].printHikeDescription(difficultyCheck);
            } else if (hikeChoice.equals("5")) {
                finalHikeList[4].printHikeDescription(difficultyCheck);
            } else {
                proceed = false;
            }

        } while (proceed);

        System.out.println();
        System.out.println("Thanks, and enjoy your hike!");
    }

    //Creates a list of arrays that contain the values of each hike.
    public static void createList(Scanner in, List<String[]> list) {
        while (in.hasNextLine()) {
            String[] hike = in.nextLine().split(",");
            list.add(hike);
        }
    }

    //Determines if provided string is a valid, workable double.
    public static double parseDouble(String string) {
        try {
            return Double.parseDouble(string);

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid argument. Requires a number");
        }
    }


}
