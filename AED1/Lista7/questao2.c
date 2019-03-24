#include <stdlib.h>
#include <stdio.h>
#include <math.h>

int main()
{

    int a = 1, b = 100, n;
    printf("Digite o numero de termos da progressao:\n");
    scanf("%d", &n);
    for (int i = 0; i < n; i++)
    {
        if (i % 2 == 0)
        {
            printf("%.0d/%.0d\n", a, b);
            a++;
            b -= 3;
        }
        else
        {
            printf("%.0d/%.0d\n", b, a);
            a++;
            b -= 3;
        }
    }
}