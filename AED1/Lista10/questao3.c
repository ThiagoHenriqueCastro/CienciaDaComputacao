#include <stdlib.h>
#include <stdio.h>
float perimetro(float lado);
float scanLado();
int main()
{
    float lado = scanLado();
    float perimetroNum = perimetro(lado);
    printf("\nO perimetro é: %.2f\n", perimetroNum);
}
float scanLado()
{
    float x = 0;
    printf("\nDigite o valor do lado: ");
    scanf("%f", &x);
    return x;
}
float perimetro(float lado)
{
    return lado * 4;
}