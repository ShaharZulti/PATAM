package test;

import java.util.ArrayList;

public class Graph extends ArrayList<Node>{
    
    public boolean hasCycles() {
        for (Node node : this) {
            if (node.hasCycles()) {
                return true;
            }
        }
        return false;
    }
    private Node findOrCreateNode(String name) {
        // Search for existing node
        for (Node node : this) {
            if (node.getName().equals(name)) {
                return node;  // Found existing node
            }
        }
        
        // Not found, create new node and add it
        Node newNode = new Node(name);
        this.add(newNode);
        return newNode;
    }

    public void createFromTopics() {
        for (Topic t : TopicManagerSingleton.get().getTopics()) {
            Node n = new Node("T" + t.name);
            this.add(n);

            for (Agent a : t.getSubscribers()) {
                Node an = findOrCreateNode("A" + a.getName());
                n.addEdge(an);
            }
            for (Agent a : t.getPublishers()) {
                Node an = findOrCreateNode("A" + a.getName());
                an.addEdge(n);
            }
        }
    }    

    
}
