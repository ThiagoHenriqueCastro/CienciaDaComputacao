#include <stdlib.h>
#include <stdio.h>
const int MAX = 6;
void inverte(int numeros[], int m, int n);
void printArray(int numeros[]);

int main()
{
    int numeros[MAX] = {1, 2, 3, 4, 5, 6};
    inverte(numeros, 3, 6);
    printArray(numeros);
}

void inverte(int numeros[], int m, int n)
{
    int aux = numeros[n - 1];
    numeros[n - 1] = numeros[m - 1];
    numeros[m - 1] = aux;
}
void printArray(int numeros[])
{
    for (int i = 0; i < MAX; i++)
    {
        printf("%i\n", numeros[i]);
    }
}