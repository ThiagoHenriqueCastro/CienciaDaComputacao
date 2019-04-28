#include <stdlib.h>
#include <stdio.h>
int somaAte(int inferior, int superior);
int somatorio = 0;
int main()
{
    int soma = somaAte(1, 4);
    printf("%i \n", soma);
}
int somaAte(int inferior, int superior)
{
    if (inferior <= superior)
    {
        somaAte(inferior + 1, superior);
        somatorio += inferior;
    }
    return somatorio;
}