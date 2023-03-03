package com.example.karachiuniversitymap;

import static java.lang.Integer.MAX_VALUE;

import java.util.ArrayList;

public class dijsktraMap {
    int[][] graph;
    int[][] path;
    int rows;
    public dijsktraMap() {
        this.graph = new int[][]
                {
                        {7,-1,6,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                        {7,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                        {6,-1,-1,3,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                        {-1,-1,3,-1,5,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                        {-1,-1,-1,5,-1,5,2,-1,-1,-1,1,6,-1,-1,-1,5},
                        {-1,-1,-1,-1,5,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                        {-1,-1,-1,-1,2,-1,-1,5,-1,-1,-1,-1,-1,-1,-1,-1},
                        {-1,-1,-1,-1,-1,-1,5,-1,11,-1,-1,-1,-1,-1,-1,-1},
                        {-1,-1,-1,-1,-1,-1,-1,11,-1,10,-1,-1,-1,-1,-1,-1},
                        {-1,-1,-1,-1,-1,-1,-1,-1,10,-1,5,-1,-1,-1,-1,-1},
                        {-1,-1,-1,-1,1,-1,-1,-1,-1,5,-1,-1,-1,-1,-1,-1},
                        {-1,-1,-1,-1,6,-1,-1,-1,-1,-1,-1,-1,13,-1,-1,-1},
                        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,13,-1,9,-1,-1},
                        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,9,-1,2,-1},
                        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,2,-1,9},
                        {-1,-1,-1,-1,5,-1,-1,-1,-1,-1,-1,-1,-1,-1,9,-1},
                };
        this.rows = 16;
        this.path = new int[rows][rows];
    }
    int SmallestUnknownVertex(int[] dist, boolean[] UnknownKnownVertices)
    {
        int min_vertex = 0;
        int min = MAX_VALUE;
        for (int i = 0; i < rows; i++)
        {
            if (dist[i] <= min && !UnknownKnownVertices[i])
            {
                min_vertex = i;
                min = dist[i];
            }
        }
        return min_vertex;
    }
    ArrayList dijkstra(int src, int des)
    {
        int[] dist = new int[rows];
        boolean[] UnknownKnownVertices = new boolean[rows];
        int count = 0;
        // Initializing 2d Array "path" cells with "-1"
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < rows; j++)
            {
                path[i][j] = -1;
            }
        }

        // Initializing 2d Array "dist" and "UnknownKnownVertices" cells with "Maximum Integer number" and "false"
        for (int i = 0; i < rows; i++)
        {
            dist[i] = MAX_VALUE;
            UnknownKnownVertices[i] = false;
        }

        // Initialzing the src in the distance array to "0"
        dist[src] = 0;

        for (int i = 0; i < rows; i++)
        {
            int v = SmallestUnknownVertex(dist, UnknownKnownVertices); // finding the smallest unknown Vertex
            if (v == -1)                                               // if vertex is invalid
            {
                break;
            }
            else
            {
                UnknownKnownVertices[v] = true; // making the found vertex true so it wont be choose in future
                for (int j = 0; j < rows; j++)
                {
                    if (graph[v][j] > 0 && !UnknownKnownVertices[j] && dist[v] + graph[v][j] < dist[j])
                    {

                        dist[j] = dist[v] + graph[v][j]; // updating the dist array
                        path[j][j] = v;                // updating the path 2d Array to track down the vertices
                        count++;
                    }
                }
            }
        }

        return printGraph(dist,src,des);
    }
    ArrayList printGraph(int[] dist, int src, int des) {
        ArrayList<Integer> pathInfo = new ArrayList<>();
        ArrayList<String> CompletePath = new ArrayList<>();
        CompletePath.add(location(src));
        for (int j = 0; j < rows; j++) {
            if (path[des][j] > -1) {
                int u = path[des][des];
                while (u != -1) {
                    if (path[u][u] == -1)
                        break;
                    pathInfo.add(path[u][u]);
                    u = path[u][u];
                }
                for (int x = pathInfo.size() - 1; x >= 0; x--) {
                    CompletePath.add(location(pathInfo.get(x)));
                }
                CompletePath.add(location(path[des][j]));
                CompletePath.add(location(des));
                CompletePath.add(String.valueOf(dist[des]));
            }
        }
        return CompletePath;

    }
    String location(int num)
    {
        if(num == 0) return "Kaneez Fatima Gate";
        else if(num == 1) return "Mass Communication";
        else if(num == 2) return "UBIT";
        else if(num == 3) return "Pharmacy Department";
        else if(num == 4) return "KU Circular Road";
        else if(num == 6) return "Biochemistry Department";
        else if(num == 7) return "Admin Block";
        else if(num == 8) return "Silver Jublee Gate";
        else if(num == 9) return "Gymnasium";
        else if(num == 10) return "Habib Bank";
        else if(num == 11) return "Pharmacy Canteen";
        else if(num == 12) return "Muskan Gate";
        else if(num == 13) return "KUBS";
        else if(num == 14) return "Public Adminstration Department";
        else if(num == 15) return "IBA University";
        else return null;
    }
}
