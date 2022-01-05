/**
 * AS09
 * @author THIAGO HENRIQUE DE CASTRO OLIVEIRA
 * @version 1 09/2021
 * Solução inspirada em https://github.com/Masterlemos/algorithms/blob/master/Uri/Uri.1517.Apples.cpp
 */

#include <iostream>
#include <vector>
#include <algorithm>
#include <cstring>

using namespace std;

int area[30][30][3000], row, column, apples, _t;
bool positions[30][30][3000];
int result;

int _catchApples(int rows, int col, int time)
{
    // VERIFICA SE A POSICAO É VALIDA, OU SEJA, PERTENCE A AREA ABAIXO DA ARVORE E NO MOMENTO CERTO
    if ((rows > 0) && (rows <= row) && (col > 0) && (col <= column) && (time <= _t))
    {
        // SE A POSICAO DO VETOR É DIFERENTE DE -1, JA FOI CALCULADA, ENTAO A RETORNAMOS
        if (area[rows][col][time] != -1)
            return area[rows][col][time];

        int result = 0, plus = 0;
        // SE A POSICAO E O TEMPO ATUAL ESTA MARCADA NO VETOR DE POSICOES, PEGAMOS A MACA
        if (positions[rows][col][time])
            plus = 1;

        // PERCORREMOS RECURSIVAMENTE EM TODAS AS DIRECOES, A PARTIR DA POSICAO INICIAL
        // PEGAMOS O MAXIMO ENTRE A RECURSAO E O VALOR DA SAIDA

        result = max(result, _catchApples(rows + 1, col, time + 1));

        result = max(result, _catchApples(rows - 1, col, time + 1));

        result = max(result, _catchApples(rows, col - 1, time + 1));

        result = max(result, _catchApples(rows, col + 1, time + 1));

        result = max(result, _catchApples(rows + 1, col - 1, time + 1));

        result = max(result, _catchApples(rows + 1, col + 1, time + 1));

        result = max(result, _catchApples(rows - 1, col - 1, time + 1));

        result = max(result, _catchApples(rows - 1, col + 1, time + 1));

        result = max(result, _catchApples(rows, col, time + 1));

        return area[rows][col][time] = result + plus;
    }
    else
        return 0;
}

int main()
{

    // VARIAVEIS
    int x, y, t;
    int init_x, init_y;

    do
    {
        // SETA TODO O VETOR COM -1 E 0 RESPECTIVAMENTE
        memset(area, -1, sizeof area);
        memset(positions, 0, sizeof positions);
        cin >> row >> column >> apples;

        // PARA CADA MAÇA QUE CAI, MARCA SUA POSIÇÃO NO VETOR DE POSICOES
        for (int i = 0; i < apples; i++)
        {
            cin >> x >> y >> t;

            positions[x][y][t] = 1;
        }

        // SALVA O MOMENTO DA ULTIMA MAÇA A CAIR
        _t = t;

        cin >> init_x >> init_y;

        result = _catchApples(init_x, init_y, 0);

        if (result != 0)
            cout << result << endl;

    } while (row != 0 && column != 0 && apples != 0);
}
