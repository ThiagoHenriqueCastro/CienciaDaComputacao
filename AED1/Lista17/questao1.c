#include <stdio.h>
#include <stdlib.h>
void escreveMatriz();
void leMatriz();
int main()
{

    float A[][2] = {{5, 6}, {7, 8}};
    float B[2][2];
    leMatriz(A, B);
    escreveMatriz(B);
}
void leMatriz(float array1[2][2], float array2[][2])
{

    for (int i = 0; i < 2; i++)
    {

        for (int j = 0; j < 2; j++)
        {

            array2[i][j] = array1[i][j];
        }
    }
}
void escreveMatriz(float array[][2])
{

    for (int i = 0; i < 2; i++)
    {

        for (int j = 0; j < 2; j++)
        {

            printf("m[%i][%i]=%.2f\n", i, j, array[i][j]);
        }
    }
}
/***************************************************************
 * Assinatura da fun��o:
 * Data da elabora��o: 20/05
 * Autor: Thiago Oliveira
 * Objetivo: passar o array A para o B:
 * Dado de sa�da (valor gerado pela fun��o): array A atribuido ao B
 ****************************************************************
 */
