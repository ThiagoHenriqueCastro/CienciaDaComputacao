#include <stdlib.h>
#include <stdio.h>
float maior(float x);
float scanNum();
float maiorNumero = INT32_MIN;
int main()
{
    float maiorNum = scanNum();
    printf("\nO maior número é: %f\n", maiorNum);
}
float scanNum()
{
    float x;
    float maiorNum;
    do
    {
        printf("\nDigite seu número: ");
        scanf("%f", &x);
        if (x != 0)
            maiorNum = maior(x);
    } while (x != 0);

    return maiorNum;
}
float maior(float x)
{
    if (x > maiorNumero)
        maiorNumero = x;
    return maiorNumero;
}