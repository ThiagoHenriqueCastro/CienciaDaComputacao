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
    int j = 1;
    float maior = 0;
    for (int i = 0; i < N; i++)
    {
        for (int x = j; x < N; x++)
        {
            if (array[i][x] > maior)
            {
                maior = array[i][x];
            }
        }
        j++;
    }

    return maior;
}