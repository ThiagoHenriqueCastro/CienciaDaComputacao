#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int main()
{
    float a, b, c, delta, x1, x2;
    printf("Segundo grau\n");
    printf("Digite o valor de A\n");
    scanf("%f", &a);
    printf("Digite o valor de B\n");
    scanf("%f", &b);
    printf("Digite o valor de c\n");
    scanf("%f", &c);
    delta = sqrt(pow(b, 2) - 4 * a * c);
    if (delta > 0)
    {
        x1 = (-b + delta) / (2 * a);
        x2 = (-b - delta) / (2 * a);

        printf("Os valores de x são %.2f e %.2f\n", x1, x2);
    }
    else
    {
        printf("Delta negativo, sem solução real\n");
    }
}