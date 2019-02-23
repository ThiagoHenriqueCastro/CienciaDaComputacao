#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int main()
{
    float raio;
    printf("Calculo da area do circulo\n");
    printf("Digite o raio\n");
    scanf("%f", &raio);
    float area = M_PI * pow(raio, 2);

    printf("A area do circulo de raio %.2f é igual a %.2f\n", raio, area);
}