#include <stdio.h>
#include <stdlib.h>

int main()
{
    char operacao;
    float a, b, resultado;
    printf("Digite o primeiro numero\n");
    scanf("%f", &a);
    printf("Digite o segundo numero\n");
    scanf("%f", &b);
    printf("Digite o operador\n");
    printf("+\n");
    printf("-\n");
    printf("/\n");
    printf("*\n");
    printf("\n");
    scanf(" %c", &operacao);
    if (operacao == '+')
    {
        printf("Soma\n");
        float resultado = a + b;
        printf("%.2f + %.2f = %.2f\n", a, b, resultado);
    }
    else if (operacao == '-')
    {
        printf("Subtracao\n");
        float resultado = a - b;
        printf("%.2f - %.2f = %.2f\n", a, b, resultado);
    }
    else if (operacao == '/')
    {
        printf("Divisao\n");
        if (b != 0)
        {
            float resultado = a / b;
            printf("%.2f / %.2f = %.2f\n", a, b, resultado);
        }
        else
        {
            printf("Nao existe divisao por 0\n");
        }
    }
    else if (operacao == '*')
    {
        printf("Multiplicacao\n");
        float resultado = a * b;
        printf("%.2f * %.2f = %.2f\n", a, b, resultado);
    }
    else
    {
        printf("Operador invalido\n");
    }
}