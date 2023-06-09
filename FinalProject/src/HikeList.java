import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Class holding the pointer to the front of a linked list.
public class HikeList {
    //Front of linked list
    private HikeNode front;
    private final double userLatitude;
    private final double userLongitude;

    //Constructor
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

    //Method to add a HikeNode to the linked list.
    public void add(String[] hikeInfo) {
        HikeNode node = createNode(hikeInfo);
        if (this.front == null) {
            this.front = node;
        } else {
            add(this.front, node);
        }
    }

    //Helper method to recursively add HikeNodes to the linked list in order of difficulty.
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

    //creates a node out of information stored in an array.
    public HikeNode createNode(String[] hikeInfo) {
        String name = hikeInfo[0];
        double distance = Double.parseDouble(hikeInfo[1]);
        double elevationGain = Double.parseDouble(hikeInfo[2]);
        double latitude = Double.parseDouble(hikeInfo[3]);
        double longitude = Double.parseDouble(hikeInfo[4]);
        double distanceFromUser = distance(latitude, longitude, this.userLatitude, this.userLongitude);

        return new HikeNode(name, distance, elevationGain, latitude, longitude, distanceFromUser);
    }

    //Provides the length of the linked list.
    private int getLength() {
        HikeNode current = front;
        int count = 0;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    //Provides the first half of the linked list (the easiest hikes)
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

    //Provides the second half of the linked list (the hardest hikes)
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
