#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
const int N = 4;
float MaiorElemento(float array[][N]);
int main()
{
    float reais[N][N] = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
    printf("%.2f\n", MaiorElemento(reais));
}

float MaiorElemento(float array[][N])
{
    int cont = 0;
    int j = 1, t = N - 1;
    float maiorAcima = 0, maiorAbaixo = 0;
    for (int i = 0; i < N; i++)
    {
        for (int x = j; x < N; x++)
        {
            if (array[i][x] > maiorAcima)
            {
                maiorAcima = array[i][x];
            }
        }
        j++;
    }

    for (int a = 1; a < N; a++)
    {
        int aux = 0;
        for (int b = t; b < N; b++)
        {
            if (array[a][aux] > maiorAbaixo)
            {
                maiorAbaixo = array[a][aux];
            }
            aux++;
        }
        t = t - 1;
    }

    return maiorAcima - maiorAbaixo;
}
