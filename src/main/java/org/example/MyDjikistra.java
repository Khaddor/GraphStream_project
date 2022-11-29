package org.example;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;


import java.util.HashMap;
import java.util.Map.Entry;

public class MyDjikistra {

    Graph graph;
    String source;
    Node minNode,thisNode = null;
    int poids;

    public MyDjikistra(Graph graph, String source){
        this.graph = graph;
        this.source = source;

        //Liste des noueds non parcourus
        HashMap<Node,Integer> nonVisited = new HashMap<>();

        //Liste des noeuds dèjà parcourus
        HashMap<Node,Integer> visited = new HashMap<>();

        //Initialiser la distance par l'infini pour tous les noeuds
        graph.nodes().forEach(e -> {
            nonVisited.put(e, Integer.MAX_VALUE);
        });

        //Initialiser le noued source par 0
        nonVisited.replace(graph.getNode(source), 0);

        System.out.println("--------Ma version du Djikistra----------------");
        //parcourir tous les noeuds dans la liste des noueds non visités
        while(!nonVisited.isEmpty()){

        	//Extraire le noeuds avec la distance minimale
        	thisNode = getMinNode(nonVisited);

        	
            if( thisNode == null){
                //nonVisited.forEach((n,i) -> visited.put(n,i));
                visited.put(thisNode,nonVisited.get(thisNode));
                nonVisited.remove(thisNode);
                
            }else{
                    //Parcourir tous les noeuds voisins du Nouveau noeud
            		thisNode.neighborNodes().forEach( v -> {
            			poids = (int) thisNode.getEdgeBetween(v).getAttribute("poids");
            				if(nonVisited.containsKey(thisNode) && nonVisited.containsKey(v)){
	                    		if(nonVisited.get(v) > nonVisited.get(thisNode) + poids ){
	                    				nonVisited.replace(v,nonVisited.get(thisNode) + poids);
	                    		}
            				}
            		});
            	
               //Deplacé le noeud traité de liste des noeuds non-visités vers la liste des noeuds visités
                visited.put(thisNode,nonVisited.get(thisNode));
                nonVisited.remove(thisNode);
            }
            
        }
        //Affichage du résultat (distance minimale depuis le noeud source vers tous les noeuds)
        visited.forEach((n,i)
                -> System.out.println("Sommet : " +source + " vers " + n + " = " + i ));
    }
    
    

    //Fonction pour extraire le noeud avec le poids minimun
    public Node getMinNode(HashMap<Node,Integer> nodes){
    	
    	//HashMap<Node, Integer> r = new HashMap<Node, Integer>();
    	Entry<Node, Integer> x = null;
        //Chercher la plus petite valeur dans la HashMap
    	for (Entry<Node, Integer> node : nodes.entrySet()) {
    	    if (x == null || x.getValue() > node.getValue()) {
    	        x = (Entry<Node, Integer>) node;
    	    }
    	}
        //Renvoyer le Noeud avec la valeur minimale
		return  x.getKey();
    }
    
    
    
}










