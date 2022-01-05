/**
 * AS10
 * @author THIAGO HENRIQUE DE CASTRO OLIVEIRA
 * @version 1 09/2021
 */

#include <iostream>
#include <vector>
#include <algorithm>
#include <cstring>
#include <iomanip>
#include <tuple>
#include <climits>

using namespace std;

int _lines = 0;
int _cols = 0;

class Triplet
{
public:
    int first, second, third;

    Triplet(int a, int b, int c)
    {
        this->first = a;
        this->second = b;
        this->third = c;
    }
};

class Board
{
public:
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

    int horseMove(int srcX, int srcY, int destX, int destY, vector<pair<int, int> > u)
    {
        bool tested[_lines][_cols];
        for (int a = 0; a < _lines; a++)
            for (int b = 0; b < _cols; b++)
                tested[a][b] = false;

        for (int i = 0; i < u.size(); i++)
        {
            tested[u[i].first][u[i].second] = true;
        }

        vector<pair<int, int> > movimentos;
        movimentos.push_back(make_pair(-1, -2));
        movimentos.push_back(make_pair(-2, -1));
        movimentos.push_back(make_pair(-2, 1));
        movimentos.push_back(make_pair(-1, 2));
        movimentos.push_back(make_pair(1, 2));
        movimentos.push_back(make_pair(2, 1));
        movimentos.push_back(make_pair(2, -1));
        movimentos.push_back(make_pair(1, -2));

        vector<Triplet> passos;
        passos.push_back(Triplet(srcX, srcY, 0));

        int x = 0, y = 0;

        while (true)
        {
            vector<Triplet> nextSteps;

            for (int i = 0; i < passos.size(); i++)
            {
                for (int j = 0; j < movimentos.size(); j++)
                {
                    x = passos[i].first + movimentos[j].first;
                    y = passos[i].second + movimentos[j].second;

                    if (x == destX && y == destY)
                        return passos[i].third + 1;
                    if (0 <= x < _lines && 0 <= y < _cols && !tested[x][y])
                    {
                        nextSteps.push_back(Triplet(x, y, passos[i].third + 1));
                        tested[x][y] = true;
                    }
                }
            }
            passos = nextSteps;
            nextSteps.clear();
            nextSteps.shrink_to_fit();
        }
    }

    void run(int l, int c, int k)
    {
        int init_x, init_y;
        char board[l][c];
        int **graph = new int *[k + 1];
        for (int j = 0; j < (k + 1); ++j)
        {
            graph[j] = new int[k + 1];
            for (int t = 0; t < (k + 1); ++t)
            {
                if (j == t)
                    graph[j][t] = 0;

                else
                    graph[j][t] = -1;
            }
        }
        vector<pair<int, int> > p;
        vector<pair<int, int> > unable;

        for (int i = 0; i < l; i++)
            for (int j = 0; j < c; j++)
            {
                cin >> board[i][j];
                if (board[i][j] == 'C')
                {
                    init_x = i, init_y = j;
                    p.insert(p.begin(), make_pair(i, j));
                }
                else if (board[i][j] == 'P')
                    p.push_back(make_pair(i, j));
                else if (board[i][j] == '#')
                    unable.push_back(make_pair(i, j));
            }

        int weight = 0;
        // De cada P para cada P ou C
        for (int i = 0; i < p.size() - 1; i++)
        {
            for (int j = i + 1; j < p.size(); j++)
            {
                weight = horseMove(p[i].first, p[i].second, p[j].first, p[j].second, unable);
                graph[i][j] = weight;
                graph[j][i] = weight;
            }
        }

        cout << tsp(graph, 0, k + 1) << endl;
    }
};

int main()
{
    int lines, cols, k;
    cin >> lines >> cols >> k;
    _lines = 8;
    _cols = 8;
    while (lines != 0)
    {
        Board board;

        board.run(lines, cols, k);

        cin >> lines >> cols >> k;
        _lines = 8;
        _cols = 8;
    }
}