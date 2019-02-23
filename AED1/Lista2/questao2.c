#include <stdio.h>
#include <stdlib.h>

int main()
{
    float lado, area;
    printf("Calculo da area do quadrado\n");
    printf("Digite o lado\n");
    scanf("%f", &lado);

    area = lado * lado;

    printf("A area do quadrado descrito é %.2f\n", area);
}