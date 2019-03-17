#include <stdio.h>
#include <stdlib.h>

int main()
{
    float num1, num2, resultado;
    char operacao;
    do
    {
        printf("Digite o primeiro número\n");
        scanf("%f", &num1);
        printf("Digite o segundo número\n");
        scanf("%f", &num2);
        printf("Digite a sua operaçao\n");
        printf("+\n");
        printf("-\n");
        printf("*\n");
        printf("/\n");
        printf("Pressione 0 para sair\n");
        scanf("%s", &operacao);
        switch (operacao)
        {
        case '+':
            resultado = num1 + num2;
            printf("%.2f + %.2f = %.2f\n", num1, num2, resultado);
            break;
        case '-':
            resultado = num1 - num2;
            printf("%.2f - %.2f = %.2f\n", num1, num2, resultado);
            break;
        case '*':
            resultado = num1 * num2;
            printf("%.2f * %.2f = %.2f\n", num1, num2, resultado);
            break;
        case '/':
            resultado = num1 / num2;
            printf("%.2f / %.2f = %.2f\n", num1, num2, resultado);
            break;
        case '0':
            printf("Obrigado por utilizar este programa\n");
            break;
        default:
            printf("Código Invalido\n");
            break;
        }

    } while (operacao != '0');
}