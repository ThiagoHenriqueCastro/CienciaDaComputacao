#include <stdio.h>
#include <stdlib.h>

int main()
{
    float array[9] = {1, 2, 3, 4, 5, 6, 7, 8, 10}, maior = INT16_MIN, segundoMaior = INT16_MIN;
    int i, x;

    for (i = 0; i <= 9; i++)
    {
        if (array[i] > maior)
        {
            maior = array[i];
                }
    }
    for (i = 0; i <= 9; i++)
    {
        if (maior > array[i] && array[i] > segundoMaior)
        {
            segundoMaior = array[i];
        }
    }

    printf("Maior %.2f\n", maior);
    printf("Segundo maior %.2f\n", segundoMaior);
}