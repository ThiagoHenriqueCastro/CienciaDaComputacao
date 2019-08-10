#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
const int N = 4;
float SomaColuna(float array[][N], int col);
int main()
{
    float reais[N][N] = {{2, 5, 2, 7}, {5, 4, 8, 8}, {2, 6, 1, 9}, {3, 8, 10, 1}};
    printf("%.2f\n", SomaColuna(reais, 1));
}

float SomaColuna(float array[][N], int col)
{
    float soma = 0;
    for (int i = 0; i < N; i++)
    {
        soma += array[i][col];
    }

    return soma;
}