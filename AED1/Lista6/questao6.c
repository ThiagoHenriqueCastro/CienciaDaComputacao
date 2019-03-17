#include <stdio.h>
#include <stdlib.h>

int main()
{
    int n, r, termo;

    printf("Digite o numero de termos\n");
    scanf("%d", &n);
    printf("Digite a razao\n");
    scanf("%d", &r);
    printf("Digite o primeiro termo\n");
    scanf("%d", &termo);
    printf("\n");

    for (int i = 0; i < n; i++)
    {
        printf("%d\n", termo);

        termo += r;
    }
}