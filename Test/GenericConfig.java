package test;

import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericConfig {
    private String confFileName;
    private ArrayList<Agent> agents;

    public GenericConfig() {
        this.confFileName = "";
        this.agents = new ArrayList<Agent>();
    }
    public void setConfFile(String filePath) {
        this.confFileName = filePath;
    }
    
    public List<String> readConfigFile() {
        Path path = Paths.get(this.confFileName);
        try {
            List<String> lines = Files.readAllLines(path);
            return lines;
        } catch (Exception e) {
            // System.out.println("Error reading config file: " + e.getMessage());
            return new ArrayList<String>();
        }
    }

    public void create(){
        // System.out.println("Creating configuration from file: " + this.confFileName);
        List<String> lines = readConfigFile();
        if (lines.size() % 3 != 0) {
            throw new RuntimeException("Configuration file format is incorrect.");
        } 
        for (int i = 0; i < lines.size(); i+=3) {
            try {
                String agentName = lines.get(i).trim();
                String[] subs = lines.get(i+1).trim().split(",");
                String[] pubs = lines.get(i+2).trim().split(",");
                agents.add(new ParallelAgent(agentName, subs, pubs));
            }
            catch (Exception e){
                throw new RuntimeException(e);
            }

        }
    }

    public void close() {
        for (Agent agent : agents) {
            agent.close();
        }
    }
}
