#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#define LINE 1000
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

int isPalindromo(char *line, int i, int j)
{
    int result;
    // printf("%c %c\n", line[i], line[j]);
    if (i >= j)
        result = 0;
    else if (line[i] == line[j])
        result = 1 + isPalindromo(line, (i + 1), (j - 1));
    else
        result = 0 + isPalindromo(line, (i + 1), (j - 1));
    return result;
}

int main(int argc, char const *argv[])
{
    char input[ENTRY][LINE];
    int inputIndex = 0;

    do
    {
        fgets(input[inputIndex], LINE, stdin);
    } while (isFim(input[inputIndex++]) == false);
    inputIndex--;

    int operation = 0;
    for (int i = 0; i < inputIndex; i++)
    {
        operation = (int)((strlen(input[i]) - 1) / 2);

        isPalindromo(input[i], 0, (strlen(input[i]) - 2)) >= operation ? printf("SIM\n") : printf("NAO\n");
    }
}