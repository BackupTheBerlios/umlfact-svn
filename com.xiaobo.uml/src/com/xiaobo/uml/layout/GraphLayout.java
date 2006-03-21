package com.xiaobo.uml.layout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.AbstractLayout;
import org.eclipse.draw2d.Bendpoint;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.RelativeBendpoint;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.graph.DirectedGraph;
import org.eclipse.draw2d.graph.DirectedGraphLayout;
import org.eclipse.draw2d.graph.Edge;
import org.eclipse.draw2d.graph.Node;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

/**
 * 
 * @author xiaobo
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights reserved.
 */
public class GraphLayout extends AbstractLayout {

	private final int padding = 10;

	private AbstractGraphicalEditPart diagram;

	private DirectedGraph graph;

	private Map partToNodes;

	private Point point;

	public GraphLayout(AbstractGraphicalEditPart diagram) {
		this.diagram = diagram;
	}

	protected Dimension calculatePreferredSize(IFigure container, int wHint,
			int hHint) {
		container.validate();
		List children = container.getChildren();
		Rectangle result = new Rectangle().setLocation(container
				.getClientArea().getLocation());
		for (int i = 0; i < children.size(); i++)
			result.union(((IFigure) children.get(i)).getBounds());
		result.resize(container.getInsets().getWidth(), container.getInsets()
				.getHeight());
		return result.getSize();
	}

	public void layout(IFigure container) {
		graph = new DirectedGraph();
		point = diagram.getFigure().getBounds().getLocation();
		partToNodes = new HashMap();
		addNodes();
		addEdges();
		if (graph.nodes.size() > 0) {
			new DirectedGraphLayout().visit(graph);
			applyLayout();
		}
	}

	private void applyLayout() {
		for (int i = 0; i < diagram.getChildren().size(); i++) {
			applyNodeLayout((AbstractGraphicalEditPart) diagram.getChildren()
					.get(i));
		}
		for (int i = 0; i < diagram.getChildren().size(); i++) {
			applyEdgesLayout((AbstractGraphicalEditPart) diagram.getChildren()
					.get(i));
		}
	}

	private void applyEdgesLayout(AbstractGraphicalEditPart part) {
		for (int i = 0; i < part.getSourceConnections().size(); i++) {
			applyEdgeLayout((AbstractConnectionEditPart) part
					.getSourceConnections().get(i));
		}
	}

	private void applyNodeLayout(AbstractGraphicalEditPart part) {
		Node node = (Node) partToNodes.get(part);
		Rectangle bounds = new Rectangle(node.x, node.y, node.width,
				node.height);

		bounds.translate(point);
		part.getFigure().setBounds(bounds);
	}

	private void applyEdgeLayout(AbstractConnectionEditPart part) {
		Edge e = (Edge) partToNodes.get(part);

		PolylineConnection conn = (PolylineConnection) part
				.getConnectionFigure();
		if (e.vNodes != null) {
			List bends = new ArrayList();
			if (!e.isFeedback) {
				for (int i = 0; i < e.vNodes.size(); i++) {
					Node vn = e.vNodes.getNode(i);
					Bendpoint[] bendpoints = prepareBendpoints(vn, conn);
					bends.add(bendpoints[0]);
					bends.add(bendpoints[1]);
				}
			} else {
				for (int i = e.vNodes.size() - 1; i >= 0; i--) {
					Node vn = e.vNodes.getNode(i);
					Bendpoint[] bendpoints = prepareBendpoints(vn, conn);
					bends.add(bendpoints[1]);
					bends.add(bendpoints[0]);
				}
			}
			conn.setRoutingConstraint(bends);
		} else {
			conn.setRoutingConstraint(Collections.EMPTY_LIST);
		}
	}

	// private Bendpoint[] _prepareBendpoints(Node vn, PolylineConnection conn)
	// {
	// Bendpoint[] bendpoints = new Bendpoint[2];
	// bendpoints[0] = new AbsoluteBendpoint(new Point(vn.x, vn.y)
	// .translate(point));
	// bendpoints[1] = new AbsoluteBendpoint(new Point(vn.x, vn.y + vn.height)
	// .translate(point));
	// return bendpoints;
	// }

	private Bendpoint[] prepareBendpoints(Node vn, PolylineConnection conn) {
		Point firstBendPoint = new Point(vn.x, vn.y).translate(point);
		Point secondBendPoint = new Point(vn.x, vn.y + vn.height)
				.translate(point);
		RelativeBendpoint[] bendpoints = new RelativeBendpoint[2];
		bendpoints[0] = new RelativeBendpoint(conn);
		bendpoints[1] = new RelativeBendpoint(conn);
		Point sourcePoint = conn.getSourceAnchor().getReferencePoint();
		Point targetPoint = conn.getTargetAnchor().getReferencePoint();

		diagram.getContentPane().translateToRelative(sourcePoint);
		diagram.getContentPane().translateToRelative(targetPoint);

		bendpoints[0].setRelativeDimensions(firstBendPoint
				.getDifference(sourcePoint), firstBendPoint
				.getDifference(targetPoint));
		bendpoints[1].setRelativeDimensions(secondBendPoint
				.getDifference(sourcePoint), secondBendPoint
				.getDifference(targetPoint));

		return bendpoints;
	}

	private void addNodes() {
		for (int i = 0; i < diagram.getChildren().size(); i++) {
			addNode((AbstractGraphicalEditPart) diagram.getChildren().get(i));
		}
	}

	private void addNode(AbstractGraphicalEditPart part) {
		Node n = new Node(part);
		n.width = part.getFigure().getPreferredSize().width;
		n.height = part.getFigure().getPreferredSize().height;
		n.setPadding(new Insets(padding));
		graph.nodes.add(n);
		partToNodes.put(part, n);
	}

	private void addEdges() {
		for (int i = 0; i < diagram.getChildren().size(); i++) {
			addEdges((AbstractGraphicalEditPart) diagram.getChildren().get(i));
		}
		new ClusterEdgeCreator().visit(graph);
	}

	private void addEdges(AbstractGraphicalEditPart part) {
		List outgoing = part.getSourceConnections();
		for (int i = 0; i < outgoing.size(); i++) {
			addEdge((AbstractConnectionEditPart) outgoing.get(i));
		}
	}

	private void addEdge(AbstractConnectionEditPart part) {
		Node source = (Node) partToNodes.get(part.getSource());
		Node target = (Node) partToNodes.get(part.getTarget());
		Edge e = new Edge(part, source, target);
		graph.edges.add(e);
		partToNodes.put(part, e);
	}
}
