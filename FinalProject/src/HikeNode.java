//Node for our linked list that contains information about one hike.
public class HikeNode {
    private final HikeNodeData data;
    public HikeNode next;
    //Constructor that sets up the record of data.
    public HikeNode(String name, double distanceInMiles, double elevationGain,
                    double latitude, double longitude, double distanceFromUser) {
        this.data = new HikeNodeData(name, distanceInMiles, elevationGain,
                latitude, longitude, elevationGain / distanceInMiles / 2, distanceFromUser);
    }

    //Getter to receive data information from the record.
    public HikeNodeData getData() {
        return this.data;
    }

    //A record to help hold and consolidate information about the hike, making the code less messy.
    record HikeNodeData(String name, double distanceInMiles, double elevationGain,
                        double latitude, double longitude, double difficulty, double distanceFromUser) {
    }

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
}