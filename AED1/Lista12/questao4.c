#include <stdlib.h>
#include <stdio.h>
void lerReal(float *num);
void calcMaiorSeg(float num1, float num2, float num3, float *maior);
void printMaior(float maior);
int main()
{
    float num1, num2, num3, maior;
    lerReal(&num1);
    lerReal(&num2);
    lerReal(&num3);
    calcMaiorSeg(num1, num2, num3, &maior);
    printMaior(maior);
}
void lerReal(float *num)
{
    printf("\nDigite um número: ");
    scanf("%f", num);
}
void calcMaiorSeg(float num1, float num2, float num3, float *maior)
{
    if (num1 > num2 && num1 > num3)
    {
        if (num2 > num3)
        {
            *maior = num2;
        }
        else
        {
            *maior = num3;
        }
    }
    else if (num2 > num1 && num2 > num3)
    {
        if (num1 > num3)
        {
            *maior = num1;
        }
        else
        {
            *maior = num3;
        }
    }
    else if (num3 > num1 && num3 > num2)
    {
        if (num1 > num2)
        {
            *maior = num1;
        }
        else
        {
            *maior = num2;
        }
    }
}
void printMaior(float maior)
{
    printf("\nO segundo maior numero e: %.1f\n", maior);
}