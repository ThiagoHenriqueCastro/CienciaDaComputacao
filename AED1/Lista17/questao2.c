#include <stdio.h>
#include <stdlib.h>
const int max = 4;
void escrevamatriz(int quantidade);
int lematriz(float array1[][2], float array2[][2]);
int main()
{

    float A[][2] = {{5, 2}, {8, 8}};
    float B[][2] = {{5, 2}, {8, 8}};
    int quantidade = lematriz(A, B);
    escrevamatriz(quantidade);
}
int lematriz(float array1[][2], float array2[][2])
{
    int quantidade = 0;
    for (int i = 0; i < 2; i++)
    {

        for (int j = 0; j < 2; j++)
        {

            if (array1[i][j] == array2[i][j])
            {
                quantidade++;
            }
        }
    }
    return quantidade;
}
void escrevamatriz(int quantidade)
{

    if (quantidade == max)
    {
        printf("Iguais\n");
    }
    else
    {
        printf("Diferentes\n");
    }
}
/***************************************************************
 * Assinatura da fun��o:
 * Data da elabora��o: 20/05
 * Autor:Thiago Oliveira
 * Objetivo: testa se vetores sao iguais
 * Dados de entrada (argumentos): os vetores
 * Dado de sa�da (valor gerado pela fun��o): quantidade de elementos iguais
 ****************************************************************
 */
