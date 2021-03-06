package com.carmatech.algo.graphs;

import com.carmatech.algo.utilities.IVisitable;

import java.util.List;
import java.util.function.Function;

public interface IWeightedGraph<T extends Edge> extends IVisitable<IWeightedGraph<T>> {
	void addEdge(T edge);

	List<T> neighbours(int vertice);

	List<T> allEdges();

	int numVertices();

	int numEdges();

	int degree(int vertice);

	int maxDegree();

	double averageDegree();

	int edgesToSelf(int vertice);

	int numEdgesToSelf();

	boolean isCyclic();

	boolean hasEdgeToSelf(int vertice);

	<Out> Out visit(final Function<IWeightedGraph<T>, Out> visitor);
}
