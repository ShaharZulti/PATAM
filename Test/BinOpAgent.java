package test;

import java.util.function.BinaryOperator;
import test.TopicManagerSingleton.TopicManager;

public class BinOpAgent implements Agent {
    private final String agentName;
    private final Topic topicIn1;
    private final Topic topicIn2;
    private final Topic topicOut;
    private final BinaryOperator<Double> operation;
    private Message in1 = null;
    private Message in2 = null;

    public BinOpAgent(String agentName, String topicIn1, String topicIn2, String topicOut, BinaryOperator<Double> operation) {
        this.agentName = agentName;
        TopicManager tm=TopicManagerSingleton.get();

        this.topicIn1 = tm.getTopic(topicIn1);
        this.topicIn2 = tm.getTopic(topicIn2);
        this.topicOut = tm.getTopic(topicOut);
        this.operation = operation;

        this.topicIn1.subscribe(this);
        this.topicIn2.subscribe(this);
        this.topicOut.addPublisher(this);
    }
    @Override
    public String getName() {
        return this.agentName;
    }
    @Override
    public void reset()
    {
        this.in1 = null;
        this.in2 = null;
    }
    @Override
    public void callback(String topic, Message msg)
    {
        if (topic.equals(this.topicIn1.name)) {
            this.in1 = msg;
        } else if (topic.equals(this.topicIn2.name)) {
            this.in2 = msg;
        }

        if (this.in1 != null && this.in2 != null) {
            double result = this.operation.apply(this.in1.asDouble, this.in2.asDouble);
            Message outMsg = new Message(result);
            this.topicOut.publish(outMsg);
        }
    }
    @Override
    public void close()
    {
    }

}
