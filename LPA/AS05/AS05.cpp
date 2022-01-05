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

void getComponents(int **graph, int ordem)
{
    vector<int> x;
    int control[ordem];
    int connected = 0;
    int stack[ordem];
    int top = -1;
    int components = 0;
    char vertex;

    for (int i = 0; i < ordem; ++i)
        control[i] = 0;

    for (int j = 0; j < ordem; ++j)
    {
        // A variavel control é um vetor que controla quais vertices ja estao em um componente
        // Caso seja 0, dado vertice ainda nao foi comtemplado

        if (control[j] == 0)
        {
            // Ao visitar o vertice, ele é marcado
            control[j] = 1;
            // Esse vetor é colocado no topo da pilha para inserção no vetor de saida
            stack[++top] = j;

            while (top != -1)
            {
                // pega o valor no posicao da pilha e o insere no vetor de saida
                connected = stack[top--];
                x.push_back(connected);

                // Para cada vetor no nosso grafo(matriz), percorre-se suas conexoes
                // Caso exista uma conexao com o vertex i, e esse vertice ainda nao tenha sido comtemplado pela funcao, ele é adicionado no topo da pilha e marcado como visitado
                // Ex: No grafo a,c / b - com dois componentes conexos
                // percorre-se o vertex a e ve que existe conexao com c
                // marca o vertex c pois se a conecta com c, c conecta com a e faz parte desse componente conexo
                // Como top existe entao um elemento na pilha, ele é inserido novamente no vetor de saida e verifica-se suas conexoes que fazem parte do subgrafo
                // Se a conecta com C, C tem suas conexoes
                // Logo, é necessario verificar na matrix suas conexoes e caso ache, repetir o processo com cada elemento dessa pilha
                // Isso é feito até nao achar mais nenhum elemento a ser verificado
                for (int i = 0; i < ordem; ++i)
                {
                    if (graph[connected][i] && control[i] == 0)
                    {
                        stack[++top] = i;
                        control[i] = 1;
                    }
                }
            }

            // Ordena o vetor de saida para a re4sposta do problema
            sort(x.begin(), x.end());

            // Para cada elemento na saida, converte para char novamente e o imprime.
            for (int i = 0; i < x.size(); ++i)
            {
                vertex = x[i] + 97;
                cout << vertex << ",";
            }
            cout << endl;
            x.clear();
            components++;
        }
    }

    cout << components << " connected components"
         << "\n";
}

int main()
{

    char charSrc, charDest;
    int cases, v, e, src, dest;

    cin >> cases;

    for (int i = 1; i <= cases; ++i)
    {
        cin >> v >> e;

        // Init DO GRAFO
        // O grafo é um vetor bidimensional com v x v posições, ou seja, uma matrix quadrada com dimensões iguais ao número de vertices
        // Onde houver conexao entre vertices = 1
        int **graph = new int *[v];
        for (int j = 0; j < v; ++j)
        {
            graph[j] = new int[v];
            for (int k = 0; k < v; ++k)
                graph[j][k] = 0;
        }
        for (int j = 0; j < e; ++j)
        {
            cin >> charSrc >> charDest;
            src = (int)charSrc - 97;
            dest = (int)charDest - 97;
            graph[src][dest] = 1;
            graph[dest][src] = 1;
        }
        cout << "Case #" << i << ":"
             << "\n";
        getComponents(graph, v);
        cout << "\n";
    }
}
