#include <stdio.h>
#include <stdlib.h>
int lerIdade(int array[]);
int calcMedia(int array[], int size);
int calcAcima(int array[], int size);
void printAcima(int acima);
int main()
{
    int idades[100];
    int size = lerIdade(idades);
    int acima = calcAcima(idades, size);
    printAcima(acima);
}
int lerIdade(int array[])
{
    int nota = 0;
    int i = 0;
    do
    {
        printf("\nDigite sua idade: ");
        scanf("%i", &nota);
    } while (nota > 150 | nota < 0);
    while (nota != 0)
    {
        array[i] = nota;
        i++;
        do
        {
            printf("\nDigite sua idade: ");
            scanf("%i", &nota);
        } while (nota > 150 | nota < 0);
    }
    return i;
}

int calcMedia(int array[], int size)
{

    int soma = 0;
    for (int i = 0; i < size; i++)
    {
        soma += array[i];
    }

    return (soma / size);
}

int calcAcima(int array[], int size)
{
    int media = calcMedia(array, size);
    int cont = 0;
    for (int i = 0; i < size; i++)
    {
        if (array[i] > media)
        {
            cont++;
        }
    }
    return cont;
}

void printAcima(int acima)
{
    printf("\n%i alunos com nota acima da media\n", acima);
}