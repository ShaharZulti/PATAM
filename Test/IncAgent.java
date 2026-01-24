package test;

import java.util.ArrayList;
import test.TopicManagerSingleton.TopicManager;

public class IncAgent implements Agent {
    private final String agentName = "IncAgent";
    private Topic topicX = null;
    private Topic topicOut = null;

    public IncAgent(String[] subs, String[] pubs) {
        TopicManager tm=TopicManagerSingleton.get();

        if (subs.length > 0) {
            this.topicX = tm.getTopic(subs[0]);
            this.topicX.subscribe(this);
        }
        else {
            this.topicX = null;
        }
        if (pubs.length > 0) {
            this.topicOut = tm.getTopic(pubs[0]);
            this.topicOut.addPublisher(this);
        } else {
            this.topicOut = null;
        }
    }
    @Override
    public String getName() {
        return this.agentName;
    }
    @Override
    public void reset()
    {
    }
    @Override
    public void callback(String topic, Message msg)
    {
        if (this.topicOut != null && msg.asDouble != Double.NaN && this.topicOut != null) {
            // System.out.println("IncAgent received message on topic " + topic + ": " + msg.asDouble);
            double result = msg.asDouble + 1;
            Message outMsg = new Message(result);
            this.topicOut.publish(outMsg);
        }
    }
    @Override
    public void close()
    {
        if (this.topicX != null) {
            this.topicX.unsubscribe(this);
        }
        if (this.topicOut != null) {
            this.topicOut.removePublisher(this);
        }
    }
    
}
