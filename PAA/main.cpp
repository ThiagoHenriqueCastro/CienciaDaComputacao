#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int main()
{

    double dist, ang, escada, altura;
    long double radian, cosseno;

    do
    {

        printf("Digite a medida do angulo: ");
        scanf("%lf", &ang);
        printf("Digite a medida da escada até a parede: ");
        scanf("%lf", &dist);

        // Converter de grau para radianos
        radian = ang * (M_PI / 180);

        // Cosseno do angulo
        cosseno = cos(radian);

        // Comprimento da escada
        escada = dist / cosseno;

        // Altura da escada
        altura = sqrt(pow(escada, 2) - pow(dist, 2));

        if (!(altura + dist > escada && dist + escada > altura && altura + escada > dist))
            printf("Triangulo inválido!\n");
        else
            printf("Medida da escada: %.2lf\n", escada);

    } while (!(altura + dist > escada && dist + escada > altura && altura + escada > dist));
}