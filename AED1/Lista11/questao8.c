#include <stdlib.h>
#include <stdio.h>
#include <math.h>
int calPotencia(int base, int expoente);
int potencia = 1;
int main()
{
    int potencia = calPotencia(3, 4);
    printf("%i \n", potencia);
}
int calPotencia(int base, int expoente)
{
    if (expoente > 0)
    {
        potencia *= base;
        calPotencia(base, expoente - 1);
    }
    return potencia;
    
}