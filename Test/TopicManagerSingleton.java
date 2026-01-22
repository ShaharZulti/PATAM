package test;

import java.util.Collection;
import java.util.HashMap;

public class TopicManagerSingleton {

    public static class TopicManager{
        private static final TopicManager instance = new TopicManager();
        private final HashMap<String, Topic> topics = new HashMap<String, Topic>();

        private TopicManager() {
        }

        public Topic getTopic(String name){
            return this.topics.computeIfAbsent(name, Topic::new);
        }

        public Collection<Topic> getTopics(){
            return this.topics.values();
        }

        public void clear(){
            this.topics.clear();
        }

    }
    public static TopicManager get(){
        return TopicManager.instance;
    }
    
}
