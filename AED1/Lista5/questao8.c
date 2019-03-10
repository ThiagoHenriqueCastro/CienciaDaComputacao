#include <stdio.h>
#include <stdlib.h>

int main()
{
    float massa, peso_planeta;
    int planeta;
    printf("Digite sua massa na Terra\n");
    scanf("%f", &massa);
    printf("Digite o codigo do planeta\n");
    printf("1 - Mercúrio\n");
    printf("2 - Vênus\n");
    printf("3 - Marte\n");
    printf("4 - Júpiter\n");
    printf("5 - Saturno\n");
    printf("6 - Urano\n");
    scanf("%d", &planeta);
    switch (planeta)
    {
    case 1:
        peso_planeta = massa * 0.37;
        printf("Mercurio -> Peso = %.2f\n", peso_planeta);
        break;
    case 2:
        peso_planeta = massa * 0.88;
        printf("Vênus -> Peso = %.2f\n", peso_planeta);
        break;
    case 3:
        peso_planeta = massa * 0.38;
        printf("Marte -> Peso = %.2f\n", peso_planeta);
        break;
    case 4:
        peso_planeta = massa * 2.64;
        printf("Júpiter -> Peso = %.2f\n", peso_planeta);
        break;
    case 5:
        peso_planeta = massa * 1.15;
        printf("Saturno -> Peso = %.2f\n", peso_planeta);
        break;
    case 6:
        peso_planeta = massa * 1.17;
        printf("Urano -> Peso = %.2f\n", peso_planeta);
        break;
    default:
        printf("Código Invalido\n");
        break;
    }
}