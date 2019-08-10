#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
const int N = 3;
bool isEqual(float array[][N]);
int main()
{
    float reais[N][N] = {{2, 5, 1}, {5, 2, 8}, {2, 4, 1}};
    if (isEqual(reais) == true)
    {
        printf("Equal\n");
    }
    else
    {
        printf("Not equal\n");
    }
}

bool isEqual(float array[][N])
{
    bool retorno;
    int cont = 0;
    for (int i = 0; i < N; i++)
    {
        if (array[0][i] == array[(N - 1)][i])
        {

            cont++;
        }
    }
    if (cont == N)
    {
        retorno = true;
    }
    else
    {
        retorno = false;
    }
    return retorno;
}