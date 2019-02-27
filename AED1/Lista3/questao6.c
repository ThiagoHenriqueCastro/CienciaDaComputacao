#include <stdio.h>
#include <stdlib.h>

int main()
{
    float idade, dias;
    printf("Dias\n");
    printf("Digite sua idade\n");
    scanf("%f", &idade);
    dias = idade * 365;

    printf("O número de dias vividos é igual a %.2f\n", dias);
}