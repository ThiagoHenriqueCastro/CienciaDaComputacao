#include <stdio.h>
#include <stdlib.h>

int main()
{
    float nota1, nota2, final;
    printf("Digite o valor da primeira nota\n");
    scanf("%f", &nota1);
    printf("Digite o valor da segunda nota\n");
    scanf("%f", &nota2);
    final = nota1 + nota2;
    if (nota1 <= 50 && nota1 > 0 && nota2 > 0 && nota2 <= 50)
    {

        if (final >= 60)
        {

            printf("APROVADO\n");
        }
        else if (final >= 40 && final < 60)
        {

            printf("REAVALIAÇÃO\n");
        }
        else
        {

            printf("REPROVADO\n");
        }
    }
    else
    {
        printf("Notas Invalidas\n");
    }
}