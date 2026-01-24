package test;

import java.lang.reflect.Constructor;

public class ParallelAgent implements Agent {
    private Agent agent = null;
    public ParallelAgent(String agentName, String[] subs, String[] pubs) {
        try {
            Class<?> agentClass = Class.forName(agentName);
            
            // Constructor with parameters (String, int)
            Constructor<?> constructor = agentClass.getConstructor(String[].class, String[].class);
            
            // Create instance with arguments
            Agent newAgent = (Agent) constructor.newInstance(subs, pubs);
            this.agent = newAgent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public String getName() {
        return this.agent.getName();
    }
    @Override
    public void reset() {
        this.agent.reset();
    }
    @Override
    public void callback(String topic, Message msg) {
        this.agent.callback(topic, msg);
    }

    @Override
    public void close() {
        this.agent.close();
    }
}
