#include <stdio.h>
#include <stdlib.h>

int main()
{
    int n1 = 0, n2 = 0, nota = 0, somaAprov = 0, somaReprov = 0;
    float mediaAprov = 0, mediaReprov = 0;

    do
    {
        do
        {
            printf("\nDigite sua nota final: ");
            scanf("%i", &nota);
            if (nota < -1 || nota > 100)
            {
                printf("\nDigite uma nota valida");
            }
        } while (nota < -1 || nota > 100);
        if (nota != -1)
        {
            if (nota >= 60)
            {
                somaAprov += nota;
                n1++;
            }
            else
            {
                somaReprov += nota;
                n2++;
            }
        }

    } while (nota != -1);

    mediaAprov = (double)somaAprov / (double)n1;
    mediaReprov = (double)somaReprov / (double)n2;

    printf("\nA media de notas dos aprovados e igual a: %.2f", mediaAprov);
    printf("\nA media de notas dos reprovados e igual a: %.2f", mediaReprov);
}