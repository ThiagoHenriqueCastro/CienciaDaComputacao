#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>

bool bissexto(int ano);
int scanAno();
int main()
{
    int a = scanAno();
    bool isBissexto = bissexto(a);
    if (isBissexto)
        printf("\nO ano é bissexto!\n");
    else
        printf("\nO ano não é bissexto!\n");
}
int scanAno()
{
    int x = 0;
    printf("\nDigite o ano: ");
    scanf("%i", &x);
    return x;
}
bool bissexto(int ano)
{
    if (ano % 4 == 0 && ano % 100 != 0)
    {
        return 1;
    }
    else if (ano % 4 == 0 && ano % 100 == 0 && ano % 400 == 0)
    {
        return 1;
    }
    else
    {
        return 0;
    }
}