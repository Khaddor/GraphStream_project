package org.example;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import java.sql.Time;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        //Génération d'un graphe aléatoire
        RandomGen randomGen = new RandomGen("myGraph", 5,10);
        Graph graph = randomGen.generate();
        System.out.println("Number of Nodes : " + graph.getNodeCount());
        //Ma version du Djikistra
        long before = System.nanoTime();
       MyDjikistra myDjikistra = new MyDjikistra(graph, "0");
       long execTime = System.nanoTime() - before;
      // graph.display();


       //Djikistra du GraphStream
        long before2 = System.nanoTime();
        Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE,"result", "poids");
        dijkstra.init(graph);
        dijkstra.setSource("0");
        dijkstra.compute();
        long execTime2 = System.nanoTime() - before2;
        System.out.println("---------------Djikistra du GraphStream---------");
        for (Node node : graph)
            System.out.printf("%s->%s:%6.2f%n", dijkstra.getSource(), node, dijkstra.getPathLength(node));


        System.out.println("Temps d execution pour la version naive de Djikistra : " + TimeUnit.MILLISECONDS.convert(execTime,TimeUnit.NANOSECONDS) + " ms" );
        System.out.println("Temps d execution pour Graphstream's Djikistra : " +TimeUnit.MILLISECONDS.convert(execTime2,TimeUnit.NANOSECONDS) + " ms");

        // System.out.println(graph.getNodeCount());
    }
}