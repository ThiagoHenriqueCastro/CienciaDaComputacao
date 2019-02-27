#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int main()
{
    float x1, y1, x2, y2, distancia;
    printf("Pontos\n");
    printf("Digite o valor de X1\n");
    scanf("%f", &x1);
    printf("Digite o valor de Y1\n");
    scanf("%f", &y1);
    printf("Digite o valor de X2\n");
    scanf("%f", &x2);
    printf("Digite o valor de Y2\n");
    scanf("%f", &y2);
    distancia = sqrt(pow((x2 - x1), 2) + pow((y2 - y1), 2));

    printf("O distancia é igual a %.2f\n", distancia);
}