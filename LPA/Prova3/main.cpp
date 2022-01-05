/**
 * PROVA-03
 * @author THIAGO HENRIQUE DE CASTRO OLIVEIRA
 * @version 1 09/2021
 * Complexidade: O(K(Vˆ2)) onde V são os vertices e K o numero de consultas e execuções do algoritmo
 */

#include <iostream>
#include <vector>
#include <algorithm>
#include <cstring>
#include <iomanip>
#include <climits>

// Constantes
#define CONST_T 1000000
#define CONST_S 501

using namespace std;

class Graph
{
public:
    int vertex;
    int edges;
    int routes[CONST_S][CONST_S];
};

void Dijkstra(int src, int dest, Graph g)
{
    // indexadores
    int a0, a1;

    // Array que marca posicoes visitadas
    bool visited[CONST_S];
    // Array com as distancias
    int dist[CONST_S];

    // inicializa todas as distancias com o maximo e tudo como nao visitado
    memset(visited, false, sizeof(visited));
    memset(dist, CONST_T, sizeof(dist));

    dist[src] = 0; // A distancia da origem para ela mesma é 0

    int min;
    do
    {
        // Define o minimo como a constante maxima de constante definida
        min = CONST_T;

        // Para cada vertice do grafo, testa se foi visitado e se a distancia do msm é menor do que o ultimo minimo
        // Caso seja menor, o valor de minimo é atualizado para posteriores verificações
        // Possuo 2 indexadores, nesse caso o a1 percorre e a0 guarda a posicao do minimo encontrado
        for (a1 = 0; a1 < g.vertex; a1++)
        {
            if (!visited[a1] && min > dist[a1])
            {
                min = dist[a0 = a1];
            }
        }

        // Marco a posicao como visitada
        visited[a0] = true;

        // Percorro novamente todos os vertices
        // Aqui, atualizo a distancia dos vertices vizinhos
        for (a1 = 0; a1 < g.vertex; a1++)
        {
            if (dist[a1] > dist[a0] + g.routes[a0][a1])

                dist[a1] = dist[a0] + g.routes[a0][a1];
        }
    } while (min != CONST_T);

    // Caso no vetor de distancia, a posicao destino seja menor doq a constante maxima definida, é possivel entregar
    // Isso acontece pois caso nao seja possivel estabelecer uma conexao, a constante inicializada continua marcada
    if (dist[dest] < CONST_T)
        cout << dist[dest] << endl;
    else
        cout << "Nao e possivel entregar a carta" << endl;
}

int main()
{

    // Variaveis de entrada
    int N, E, X, Y, H, K, O, D;
    Graph graph;

    cin >>
        N >> E;

    do
    {

        // Inicializo as rotas com a constante de tempo definida no cabecalho
        for (int i = 0; i <= N; i++)
        {
            for (int j = 0; j <= N; j++)
            {
                graph.routes[i][j] = CONST_T;
            }
        }

        // Para cada acordo, leio como esse acordo funciona
        for (int i = 0; i < E; i++)
        {
            cin >> X >> Y >> H;

            // Guardo na posição o tempo do acordo
            if (graph.routes[Y][X] != CONST_T)
                graph.routes[X][Y] = 0, graph.routes[Y][X] = 0;
            else
                graph.routes[X][Y] = H;
        }

        // Inicializo o grafos
        // O numero de arestas é o numero de acordos, conexoes entre as cidades
        graph.vertex = N + 1;
        graph.edges = E;

        cin >> K;

        for (int i = 0; i < K; i++)
        {
            cin >> O >> D;

            // Para cada teste de mensagem, executo o algoritmo para encontrar a menor distancia entre eles
            Dijkstra(O, D, graph);
        }
        cout << endl;
        cin >> N >> E;
    } while (N != 0 && E != 0);
}