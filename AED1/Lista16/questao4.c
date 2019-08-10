#include <stdlib.h>
#include <stdio.h>
const int MAX = 6;
void leArranjo(int array[]);
void ordena(int array[]);
void troca(int array[], int a, int b);
void escreveArranjo(int array[]);
int main()
{
    int A[] = {6, 5, 4, 3, 2, 1};
    int B[] = {3, 1, 6, 4, 2, 5};
    int C[6];
    leArranjo(C);
    ordena(A);
    ordena(B);
    ordena(C);
    escreveArranjo(A);
    escreveArranjo(B);
    escreveArranjo(C);
}
void leArranjo(int array[])
{
    for (int i = 0; i < 6; i++)
    {
        printf("\nDigite seu número: ");
        scanf("%i", &array[i]);
    }
}

void ordena(int array[])
{
    for (int x = 0; x < 6; x++)
    {
        for (int i = 0; i < 6; i++)
        {
            troca(array, i, (i + 1));
        }
    }
}

void troca(int array[], int a, int b)
{
    if (array[a] > array[b])
    {
        int aux = array[b];
        array[b] = array[a];
        array[a] = aux;
    }
}

void escreveArranjo(int array[])
{

    for (int i = 0; i < 6; i++)
    {
        printf("%i\n", array[i]);
    }
    printf("\n");
}
