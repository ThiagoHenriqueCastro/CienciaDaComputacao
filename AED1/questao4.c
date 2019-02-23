#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int main()
{
    float raio = 1.0;
    float area = M_PI * pow(raio, 2);

    printf("A area do circulo de raio %.2f é igual a %.2f", raio, area);
}