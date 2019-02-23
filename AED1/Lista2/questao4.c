#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int main()
{
    float raio;
    printf("Calculo do perimetro do circulo\n");
    printf("Digite o raio\n");
    scanf("%f", &raio);
    float perimetro = 2 * M_PI * raio;

    printf("O perimetro do circulo de raio %.2f é igual a %.2f\n", raio, perimetro);
}