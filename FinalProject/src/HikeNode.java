/**
 * Class dedicated to the storage of information of individual hikes.
 */
public class HikeNode {
    private final HikeNodeData data;
    public HikeNode next;

    /**
     * Constructor that sets the values of the record to hold data about the hike.
     * @param name Hike name.
     * @param distanceInMiles Hike distance in miles.
     * @param elevationGain Hike elevation gain.
     * @param latitude Hike latitude location.
     * @param longitude Hike longitude location.
     * @param distanceFromUser Hike distance from user.
     */
    public HikeNode(String name, double distanceInMiles, double elevationGain,
                    double latitude, double longitude, double distanceFromUser) {
        this.data = new HikeNodeData(name, distanceInMiles, elevationGain,
                latitude, longitude, elevationGain / distanceInMiles / 2, distanceFromUser);
    }

    /**
     * Getter for the data stored in the HikeNodeData record.
     * @return Returns HikeNodeData field.
     */
    public HikeNodeData getData() {
        return this.data;
    }

    /**
     * Holds data for the hike node.
     * @param name Hike name.
     * @param distanceInMiles Hike distance in miles.
     * @param elevationGain Hike elevation gain.
     * @param latitude Hike latitude location.
     * @param longitude Hike longitude location.
     * @param difficulty Hike difficulty.
     * @param distanceFromUser Hike distance from user.
     */
    record HikeNodeData(String name, double distanceInMiles, double elevationGain,
                        double latitude, double longitude, double difficulty, double distanceFromUser) {
    }

    /**
     * Prints a detailed paragraph about the hike.
     * @param difficulty Difficulty of the hike, either e or d.
     */
    public void printHikeDescription(String difficulty) {
        if (difficulty.equals("e")) {
            difficulty = "n easy";
        } else {
            difficulty = " difficult";
        }
        System.out.println(getData().name + " is a" + difficulty + " hike. " +
                "It is " + getData().distanceInMiles + " miles long and has an elevation gain of " + getData().elevationGain + " feet." +
                "\nThis hike is located at the following coordinates: " + getData().latitude + ", " + getData().longitude + ". " +
                "It is located " + getData().distanceFromUser + " miles from your location.");
    }

    /**
     * Returns a detailed paragraph about the hike.
     * @param difficulty Difficulty of the hike, either "e" or "d".
     * @return Detailed paragraph about the hike.
     */
    public String hikeDescription(String difficulty) {
        if (difficulty.equals("e")) {
            difficulty = "n easy";
        } else {
            difficulty = " difficult";
        }

        return getData().name + " is a" + difficulty + " hike. " +
                "It is " + getData().distanceInMiles + " miles long and has an elevation gain of " + getData().elevationGain + " feet. " +
                "This hike is located at the following coordinates: " + getData().latitude + ", " + getData().longitude + ". " +
                "It is located " + getData().distanceFromUser + " miles from your location.";
    }
}