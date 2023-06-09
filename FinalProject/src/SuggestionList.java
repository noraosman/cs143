import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Contains a list of 5 HikeNodes that closest match the average of ExampleList's difficulty from HikeTree.
 * List is then ordered by distance of the hike from the user's location.
 */

// create a suggestion list and give 5 hikes
// Sort the list in ascending order based on distance from the user
// Add hikes to sorted set


public class SuggestionList {

    private final Map<Double, HikeNode> map;
    private final double userLatitude;
    private final double userLongitude;


    /**
     * Constructor to initialize map with hike information
     * Distance from user is key, hikes are values
     *
     * @param selectedHikes list of suggested hikes to the user
     * @param userLatitude  user's latitude to calculate distance from
     * @param userLongitude user's longitude to calculate distance from
     */
    public SuggestionList(List<HikeNode> selectedHikes, double userLatitude, double userLongitude) {

        if (selectedHikes.isEmpty()) {
            throw new IllegalArgumentException();
        }


        this.map = new TreeMap<>();

        for (HikeNode hike : selectedHikes) {
            map.put(hike.getData().distanceFromUser(), hike);
        }

        this.userLatitude = userLatitude;
        this.userLongitude = userLongitude;
    }

    //Provides the user's latitude.
    public double getUserLatitude() {
        return userLatitude;
    }

    //Provides the user's longitude.
    public double getUserLongitude() {
        return userLongitude;
    }

    /**
     * getter method for the map
     *
     * @return map that can not be modified
     */
    public Map<Double, HikeNode> getMap() {
        return Collections.unmodifiableMap(this.map);
    }
}
