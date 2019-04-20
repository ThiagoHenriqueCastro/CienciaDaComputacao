#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>

float potencia(float base, int expoente);
float scanNum();
int scanExpoente();
int main()
{
    float base = scanNum();
    int expoente = scanExpoente();
    float valorPotencia = potencia(base, expoente);
    printf("\nA potencia é: %.2f\n", valorPotencia);
}
float scanNum()
{
    float x = 0;
    printf("\nDigite o numero: ");
    scanf("%f", &x);
    return x;
}
int scanExpoente()
{
    int x = 0;
    printf("\nDigite o expoente: ");
    scanf("%i", &x);
    return x;
}
float potencia(float base, int expoente)
{
    float potencia  = 1;
    for (int i = 0; i < expoente; i++)
    {
        potencia *= base;
    }
    return potencia;
}