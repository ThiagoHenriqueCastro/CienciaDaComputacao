#include <stdio.h>
#include <stdlib.h>
float Media();
int Maior(float array[3][2], float media);
const int NC1 = 2;
const int NL1 = 3;
int main()
{
    float A[][2] = {{3, 5}, {2, 5}, {2, 2}};
    float media = Media(A);
    int maior = Maior(A, media);
    printf("Media: %.2f \n", media);
    printf("Maior: %i \n", maior);
}

float Media(float array[3][2])
{
    float media = 0, soma = 0;
    int i, j;

    for (i = 0; i < NL1; i++)
    {

        for (j = 0; j < NC1; j++)
        {

            soma += array[i][j];
        }
    }

    return soma / (i * j);
    ;
}
int Maior(float array[3][2], float media)
{
    int maior = 0;

    for (int i = 0; i < NL1; i++)
    {

        for (int j = 0; j < NC1; j++)
        {

            if (array[i][j] > media)
            {
                maior++;
            }
        }
    }

    return maior;
}
/***************************************************************
 * Assinatura da fun��o:
 * Data da elabora��o: 20/05
 * Autor: Thiago Oliveira
 * Objetivo: quantidade de valores acima da media
 * Dados de entrada (argumentos): array e media
 * Dado de sa�da (valor gerado pela fun��o): quantidade
 ****************************************************************
 */
