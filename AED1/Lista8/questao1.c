#include <stdlib.h>
#include <stdio.h>

int main()
{
    float base = 0, potencia = 1;
    int expo = 0;

    printf("Digite a base: ");
    scanf("%f", &base);
    printf("\nDigite o expoente: ");
    scanf("%i", &expo);

    for (int i = 0; i < expo; i++)
    {
        potencia *= base;
    }

    printf("A potencia é: %.2f\n", potencia);
}