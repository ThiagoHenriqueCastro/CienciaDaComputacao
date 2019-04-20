#include <stdlib.h>
#include <stdio.h>
float soma(float a, float b);
float scanNum();
int main()
{
    float a = scanNum();
    float b = scanNum();
    float somaNum = soma(a, b);
    printf("\nA soma é: %.2f\n", somaNum);
}
float scanNum()
{
    float x = 0;
    printf("\nDigite seu número: ");
    scanf("%f", &x);
    return x;
}
float soma(float a, float b)
{
    return a + b;
}