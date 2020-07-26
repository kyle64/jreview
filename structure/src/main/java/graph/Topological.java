package graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by ziheng on 2020/7/26.
 */
public class Topological {
    // 拓扑排序
    class Vertex {
        int value;
        int inDegreee;
        List<Edge> adjcentEdges;
    }

    class Edge {
        Vertex sourcecVertex;
        Vertex targetVertex;
    }

    public void topoSort(Vertex[] nums) {
        // 辅助队列
        Queue<Vertex> queue = new LinkedList<>();
        int count = 0;

        // 将入度为0的节点放入队列
        for (Vertex vertex : nums) {
            if (vertex.inDegreee == 0) {
                queue.offer(vertex);
            }
        }

        while (!queue.isEmpty()) {
            // 取出vertex
            Vertex v = queue.poll();
            System.out.print(v.value + " ");
            count++;

            // 将相邻的vertex入度减一
            for (Edge edge : v.adjcentEdges) {
                // 如果入度为0，加入队列
                if (--edge.targetVertex.inDegreee == 0) {
                    queue.offer(edge.targetVertex);
                }
            }
        }

        if (count != nums.length) {
            System.out.println("Graph hash cycle");
        }
    }
}
