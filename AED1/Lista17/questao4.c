#include <stdio.h>
#include <stdlib.h>
float Maior(float array[][2], int x);
const int NC1 = 2;
const int NL1 = 3;

int main()
{
    float A[][2] = {{1.2, 5.9}, {8.5, 8.04}, {1, 100.0}};
    float maior = Maior(A, 0);
    printf("O maior valor dessa linha : %.2f \n", maior);
}

float Maior(float array[][2], int x)
{

    float maior = 0;
    for (int i = 0; i < NC1; i++)
    {

        if (array[x][i] > maior)
        {

            maior = array[x][i];
        }
    }
    return maior;
}

/***************************************************************
 * Assinatura da fun��o:
 * Data da elabora��o: 20/05
 * Autor: Thiago Oliveira
 * Objetivo: maior valor da linha selecionada
 * Dados de entrada (argumentos): array e a linha
 * Dado de sa�da (valor gerado pela fun��o): maior valor
 ****************************************************************
 */
