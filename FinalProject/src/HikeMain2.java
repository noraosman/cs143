import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HikeMain2 {
    public static final String EASTERN_WASHINGTON = "EasternHikes.csv";
    public static final String WESTERN_WASHINGTON = "WesternHikes.csv";


    public static void main(String[] args) throws Exception{
        HikeGui GUI = new HikeGui();
        GUI.showGui();

    }

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



    public static void writeFile(Scanner in, List<String[]> list) {
        while (in.hasNextLine()) {
            String[] hike = in.nextLine().split(",");
            list.add(hike);
        }
    }




}
