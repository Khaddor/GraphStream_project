package org.example;

import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.Random;

public class RandomGen {
    private String GraphName;
    private int n,d;

    public RandomGen(String GraphName, int n, int d){
        this.GraphName = GraphName;
        this.n = n;
        this.d = d;
    }

    public Graph generate(){
        System.setProperty("org.graphstream.ui", "swing");
        Graph graph = new SingleGraph(GraphName);
        Generator generator = new RandomGenerator(this.d);
        Random r = new Random();

        generator.addSink(graph);
        generator.begin();
        for(int i =0; i<d; i++){
            generator.nextEvents();
        }
        generator.end();

        graph.edges().forEach(e -> {
            e.setAttribute("poids" , r.nextInt(12) );
            e.setAttribute("ui.label" , e.getAttribute("poids"));
        });
        graph.nodes().forEach(e -> {
            System.out.println(e);
        });
        return graph;
    }

}
