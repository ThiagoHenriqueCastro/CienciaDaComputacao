#include <stdio.h>
#include <stdlib.h>

int main()
{
    int a, b, c;
    printf("Digite o valor do primeiro numero\n");
    scanf("%d", &a);
    printf("Digite o valor do segundo numero\n");
    scanf("%d", &b);
    printf("Digite o valor do terceiro numero\n");
    scanf("%d", &c);
    if (a > b && a > c)
    {
        if (b > c)
        {
            printf("%d, %d, %d\n", a, b, c);
        }
        else
        {
            printf("%d, %d, %d\n", a, c, b);
        }
    }
    else if (b > a && b > c)
    {
        if (a > c)
        {
            printf("%d, %d, %d\n", b, a, c);
        }
        else
        {
            printf("%d, %d, %d\n", b, c, a);
        }
    }
    else if (c > a && c > b)
    {
        if (a > b)
        {
            printf("%d, %d, %d\n", c, a, b);
        }
        else
        {
            printf("%d, %d, %d\n", c, b, a);
        }
    }
}