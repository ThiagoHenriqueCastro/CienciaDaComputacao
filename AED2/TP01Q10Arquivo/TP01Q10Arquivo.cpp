/**
 * TP01Q10 ARQUIVO
 * 
 * @author Thiago Henrique de Castro Oliveira
 * @version 1 08/2019 Algoritmo que repassa dadosda stdin para um arquivo e o repassa a stdout inversamente.
 */

#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#define ENTRY 1000

#define false 0
#define true 1
#define boolean short

int main()
{
    int size;
    scanf("%i", &size); // le o numero de elementos a serem lidos

    FILE *arq = fopen("file_c.in", "w"); // abre o aquivo para a escrita dos dados da entrada padrao

    float num;
    for (int i = 0; i < size; i++)
    {
        scanf("%f", &num);
        fwrite(&num, sizeof(double), 1, arq); // escreve no arquivo o double com os 8 bytes do tipo basico
    }

    fclose(arq); // fecha o arquivo

    arq = fopen("file_c.in", "r"); // abre o arquivo para leitura apenas
    size = (size * 8) * (-1);      // pega o numero total de bytes do arquivo, numero de elementos * 8
    int begin = -8;

    // de oito em oite bytes, le linha por linha do arquivo ate chegar ao inicio do mesmo
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

    fclose(arq); // fecha o arquivo
}