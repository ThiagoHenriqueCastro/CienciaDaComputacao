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

boolean isUpper(char letter)
{
    boolean resp = false;
    if (letter >= 'A' && letter <= 'Z')
        resp = true;

    return resp;
}

int countUpper(char *line)
{
    int result = 0;

    for (int i = 0; i < strlen(line); i++)
    {
        if (isUpper(line[i]))
            result++;
    }

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

    for (int i = 0; i < inputIndex; i++)
    {
        printf("%i\n", countUpper(input[i]));
    }
}