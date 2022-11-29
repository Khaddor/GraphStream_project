package org.example;

import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.Random;

public class Main {
    public static void main(String[] args) {


        RandomGen randomGen = new RandomGen("myGraph", 3,5);
        Graph graph = randomGen.generate();
        
       MyDjikistra myDjikistra = new MyDjikistra(graph, "0");
       graph.display();

    }
}