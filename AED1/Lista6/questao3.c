#include <stdio.h>
#include <stdlib.h>

int main()
{
    int superior, inferior;

    printf("Digite o limite superior\n");
    scanf("%d", &superior);
    printf("Digite o limite inferior\n");
    scanf("%d", &inferior);
    printf("\n");

    while (superior >= inferior)
    {
        printf("%d \n", superior);
        superior--;
    }
}