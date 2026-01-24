package test;

import java.util.ArrayList;
import java.util.List;


public class Node {
    private final String name;
    private final List<Node> edges;
    private Message msg;

    public Node(String name) {
        this.name = name;
        this.edges = new ArrayList<>();
        this.msg = null;
    }

    public String getName() {
        return name;
    }
    public List<Node> getEdges() {
        return edges;
    }
    public Message getMsg() {
        return msg;
    }
    public void setMsg(Message msg) {
        this.msg = msg;
    }
    public void addEdge(Node node) {
        edges.add(node);
    }
    public void removeEdge(Node node) {
        edges.remove(node);
    }
    public boolean hasCycles() {
        return hasCyclesHelper(new ArrayList<>());
    }
    private boolean hasCyclesHelper(List<Node> visited) {
        if (visited.contains(this)) {
            return true;
        }
        visited.add(this);
        for (Node neighbor : edges) {
            if (neighbor.hasCyclesHelper(visited)) {
                return true;
            }
        }
        visited.remove(this);
        return false;
    }
}
