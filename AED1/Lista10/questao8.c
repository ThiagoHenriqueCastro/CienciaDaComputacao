#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>

float peso(float altura);
float scanAltura();
int main()
{
    float a = scanAltura();
    float pesoIdeal = peso(a);
    printf("\nO peso ideal é: %.2f!\n", pesoIdeal);
}
float scanAltura()
{
    float x = 0;
    printf("\nDigite sua altura: ");
    scanf("%f", &x);
    return x;
}
float peso(float altura)
{
    return (72.7 * altura) - 58;
}