package com.carmatech.algo.graphs.operations;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import com.carmatech.algo.graphs.Edge;
import com.carmatech.algo.graphs.IWeightedGraph;

public class LazyPrimMinimumSpanningTree {
	private final IWeightedGraph<Edge> graph;
	private final int numVertices;
	private final List<Edge> mst = new LinkedList<Edge>();
	private final boolean[] visited;
	private final Queue<Edge> edges = new PriorityQueue<Edge>();

	public LazyPrimMinimumSpanningTree(final IWeightedGraph<Edge> graph) {
		this.graph = graph;
		numVertices = graph.numVertices();
		this.visited = new boolean[numVertices];
	}

	/**
	 * Find the minimum spanning tree in O(E.log(E)).
	 */
	public void findMinimumSpanningTree() {
		mst.clear();
		edges.clear();

		visit(0);

		while (!edges.isEmpty() && (mst.size() < numVertices - 1)) { // V-1 times best case, E times worst case
			final Edge edge = edges.remove(); // O(log(E))
			int v = edge.either();
			int w = edge.other(v);

			if (visited[v] && visited[w]) // O(1)
				continue;

			// V-1 times:
			mst.add(edge);
			if (!visited[v])
				visit(v);
			if (!visited[w])
				visit(w);
		}
	}

	private void visit(final int v) {
		visited[v] = true; // O(1)
		for (final Edge edge : graph.neighbours(v))
			if (!visited[edge.other(v)])
				edges.add(edge); // O(log(E))
	}

	public List<Edge> edges() {
		return mst;
	}

	public int size() {
		return mst.size();
	}
}
