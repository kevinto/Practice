package ch4TreesAndGraphsCURR;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Kevin on 5/21/16.
 */
public class BuildOrder {

}

class Graph {
    private ArrayList<Project> nodes = new ArrayList<>();
    private HashMap<String, Project> map = new HashMap<>();

    public Project getOrCreateNode(String name) {
        if (!map.containsKey(name)) {
            Project node = new Project(name);
            nodes.add(node);
            map.put(name, node);
        }

        return map.get(name);
    }

    public void addEdge(String startName, String endName) {
        Project start = getOrCreateNode(startName);
        Project end = getOrCreateNode(endName);
        start.addNeighbor(end);
    }

    public ArrayList<Project> getNodes() {
        return nodes;
    }
}

class Project {
    private ArrayList<Project> children = new ArrayList<>();
    private HashMap<String, Project> map = new HashMap<>();
    private String name;
    private int dependencies = 0;

    public Project(String n) { name = n; }

    public void addNeighbor(Project node) {
        if (!map.containsKey(node.getName())) {
            children.add(node);
            node.incrementDependencies();
        }
    }

    public void incrementDependencies() {
        dependencies++;
    }

    public void decrementDependencies() {
        dependencies--;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Project> getChildren() {
        return children;
    }

    public int getDependencies() {
        return dependencies;
    }
}
