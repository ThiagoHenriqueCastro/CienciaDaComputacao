#include <stdlib.h>
#include <stdio.h>
void sequencia(int inferior, int superior);
int main()
{
    sequencia(5, 10);
}
void sequencia(int inferior, int superior)
{
    if (inferior <= superior)
    {
        printf("%i ", inferior);
        sequencia(inferior + 1, superior);
        printf("%i ", inferior);
        }
}