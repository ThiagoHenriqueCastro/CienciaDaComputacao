#include <stdio.h>
#include <stdlib.h>

void preencheArray(int *inteiros);
int contPar(int inteiros[]);

int main()
{
    int inteiros[10];
    preencheArray(inteiros);
    int pares = contPar(inteiros);
    printf("\nExistem %i numeros pares no array\n", pares);
}

void preencheArray(int *inteiros)
{
    for (int i = 0; i < 10; i++)
    {
        printf("\nDigite seu número: ");
        scanf("%i", &inteiros[i]);
    }
}

int contPar(int inteiros[])
{
    int pares = 0;
    for (int i = 0; i < 10; i++)
    {
        if (inteiros[i] % 2 == 0)
        {
            pares++;
        }
    }
    return pares;
}