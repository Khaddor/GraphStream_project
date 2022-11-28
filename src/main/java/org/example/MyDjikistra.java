package org.example;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import java.util.HashMap;

public class MyDjikistra {

    Graph graph;
    String source;
    Node thisNode ,minNode = null;
    int min, poids;

    public MyDjikistra(Graph graph, String source){
        this.graph = graph;
        this.source = source;
        this.min = 999;

        HashMap<Node,Integer> visited = new HashMap<>();
        HashMap<Node,Integer> nonVisited = new HashMap<>();

        //Distance = infini pour tous les noeud non visites
        graph.nodes().forEach(e -> {
            nonVisited.put(e, Integer.MAX_VALUE);
        });

        //Distance = 0 pour le noeud source
        nonVisited.replace(graph.getNode(source), 0);

        while(!nonVisited.isEmpty()){
            thisNode = getMinNode(nonVisited);

            if( thisNode == null){
                nonVisited.forEach((n,i) -> visited.put(n,i));
                nonVisited.clear();
            }else{
                for(Node n : thisNode.neighborNodes().toList()){
                    poids = (int) thisNode.getEdgeBetween(n).getAttribute("poids");

                    if(nonVisited.get(n) > nonVisited.get(thisNode) + poids ){
                        nonVisited.replace(n,nonVisited.get(thisNode) + poids);
                    }
                }
                visited.put(thisNode,nonVisited.get(thisNode));
                nonVisited.remove(thisNode);

            }
        }

        visited.forEach((n,i)
                -> System.out.println("Sommet : " +source + " vers " + n + " = " + i ));
    }

    public Node getMinNode(HashMap<Node,Integer> nodes){
        for(Node node : nodes.keySet()){
            if(nodes.get(node) < min){
                minNode = node;
                min = nodes.get(node);
            }
        }
        return minNode;
    }
}










