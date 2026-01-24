package test;

import java.util.ArrayList;
import test.TopicManagerSingleton.TopicManager;

public class PlusAgent implements Agent {
    private final String agentName = "PlusAgent";
    private Topic topicX = null;
    private Topic topicY = null;
    private Topic topicOut = null;
    private Message x = null;
    private Message y = null;

    public PlusAgent(String[] subs, String[] pubs) {
        TopicManager tm=TopicManagerSingleton.get();

        if (subs.length > 0) {
            this.topicX = tm.getTopic(subs[0]);
            this.topicX.subscribe(this);
        } else {
            this.topicX = null;
        }

        if (subs.length > 1) {
            this.topicY = tm.getTopic(subs[1]);
            this.topicY.subscribe(this);
        } else {
            this.topicY = null;
        }
        if (pubs.length > 0) {
            this.topicOut = tm.getTopic(pubs[0]);
            this.topicOut.addPublisher(this);
        } else {
            this.topicOut = null;
        }

        this.reset();
    }
    
    @Override
    public String getName() {
        return this.agentName;
    }
    @Override
    public void reset()
    {
        this.x = new Message(0.0);
        this.y = new Message(0.0);
    }
    @Override
    public void callback(String topic, Message msg)
    {
        if (this.topicX == null || this.topicOut == null || this.topicOut == null)
            return;

        if (topic.equals(this.topicX.name)) {
            this.x = msg;
        } else if (topic.equals(this.topicY.name)) {
            this.y = msg;
        }
        
        if (this.x.asDouble != Double.NaN &&
            this.y.asDouble != Double.NaN) {

            double result = this.x.asDouble + this.y.asDouble;
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
        if (this.topicY != null) {
            this.topicY.unsubscribe(this);
        }
        if (this.topicOut != null) {
            this.topicOut.removePublisher(this);
        }
    }
    
}
