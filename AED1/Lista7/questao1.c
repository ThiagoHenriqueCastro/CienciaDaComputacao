#include <stdlib.h>
#include <stdio.h>

int main()
{

    float h, denominador = 100;
    int n;
    printf("Digite o numero de termos da progressao:\n");
    scanf("%d", &n);
    for (int i = 0; i < n; i++)
    {
        printf("1/%.0f\n", denominador);
        h += 1 / denominador;
        denominador -= 3;
    }

    printf("%.2f\n", h);
}