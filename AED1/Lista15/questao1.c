#include <stdlib.h>
#include <stdio.h>

int contPar(FILE *arq);

int main()
{
    FILE *arq = fopen("inteiros.dat", "r");
    int pares = contPar(arq);
    printf("O arquivo possui %i numeros pares\n", pares);
    fclose(arq);
}

int contPar(FILE *arq)
{
    int pares = 0, x;
    while (!feof(arq))
    {
        fscanf(arq, "%i", &x);
        if (x % 2 == 0)
        {
            pares++;
        }
    }
    return pares;
}