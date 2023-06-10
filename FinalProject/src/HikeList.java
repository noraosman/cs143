import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Constructs a linked list of HikeNode objects. Creates HikeNodes to put into the list.
 * Adds HikeNodes to the list.
 */
public class HikeList {
    private HikeNode front;
    private final double userLatitude;
    private final double userLongitude;

    /**
     * Constructor that creates a Linked List of Hike Node objects. Records user's
     * latitude and longitude.
     * @param hikes List of Arrays of Strings containing information to construct HikeNodes.
     * @param userLatitude User's latitude.
     * @param userLongitude User's longitude.
     */
    public HikeList(List<String[]> hikes, double userLatitude, double userLongitude) {
        this.userLatitude = userLatitude;
        this.userLongitude = userLongitude;

        if (hikes.isEmpty()) {
            throw new IllegalArgumentException();
        }
        for (String[] hikeArray : hikes) {
            add(hikeArray);
        }
    }

    /**
     * Adds Adds HikeNode objects to the Linked List.
     * @param hikeInfo String array containing information about a hike.
     */
    public void add(String[] hikeInfo) {
        HikeNode node = createNode(hikeInfo);
        if (this.front == null) {
            this.front = node;
        } else {
            add(this.front, node);
        }
    }

    /**
     * Recursive helper method that adds HikeNode objects to the Linked List
     * in order of their difficulty data field.
     * @param node Initially the front of the linked list, utilized to traverse the list.
     * @param addNode Node to be added to the linked list.
     */
    public void add(HikeNode node, HikeNode addNode) {
        if (addNode.getData().difficulty() >= node.getData().difficulty()) {
            if (node.next == null) {
                node.next = addNode;
            } else {
                if (node.next.getData().difficulty() > addNode.getData().difficulty()) {
                    addNode.next = node.next;
                    node.next = addNode;
                } else {
                    add(node.next, addNode);
                }
            }
        } else {
            addNode.next = this.front;
            this.front = addNode;
        }
    }

    /**
     * Creates a HikeNode out of the information in the passed String Array.
     * @param hikeInfo String Array containing information about a hike.
     * @return New Hike Node.
     */
    public HikeNode createNode(String[] hikeInfo) {
        String name = hikeInfo[0];
        double distance = Double.parseDouble(hikeInfo[1]);
        double elevationGain = Double.parseDouble(hikeInfo[2]);
        double latitude = Double.parseDouble(hikeInfo[3]);
        double longitude = Double.parseDouble(hikeInfo[4]);
        double distanceFromUser = distance(latitude, longitude, this.userLatitude, this.userLongitude);

        return new HikeNode(name, distance, elevationGain, latitude, longitude, distanceFromUser);
    }

    /**
     * Returns the length of the Linked List.
     * @return Length of the Linked List.
     */
    private int getLength() {
        HikeNode current = front;
        int count = 0;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    /**
     * Returns a list containing the first half of the Linked List.
     * @return List containing the first half of the Linked List.
     */
    public List<HikeNode> getEasy() {
        List<HikeNode> list = new ArrayList<>();
        int count = getLength() / 2;
        HikeNode current = this.front;

        for (int i = 0; i < count; i++) {
            list.add(current);
            current = current.next;
        }

        Collections.shuffle(list);
        return list.subList(0, 5);
    }

    /**
     * Returns a list containing the latter half of the Linked List.
     * @return List of the latter half of the Linked List.
     */
    public List<HikeNode> getDifficult() {
        List<HikeNode> list = new ArrayList<>();
        int head = getLength() / 2;
        int tail = 0;

        HikeNode current = front;

        while (current != null) {
            if (tail > head) {
                list.add(current);
            }
            tail++;
            current = current.next;
        }
        Collections.shuffle(list);
        return list.subList(0, 5);
    }

    /**
     * Determines the distance between two sets of latitude and longitude.
     * @param latitude1 Latitude of first pair.
     * @param longitude1 Longitude of first pair.
     * @param latitude2 Latitude of second pair.
     * @param longitude2 Longitude of second pair.
     * @return Distance in miles between the two pairs of passed sets of latitude and longitude.
     */
    public double distance(double latitude1, double longitude1, double latitude2, double longitude2) {

        double dLat = Math.toRadians(latitude2 - latitude1);
        double dLon = Math.toRadians(longitude2 - longitude1);

        latitude1 = Math.toRadians(latitude1);
        latitude2 = Math.toRadians(latitude2);

        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(latitude1) *
                        Math.cos(latitude2);
        double rad = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));
        return (rad * c) / 1.609;
    }
}
