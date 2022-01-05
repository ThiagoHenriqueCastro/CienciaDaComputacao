#include <math.h>
#include <stdio.h>
#include <stdlib.h>
int main()
{

    float salariominimo = 0, valorquilowatt = 0,
          valorfinalconsumidor = 0, faturamentogeral = 0;
    int tipoconsumidor, quantidade, quantconsumidoresentre = 0;

    printf("Digite o valor do salario minimo atual:\n ");
    scanf("%f", &salariominimo);
    valorquilowatt = (1 * salariominimo) / 10;
    printf("O valor de cada quiilowatt esta sendo: %.2f \n ", valorquilowatt);
    printf("\n");

    do
    {
        printf("\n");
        printf("Digite a quantidade de quiilowatt gasta por esse consumidor:\n ");
        scanf("%i", &quantidade);
        if (quantidade != 0)
        {

            do
            {
                printf("\nDigite o tipo de consumidor:\n ");
                scanf("%d", &tipoconsumidor);
                if (tipoconsumidor != 1 && tipoconsumidor != 2 && tipoconsumidor != 3)
                    printf("\nDigite um tipo valido de consumidor!\n ");
            } while (tipoconsumidor != 1 && tipoconsumidor != 2 && tipoconsumidor != 3);

            if (tipoconsumidor == 1)
            {
                valorfinalconsumidor = (quantidade * valorquilowatt) * 1.1;
                printf("O valor final do cliente eh: %.2f \n ", valorfinalconsumidor);
            }
            else if (tipoconsumidor == 2)
            {
                valorfinalconsumidor = (quantidade * valorquilowatt) * 1.15;
                printf("O valor final do cliente eh: %.2f \n ", valorfinalconsumidor);
            }
            else if (tipoconsumidor == 3)
            {
                valorfinalconsumidor = (quantidade * valorquilowatt) * 1.2;
                printf("O valor final do cliente eh: %.2f \n ", valorfinalconsumidor);
            }

            if (valorfinalconsumidor > 1000 && valorfinalconsumidor < 1500)
                quantconsumidoresentre++;

            faturamentogeral += valorfinalconsumidor;
        }

    } while (quantidade != 0);
    printf("\n");
    printf("Faturamento geral da empresa: %.2f \n ", faturamentogeral);
    printf("%d consumidores pagarao entre R$1000 e R$1500 reais \n ", quantconsumidoresentre);
    return 0;
}
