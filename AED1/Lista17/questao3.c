#include <stdio.h>
#include <stdlib.h>
float lematriz(float A[2][2], float x);
int main()
{

  float A[][2] = {{1, 2}, {5, 2}};
  int quantidade = lematriz(A, 2);
  printf("%i elementos iguais a esse valor\n", quantidade);
}
float lematriz(float array[2][2], float x)
{
  int quantidade = 0;

  for (int i = 0; i < 2; i++)
  {

    for (int j = 0; j < 2; j++)
    {

      if (array[i][j] == x)
      {
        quantidade++;
      }
    }
  }

  return quantidade;
}
/***************************************************************
 * Assinatura da fun��o:
 * Data da elabora��o: 20/05
 * Autor: Thiago Oliveira
 * Objetivo: quantas vezes um numero aparece no vetor
 * Dados de entrada (argumentos): o vetor e o numero
 * Dado de sa�da (valor gerado pela fun��o): quantidade
 ****************************************************************
 */
