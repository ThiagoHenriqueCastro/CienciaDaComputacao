#include <stdlib.h>
#include <stdio.h>

int main()
{
    FILE *arq;
    arq = fopen("exemplo.dat", "w");
    if (arq == NULL)
    {
        printf("Não abriu o arquivo\n");
        return 1;
    }
    fflush(arq);
    fclose(arq);
    /*---------------------------------------------*/
    arq = fopen("exemplo.dat", "w");
    fprintf(arq, "%i\n", 18);
    fflush(arq);
    fclose(arq);
    /*---------------------------------------------*/
    arq = fopen("exemplo.dat", "a");
    fprintf(arq, "%i\n", 50);
    fprintf(arq, "%i\n", 51);
    fflush(arq);
    fclose(arq);
    /*---------------------------------------------*/
    arq = fopen("exemplo.dat", "r");
    char *idade;
    char input[255];
    do
    {
        idade = fgets(input, sizeof(input), arq);
        if (idade)
        {
            printf("%s\n", input);
        }
    } while (idade);
}