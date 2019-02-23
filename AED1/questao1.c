#include <stdio.h>
#include <stdlib.h>

int main()
{
    float base = 3.5;
    float altura = 2.0;
    float perimetro = (base + altura) * 2;

    printf("O retangulo de base %.2f e altura %.2f tem uma área igual a %.2f", base, altura, perimetro);
}