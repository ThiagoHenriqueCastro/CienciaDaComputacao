#include <stdio.h>
#include <stdlib.h>

int main()
{
    float lado1, lado2, lado3;
    printf("Digite o valor do primeiro lado\n");
    scanf("%f", &lado1);
    printf("Digite o valor do segundo lado\n");
    scanf("%f", &lado2);
    printf("Digite o valor do terceiro lado\n");
    scanf("%f", &lado3);
    if (lado1 + lado2 > lado3 && lado1 + lado3 > lado2 && lado2 + lado3 > lado1)
    {
        if (lado1 == lado2 && lado1 == lado3)
        {
            printf("Triangulo Equilatero\n");
        }
        else if ((lado1 == lado2 && lado1 != lado3 & lado2 != lado3) || (lado2 == lado3 && lado2 != lado1 & lado3 != lado1) || (lado3 == lado1 && lado3 != lado2 & lado1 != lado2))
        {
            printf("Trian2gulo Isorceles\n");
        }
        else if (lado1 != lado2 && lado2 != lado3 && lado3 != lado1)
        {
            printf("Triangulo Escaleno\n");
        }
    }
    else
    {
        printf("Nao é um triangulo\n");
    }
}
