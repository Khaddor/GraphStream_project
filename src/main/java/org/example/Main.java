package org.example;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.Random;

public class Main {
    public static void main(String[] args) {

        //Génération d'un graphe aléatoire
        RandomGen randomGen = new RandomGen("myGraph", 3,5);
        Graph graph = randomGen.generate();

        //Ma version du Djikistra
       MyDjikistra myDjikistra = new MyDjikistra(graph, "0");
       graph.display();


       //Djikistra du GraphStream
        Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE,"result", "poids");
        dijkstra.init(graph);
        dijkstra.setSource("0");
        dijkstra.compute();
        System.out.println("---------------Djikistra du GraphStream---------");
        for (Node node : graph)
            System.out.printf("%s->%s:%6.2f%n", dijkstra.getSource(), node, dijkstra.getPathLength(node));

    }
}