#include <stdlib.h>
#include <stdio.h>
const int MAX = 6;
void inverte(int numeros[]);
void printArray(int numeros[]);

int main()
{
    int numeros[MAX] = {1, 2, 3, 4, 5, 6};
    inverte(numeros);
    printArray(numeros);
}

void inverte(int numeros[])
{
    int aux = numeros[MAX - 1];
    numeros[MAX - 1] = numeros[0];
    numeros[0] = aux;
}
void printArray(int numeros[])
{
    for (int i = 0; i < MAX; i++)
    {
        printf("%i\n", numeros[i]);
    }
}