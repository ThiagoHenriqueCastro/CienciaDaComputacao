#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#define ENTRY 1000

#define false 0
#define true 1
#define boolean short

boolean isFim(char *line)
{
    boolean resp = false;
    if (strlen(line) >= 3 && line[0] == 'F' && line[1] == 'I' && line[2] == 'M')
        resp = true;

    return resp;
}

int main(int argc, char const *argv[])
{
    int size;
    scanf("%i", &size);

    FILE *arq = fopen("file_c.in", "w");

    float num;
    for (int i = 0; i < size; i++)
    {
        scanf("%f", &num);
        fwrite(&num, sizeof(double), 1, arq);
    }

    fclose(arq);

    arq = fopen("file_c.in", "r");
    size = (size * 8) * (-1);
    int begin = -8;

    while (begin >= size)
    {
        fseek(arq, begin, SEEK_END);
        fread(&num, sizeof(double), 1, arq);
        if (num - (int)num == 0)
            printf("%d\n", (int)num);
        else
            printf("%g \n", num);
        begin -= 8;
    }

    fclose(arq);
}