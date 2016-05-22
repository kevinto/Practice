package ch4TreesAndGraphsCURR;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Kevin on 5/21/16.
 */
public class BuildOrder {
    Project[] findBuildOrder(String[] projects, String[][] dependencies) {
        Graph graph = buildGraph(projects, dependencies);
        return orderProjects(graph.getNodes());
    }

    Graph buildGraph(String[] projects, String[][] dependencies) {
        Graph graph = new Graph();
        for (String project : projects) {
            graph.getOrCreateNode(project);
        }

        for (String[] dependency : dependencies) {
            graph.addEdge(dependency[0], dependency[1]);
        }

        return graph;
    }

    Project[] orderProjects(ArrayList<Project> projects) {
        Project[] order = new Project[projects.size()];

        int endOfList = addNonDependent(order, projects, 0);

        int toBeProcessed = 0;
        while (toBeProcessed < order.length) {
            Project current = order[toBeProcessed];

            if (current == null) {
                return null;
            }

            ArrayList<Project> children = current.getChildren();
            for (Project child : children) {
                // We only track by dependency counts.
                child.decrementDependencies();
            }

            endOfList = addNonDependent(order, children, endOfList);
            toBeProcessed++;
        }

        return order;
    }

    int addNonDependent(Project[] order, ArrayList<Project> projects, int offset) {
        for (Project project : projects) {
            if (project.getDependencies() == 0) {
                order[offset] = project;
                offset++;
            }
        }

        return offset;
    }

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
        // TODO: how come we never push to the project map?
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
