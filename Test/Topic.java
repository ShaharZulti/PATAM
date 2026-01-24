package test;

import java.util.ArrayList;
import java.util.List;

public class Topic {
    public final String name;
    private List<Agent> subs = new ArrayList<Agent>();
    private List<Agent> pubs = new ArrayList<Agent>();

    Topic(String name){
        this.name=name;
    }

    public List<Agent> getSubscribers() {
        return this.subs;
    }
    public List<Agent> getPublishers() {
        return this.pubs;
    }
    
    public void subscribe(Agent a){
        if (!this.subs.contains(a)) {
            this.subs.add(a);
        }
    }

    public void unsubscribe(Agent a){
        this.subs.remove(a);
    }

    public void publish(Message m){
        // System.out.println("Publishing message on topic " + this.name + ": " + m.asDouble);
        for (Agent sub : this.subs) {
            sub.callback(this.name, m);
        }
    }

    public void addPublisher(Agent a){
        if (!this.pubs.contains(a)){
            this.pubs.add(a);
        }
    }

    public void removePublisher(Agent a){
        this.pubs.remove(a);
    }
}
