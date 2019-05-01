#include <stdlib.h>
#include <stdio.h>
void lerReal(float *num);
void calcMaior(float *num1, float *num2, float *maior);
void printMaior(float *maior);
int main()
{
    float num1, num2, maior;
    lerReal(&num1);
    lerReal(&num2);
    calcMaior(&num1, &num2, &maior);
    printMaior(&maior);
}
void lerReal(float *num)
{
    printf("\nDigite um número: ");
    scanf("%f", num);
}
void calcMaior(float *num1, float *num2, float *maior)
{
    if (*num1 > *num2)
    {
        *maior = *num1;
    }
    else
    {
        *maior = *num2;
    }
}
void printMaior(float *maior)
{
    printf("\nO maior numero e: %.1f\n", *maior);
}