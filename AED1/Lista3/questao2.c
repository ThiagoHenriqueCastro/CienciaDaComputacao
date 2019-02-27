#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int main()
{
    float base, expoente, resultado;
    printf("Potencia\n");
    printf("Digite o valor da base\n");
    scanf("%f", &base);
    printf("Digite o valor do expoente\n");
    scanf("%f", &expoente);
    resultado = pow(base, expoente);

    printf("O valor de %.2f elevado a %.2f é igual a %.2f\n", base, expoente, resultado);
}