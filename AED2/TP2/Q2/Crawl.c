#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#define LINE 1000
#define ENTRY 1000
#define HTML 10000

#define false 0
#define true 1
#define boolean short

/**
     * 
     * @param line string que devera ser verificada
     * @return true se 'FIM' for encontrado
     */
boolean isFim(char *line)
{
    boolean resp = false;
    if (strlen(line) >= 3 && line[0] == 'F' && line[1] == 'I' && line[2] == 'M')
        resp = true;

    return resp;
}

char *crawlNome(char *line)
{
    int begin = 0;
    int end = 0;
    char *output = "";
    for (int i = 0; i < strlen(line); i++)
    {
        if (line[i] == 'F' && line[i + 1] == 'u' && line[i + 2] == 'l' && line[i + 3] == 'l' && line[i + 4] == ' ' && line[i + 5] == 'n' && line[i + 6] == 'a' && line[i + 7] == 'm' && line[i + 8] == 'e')
        {
            begin = i + 9;
            i = strlen(line);
        }
    }

    for (int i = 0; i < strlen(line); i++)
    {
        if (line[i] == 'N' && line[i + 1] == 'i' && line[i + 2] == 'c' && line[i + 3] == 'k' && line[i + 4] == 'n' && line[i + 5] == 'a' && line[i + 6] == 'm' && line[i + 7] == 'e')
        {
            end = i - 1;
            i = strlen(line);
        }
    }

    int cont = 0;
    char aux[2];
    aux[1] = '\0';
    for (int i = begin; i < end; i++)
    {
        aux[0] = line[i];
        strcat(output, aux);
    }

     printf("%s", output);

    return output;
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

    FILE *arq;
    char *html = NULL;
    size_t len = 0;
    char *nome;
    for (int i = 0; i < 1; i++)
    {
        arq = fopen("Argentinos_Juniors.html", "r");
        if (arq == NULL)
        {
            perror(input[i]);
            return (-1);
        }
        int line_len;
        while (!feof(arq))
        {
            getline(&html, &len, arq);
            if (strstr(html, "Full name") != NULL)
            {
                int idx = 0;
                int opened = 0; // false
                for (i = 0; i < strlen(html); i++)
                {
                    if (html[i] == '<')
                    {
                        opened = 1; // true
                    }
                    else if (html[i] == '>')
                    {
                        opened = 0; // false
                    }
                    else if (!opened)
                    {
                        html[idx++] = html[i];
                    }
                }
                html[idx] = '\0';

                nome = crawlNome(html);

                printf("%s" , nome);
            }
        }
    }
}