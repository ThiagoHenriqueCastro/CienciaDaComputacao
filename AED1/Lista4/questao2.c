#include <stdio.h>
#include <stdlib.h>

int main()
{
    int a;
    printf("Digite o valor do numero\n");
    scanf("%d", &a);
    if (a % 2 == 0)
    {
        printf("O numero é par \n");
    }
    else
    {
        printf("O numero é impar \n");
    }
}