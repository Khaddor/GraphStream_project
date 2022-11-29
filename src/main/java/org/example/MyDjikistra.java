package org.example;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;


import java.util.HashMap;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

import java.util.Collections;
public class MyDjikistra {

    Graph graph;
    String source;
    Node minNode,thisNode = null;
    int min=999, poids;

    public MyDjikistra(Graph graph, String source){
        this.graph = graph;
        this.source = source;
       

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
        	System.out.println(thisNode);
        	
        	
        	
            if( thisNode == null){
                //nonVisited.forEach((n,i) -> visited.put(n,i));
                visited.put(thisNode,nonVisited.get(thisNode));
                nonVisited.remove(thisNode);
                System.out.println("hna");
                
            }else{
            	
            		thisNode.neighborNodes().forEach( v -> {
            			poids = (int) thisNode.getEdgeBetween(v).getAttribute("poids");
            				if(nonVisited.containsKey(thisNode) && nonVisited.containsKey(v)){
	                    		if(nonVisited.get(v) > nonVisited.get(thisNode) + poids ){
	                    				nonVisited.replace(v,nonVisited.get(thisNode) + poids);
	                    		}
            				}
            			
            		});
            	
               
                visited.put(thisNode,nonVisited.get(thisNode));
                nonVisited.remove(thisNode);
            }
            
        }
        visited.forEach((n,i)
                -> System.out.println("Sommet : " +source + " vers " + n + " = " + i ));

    }
    
    

    public Node getMinNode(HashMap<Node,Integer> nodes){
    	
    	HashMap<Node, Integer> r = new HashMap<Node, Integer>();
    	//Integer min = Collections.min(nodes.values());
    	Entry<Node, Integer> x = null;
    	
    	/*for(Entry<Node, Integer> node : nodes.entrySet()) {
    		if(node.getValue().equals(min)) {
    			//r.put(node.getKey(), node.getValue());
    			 minNode = node.getKey();
    		}else {
    			minNode = null;
    			} 
    	}**/
    	
    	for (Entry<Node, Integer> node : nodes.entrySet()) {
    	    if (x == null || x.getValue() > node.getValue()) {
    	        x = (Entry<Node, Integer>) node;
    	    }
    	}
    	
		return  x.getKey();
    }
    
    
    
}










