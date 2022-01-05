/**
 * AS05 - Grafos
 * @author THIAGO HENRIQUE DE CASTRO OLIVEIRA
 * @version 1 08/2021
 * COMPLEXIDADE O(N)
 * Referencias e estudo:
 * Utilizei majoritariamente os artigos abaixo para compreencão do algoritmo e para implementação, onde estudei e procurei entender a solução
 * https://github.com/sauloantuness/maratona/blob/master/uri/1082.cpp
 */

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int tsp(int **graph, int s, int V)
{
    // store all vertex apart from source vertex
    vector<int> vertex;
    for (int i = 0; i < V; i++)
        if (i != s)
            vertex.push_back(i);

    // store minimum weight Hamiltonian Cycle.
    int min_path = INT_MAX;
    do
    {

        // store current Path weight(cost)
        int current_pathweight = 0;

        // compute current path weight
        int k = s;
        for (int i = 0; i < vertex.size(); i++)
        {
            if (graph[k][vertex[i]] != -1)
            {

                current_pathweight += graph[k][vertex[i]];
            }
            k = vertex[i];
        }
        if (graph[k][s] != -1)
        {
            current_pathweight += graph[k][s];
        }

        // update minimum
        min_path = min(min_path, current_pathweight);

    } while (
        next_permutation(vertex.begin(), vertex.end()));

    return min_path;
}
int main()
{

    int cases, vertex, edges, teleports, src, dest, weight;

    cin >> cases;

    for (int i = 1; i <= cases; ++i)
    {
        cin >> vertex >> edges >> teleports;

        int **graph = new int *[vertex];
        for (int j = 0; j < vertex; ++j)
        {
            graph[j] = new int[vertex];
            for (int k = 0; k < vertex; ++k)
            {
                if (j == k)
                    graph[j][k] = 0;

                else
                    graph[j][k] = -1;
            }
        }

        for (int p = 0; p < edges; p++)
        {
            cin >> src >> dest >> weight;
            graph[src - 1][dest - 1] = weight;
            graph[dest - 1][src - 1] = weight;
        }
        /*
        for (int x = 0; x < vertex; x++)
        {
            for (int y = 0; y < vertex; y++)
            {
                cout << "Graph[" << x << "][" << y << "]: " << graph[x][y] << endl;
            }
        }*/
        cout << tsp(graph, 0, vertex) << endl;
    }
}
