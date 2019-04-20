#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>

bool primo(int n);
int scanNum();
int main()
{
    int a = scanNum();
    bool isPrimo = primo(a);
    if (isPrimo)
        printf("\nO numero é primo!\n");
    else
        printf("\nO numero não é primo!\n");
}
int scanNum()
{
    int x = 0;
    do
    {
        printf("\nDigite o numero: ");
        scanf("%i", &x);
    } while (x < 2);
    return x;
}
bool primo(int n)
{
    int divisor = 2;
    for (int i = 2; divisor <= 2 && i < n; i++)
    {
        if (n % i == 0)
            divisor++;
    }
    if (divisor > 2)
        return 0;
    else
        return 1;
}