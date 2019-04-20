#include <stdlib.h>
#include <stdio.h>
float maior(float a, float b);
float scanNum();
int main()
{
    float a = scanNum();
    float b = scanNum();
    float maiorNum = maior(a, b);
    printf("\nO maior número é: %f\n", maiorNum);
}
float scanNum()
{
    float x = 0;
    printf("\nDigite seu número: ");
    scanf("%f", &x);
    return x;
}
float maior(float a, float b)
{
    float maior = 0;
    if (a > b)
        maior = a;
    else
        maior = b;

    return maior;
}