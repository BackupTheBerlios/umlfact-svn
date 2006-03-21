package com.xiaobo.uml.layout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.graph.DirectedGraph;
import org.eclipse.draw2d.graph.Edge;
import org.eclipse.draw2d.graph.EdgeList;
import org.eclipse.draw2d.graph.Node;

/**
 * 
 * @author xiaobo
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights reserved.
 */
public class ClusterEdgeCreator {

	DirectedGraph graph;

	List edgesAdded;

	List encountered = new ArrayList();

	List clusters = new ArrayList();

	List currentCluster;

	public void visit(DirectedGraph graph) {
		this.graph = graph;
		edgesAdded = new ArrayList();

		Collections.sort(graph.nodes, new Comparator() {
			public int compare(Object o1, Object o2) {
				Node t1 = (Node) o1;
				Node t2 = (Node) o2;
				return t1.incoming.size() - t2.incoming.size();
			}
		});

		for (Iterator iter = graph.nodes.iterator(); iter.hasNext();) {
			Node node = (Node) iter.next();

			if (!encountered.contains(node)) {
				currentCluster = new ArrayList();
				clusters.add(currentCluster);
				encountered.add(node);
				currentCluster.add(node);

				recursivelyAddToCluster(node);
			}
		}
		joinClusters();
	}

	private void joinClusters() {
		if (clusters.size() > 1) {
			Node sourceNode = null;
			Node targetNode;

			for (Iterator iter = clusters.iterator(); iter.hasNext();) {
				List cluster = (List) iter.next();
				if (sourceNode != null) {
					targetNode = (Node) cluster.get(0);
					graph.edges.add(new Edge(sourceNode, targetNode));
				}
				sourceNode = (Node) cluster.get(cluster.size() - 1);
			}

		}
	}

	private void recursivelyAddToCluster(Node node) {
		EdgeList incoming = node.incoming;
		for (Iterator iter = incoming.iterator(); iter.hasNext();) {
			Edge edge = (Edge) iter.next();
			Node incomingNode = edge.source;

			if (!encountered.contains(incomingNode)) {
				encountered.add(incomingNode);
				currentCluster.add(incomingNode);

				recursivelyAddToCluster(incomingNode);
			}
		}

		EdgeList outgoing = node.outgoing;
		for (Iterator iter = outgoing.iterator(); iter.hasNext();) {
			Edge edge = (Edge) iter.next();
			Node outgoingNode = edge.target;

			if (!encountered.contains(outgoingNode)) {
				encountered.add(outgoingNode);
				currentCluster.add(outgoingNode);

				recursivelyAddToCluster(outgoingNode);
			}
		}
	}
}
