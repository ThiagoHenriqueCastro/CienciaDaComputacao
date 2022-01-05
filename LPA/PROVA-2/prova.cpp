/**
 * Prova 2
 * @author THIAGO HENRIQUE DE CASTRO OLIVEIRA
 * @version 1 09/2021
 * COMPLEXIDADE O(n) onde n é o numero de consultas ao vetor de colonias

 */

#include <iostream>
#include <algorithm>
#include <vector>
#include <map>
#include <iterator>
#include <utility>
using namespace std;

typedef pair<int, string> Pair;

template <typename t>
void move(std::vector<t> &v, size_t oldIndex, size_t newIndex)
{

    if (oldIndex > newIndex)
        std::rotate(v.rend() - oldIndex - 1, v.rend() - oldIndex, v.rend() - newIndex);
    else
        std::rotate(v.begin() + oldIndex, v.begin() + oldIndex + 1, v.begin() + newIndex + 1);
}

// Define um vertice do grafo
class Edge
{
public:
    int source, destination, weight;
    bool visited;
    Edge(int source, int destination, int weight, bool visited)
    { //constructor
        this->source = source;
        this->destination = destination;
        this->weight = weight;
        this->flag = flag;
        this->visited = visited;
    }
};

// Define um grafo
// Esse grafo entao possui um vetor de vertices e uma funcao interna para adicionar vertices na estrutura
class Graph
{
public:
    vector<Edge> edges;

    void addEdge(int source, int destination, int weight, bool visited);
    {
        Edge obj(source, destination, weight, visited);
        edges.push_back(obj); //push one edge to edge container
    }
};

// Define a classe Kruskal da arvore geradora minima
class Kruskal
{

public:
    int _vertices;

    // Subsets sao partes de um grafo que contem apenas os nós e nao as arestas
    // A variavel subset abaixo guarda o rank de cada no
    // O nó é um auxiliar para as operacoes union find
    vector<pair<int, int> > subsets;

    // A arvore geradora minima resultante
    vector<Edge> result;

    Kruskal(int _vertices)
    {
        this->_vertices = _vertices;

        // O vetor de subsets é ajustado para o tamanho de vertices total
        subsets.resize(_vertices);

        // inicializa os subsets
        for (int i = 0; i < _vertices; ++i)
        {
            subsets[i].first = i;
            subsets[i].second = 0;
        }
    }

    // funcao de comparaçao usada no sort
    // Testa se um peso é maior que o outro para ordenar
    static bool compare(Edge &x, Edge &y)
    {
        return x.weight < y.weight;
    }

    // Funcao de criação da arvore gerado minima
    // Recebe um grafo como parametro
    void createArvoreGeradora(Graph &graph, int start)
    {
        // Ordena o grafo baseado no peso dos vertices
        sort(graph.edges.begin(), graph.edges.end(), compare);

        // i =  index do total de vertices do grafo
        // e = index das arestas da arvore geradora minima
        int i = 0, e = 0;

        // Enquanto o numero de arestas da arvore geradora minima for menor que o numero total de arestas necessarios para ser minimmo
        while (e < (_vertices - 1) && i < graph.edges.size())
        {
            // Para cada aresta, verifica a origem e o destino e se batem com valores ja na arvore geradora minima para gerar ciclos
            Edge stateEdge = graph.edges[i++];

            // encontra o pai absoluto dos vertices de origem e destino de uma aresta
            int x = _find(stateEdge.source);
            int y = _find(stateEdge.destination);

            // Verifica se nenhum ciclo é formado, ou seja, nenhum pai absoluto é igual
            if (x != y)
            {
                result.push_back(stateEdge);

                // Cria uma aresta entre os vertices
                _union(x, y);
            }
        }
        int cost = 0;

        for (int i = 0; i < result.size(); i++)
        {
            // Faz a soma dos pesos da arvore resultante
            cost += result[i].weight;
        }

        cout << cost << endl;
    }

    // Funcao find
    // Path compression
    // Cada chamada de find, fazemos o valor ser filho da raiz, de forma que nas chamdas subsequentes nao seja necessario percorrer toda a arvore novamente nas proximas chamadas
    // essa funcao faz a arvore ficar mais achatada.
    // A forma mais facil de percorrer essa arvore de ponta a ponta é subir pai por pai até chegar na raiz
    // Todo nó entao vai apontando pra raiz
    int _find(int i)
    {
        if (subsets[i].first != i)
        {
            subsets[i].first = _find(subsets[i].first);
        }

        return subsets[i].first;
    }

    // Funcao de uniao
    // A uniao é feita apontando colocando o pai de um como pai de outra arvore
    // Baseado no rank, decide qual raiz ira ser o novo pai da uniao
    // Se os ranks forem iguais, escolhemos deliberadamente.
    void _union(int x, int y)
    {
        int xroot = _find(x);
        int yroot = _find(y);

        // if-else for rank comparison & update parent-rank values
        if (subsets[xroot].second < subsets[yroot].second)
        {
            subsets[xroot].first = yroot;
        }
        else if (subsets[xroot].second > subsets[yroot].second)
        {
            subsets[yroot].first = xroot;
        }
        else
        {
            subsets[xroot].first = yroot;
            subsets[yroot].second++;
        }
    }
};

int main()
{
    int stations, connections;
    cin >> stations >> connections;
    string station;
    string start;
    int parsedStart;

    string routeA, routeB;
    int parsedA, parsedB;
    int price;

    Graph g;

    vector<Pair> map;
    while (stations != 0)
    {
        for (int i = 0; i < stations; i++)
        {
            cin >> station;
            map.push_back(make_pair(i, station));
        }

        for (int j = 0; j < connections; j++)
        {
            cin >> routeA >> routeB >> price;

            for (int k = 0; k < map.size(); k++)
            {
                if (routeA == map[k].second)
                    parsedA = map[k].first;
                if (routeB == map[k].second)
                    parsedB = map[k].first;
            }

            g.addEdge(parsedA, parsedB, price, false);
        }
        cin >> start;
        for (int k = 0; k < map.size(); k++)
        {
            if (start == map[k].second)
                parsedStart = map[k].first;
        }
        //create and object of kruskal class
        Kruskal graph(stations);

        //call kruskal class's member function to find result
        if (connections < stations - 1)
            cout << "Impossible" << endl;

        else
            graph.createArvoreGeradora(g, parsedStart);

        cin >> stations >> connections;
    }
}
