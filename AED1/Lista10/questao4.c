#include <stdlib.h>
#include <stdio.h>
#include <math.h>

float area(float raio);
float scanRaio();
int main()
{
    float a = scanRaio();
    float areaCirculo = area(a);
    printf("\nA area é: %f\n", areaCirculo);
}
float scanRaio()
{
    float x = 0;
    printf("\nDigite o raio: ");
    scanf("%f", &x);
    return x;
}
float area(float raio)
{
    return M_PI * pow(raio, 2);
}