#include <stdio.h>
#include <stdlib.h>

int main()
{
    int n, somaIdades;

    printf("Digite o numero de alunos\n");
    scanf("%d", &n);
    printf("\n");

    int idades[n];

    for (int i = 0; i < n; i++)
    {
        printf("Digite a idade\n");
        scanf("%d", &idades[i]);

        somaIdades += idades[i];
    }

    int media = somaIdades / n;
    printf("A media das idades é %d\n", media);
}