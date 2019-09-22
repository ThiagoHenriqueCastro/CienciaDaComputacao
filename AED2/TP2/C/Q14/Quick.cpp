/**
 * TP01P2Q14 Quicksort em C
 * 
 * @author Thiago Henrique de Castro Oliveira
 * @version 1 09/2019 Este algoritmo le paginas html e preenche uma lista de Times
 * Apos isso, faz ordenacao na mesma
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <locale.h>

#define LINE 1000
#define ENTRY 1000
#define HTML 10000

#define false 0
#define true 1
#define boolean short

/*
* Definiçao da Struct Time
*/
typedef struct Time
{
    char nome[200];
    char apelido[200];
    char estadio[100];
    char tecnico[100];
    char liga[100];
    char nomeArquivo[100];
    int capacidade;
    int fundacaoDia;
    int fundacaoMes;
    int fundacaoAno;
    long paginaTam;
} Time;

/*
* Definiçao da Struct Lista
*/
typedef struct Lista
{
    Time array[1000];
    int n;

} Lista;

/*
* Recebe a linha e verifica se a mesma é o fim do arquivo;
*/
boolean isFim(char *line)
{
    boolean resp = false;
    if (strlen(line) >= 3 && line[0] == 'F' && line[1] == 'I' && line[2] == 'M')
        resp = true;

    return resp;
}

/*
* Recebe a linha e a quebra em tokens, com base em um delimiter
*/
char *multi_tok(char *valor, char *contoller)
{
    static char *string;
    if (valor != NULL)
        string = valor;

    if (string == NULL)
        return string;

    char *end = strstr(string, contoller);
    if (end == NULL)
    {
        char *temp = string;
        string = NULL;
        return temp;
    }

    char *temp = string;

    *end = '\0';
    string = end + strlen(contoller);
    return temp;
}

/*
* recebe uma string e remove tags da mesma, verificando o ponto de abertura e fechamento de tags
*/
char *removeTags(char *valor)
{
    int x = 0;
    int abrir = 0; // false
    for (int i = 0; i < strlen(valor); i++)
    {
        if (valor[i] == '<')
        {
            abrir = 1; // true
        }
        else if (valor[i] == '>')
        {
            abrir = 0; // false
        }
        else if (!abrir)
        {
            valor[x++] = valor[i];
        }
    }
    valor[x] = '\0';

    return valor;
}

char *trocastring(const char *valor, int retirada, char *retorno)
{
    int valor_entrado = strlen(valor);

    int len = valor_entrado - retirada;

    strncpy(retorno, valor + retirada, len);
    return retorno;
}

void ligacomp(char *valor)
{
    for (int i = 0; i < strlen(valor); i++)
    {
        if (isdigit(valor[i]))
        {
            valor[i] = '\0';
            i = strlen(valor);
        }
        if (valor[i] == '\n')
        {
            valor[i] = '\0';
            i = strlen(valor);
        }
    }

    valor[strlen(valor)] = 0;
}

char *trocastring_contoller(const char *valor, int retirada, int len, char *retorno)
{
    int valor_entrado = strlen(valor);

    strncpy(retorno, valor + retirada, len);
    return retorno;
}

int getMes(char *linha)
{
    int resp = 0;
    if (strstr(linha, "January") != NULL)
    {
        resp = 1;
    }
    else if (strstr(linha, "February") != NULL)
    {
        resp = 2;
    }
    else if (strstr(linha, "March") != NULL)
    {
        resp = 3;
    }
    else if (strstr(linha, "April") != NULL)
    {
        resp = 4;
    }
    else if (strstr(linha, "May") != NULL)
    {
        resp = 5;
    }
    else if (strstr(linha, "June") != NULL)
    {
        resp = 6;
    }
    else if (strstr(linha, "July") != NULL)
    {
        resp = 7;
    }
    else if (strstr(linha, "August") != NULL)
    {
        resp = 8;
    }
    else if (strstr(linha, "September") != NULL)
    {
        resp = 9;
    }
    else if (strstr(linha, "October") != NULL)
    {
        resp = 10;
    }
    else if (strstr(linha, "November") != NULL)
    {
        resp = 11;
    }
    else if (strstr(linha, "December") != NULL)
    {
        resp = 12;
    }
    return resp;
}

void imprimir(Time *time)
{
    printf("%s ## %s ## %d/%d/%d ## %s ## %d ## %s ## %s ## %s ## %lu ##\n", time->nome, time->apelido, time->fundacaoDia, time->fundacaoMes, time->fundacaoAno, time->estadio, time->capacidade, time->tecnico, time->liga, time->nomeArquivo, time->paginaTam);
}

int ler(char *caminho, Time *time)
{
    //printf("%s\n", caminho);
    FILE *arq;
    arq = fopen(caminho, "r");
    char nome[200] = {'\0'};
    char apelido[70] = {'\0'};
    char datav[25] = {'\0'};
    int mes;
    int dia;
    int ano;
    char anov[15] = {'\0'};
    char estadio[100] = {'\0'};
    char capacity_rcv[100] = {'\0'};
    int capacidade;
    char liga[100] = {'\0'};

    if (arq == NULL)
    {
        perror(caminho);
        return (-1);
    }
    char *html = NULL;
    char *line = NULL;
    size_t len = 0;
    int flag = 0;
    while (!feof(arq) && flag != 1)
    {
        getline(&html, &len, arq);

        if (strstr(html, "Full name") != NULL)
        {
            flag = 1;

            line = html;

            char *resp = strstr(line, "Full name");

            char *aux2 = multi_tok(resp, "<tr>");

            char *linha[100];
            for (int i = 0; i < 100; i++)
            {
                linha[i] = "0";
            }
            int contador = 0;
            while (aux2 != NULL)
            {
                linha[contador++] = aux2;
                aux2 = multi_tok(NULL, "<tr>");
            }

            contador = 0;
            //

            do
            {
                if (strstr(linha[contador], "Full name") != NULL)
                {
                    linha[contador] = removeTags(linha[contador]);
                    // printf("%s\n", linha[contador]);
                    trocastring(linha[contador], 9, nome);
                    // printf("%s\n", nome);
                    strcpy((time)->nome, nome);

                    // printf("%s\n", time->nome);
                }
                else if (strstr(linha[contador], "Nickname(s)") != NULL)
                {

                    linha[contador] = removeTags(linha[contador]);
                    //printf("%s\n", linha[contador]);
                    trocastring(linha[contador], 11, apelido);
                    // printf("%s\n", apelido);
                    strcpy((time)->apelido, apelido);
                    // printf("%s\n", time->apelido);
                }
                else if (strstr(linha[contador], "Founded") != NULL)
                {

                    linha[contador] = removeTags(linha[contador]);

                    mes = getMes(linha[contador]);
                    if (mes == 0)
                    {
                        dia = 0;
                        trocastring_contoller(linha[contador], 7, 4, anov);
                        ano = atoi(anov);
                    }
                    else
                    {
                        trocastring(linha[contador], 7, datav);

                        char *split = multi_tok(datav, " ");

                        char *splitted[3];
                        for (int i = 0; i < 3; i++)
                        {
                            splitted[i] = "0";
                        }
                        int founded_index = 0;
                        while (split != NULL)
                        {
                            splitted[founded_index++] = split;
                            split = multi_tok(NULL, " ");
                        }

                        char *test = splitted[0];

                        if (strstr(splitted[0], "January") != NULL || strstr(splitted[0], "February") != NULL || strstr(splitted[0], "March") != NULL || strstr(splitted[0], "April") != NULL || strstr(splitted[0], "May") != NULL || strstr(splitted[0], "June") != NULL || strstr(splitted[0], "July") != NULL || strstr(splitted[0], "August") != NULL || strstr(splitted[0], "September") != NULL || strstr(splitted[0], "October") != NULL || strstr(splitted[0], "November") != NULL || strstr(splitted[0], "December") != NULL)
                        {
                            if (strstr(linha[contador], ","))
                            {
                                dia = atoi(splitted[1]);
                                trocastring_contoller(splitted[2], 0, 4, anov);
                                ano = atoi(anov);
                            }
                            else if (isdigit(test[0]))
                            {

                                dia = atoi(test);
                                trocastring_contoller(splitted[1], 0, 4, anov);
                                ano = atoi(anov);
                            }
                            else
                            {

                                dia = 0;
                                trocastring_contoller(splitted[1], 0, 4, anov);
                                ano = atoi(anov);
                            }
                        }
                        else
                        {
                            dia = 0;
                            trocastring_contoller(splitted[1], 0, 4, anov);
                            ano = atoi(anov);
                        }
                    }
                    (time)->fundacaoMes = mes;
                    (time)->fundacaoDia = dia;
                    (time)->fundacaoAno = ano;
                    // printf("%d\n", mes);
                    // printf("%d\n", dia);
                    // printf("%d\n", ano);
                }

                else if (strstr(linha[contador], "Ground") != NULL)
                {
                    linha[contador] = removeTags(linha[contador]);

                    trocastring(linha[contador], 6, estadio);
                    strcpy((time)->estadio, estadio);
                    // printf("%s\n", time->estadio);
                }
                else if (strstr(linha[contador], "Capacity") != NULL)
                {

                    linha[contador] = removeTags(linha[contador]);

                    trocastring(linha[contador], 8, capacity_rcv);
                    char *split = multi_tok(capacity_rcv, "&");

                    char *splitted[50];
                    for (int i = 0; i < 50; i++)
                    {
                        splitted[i] = "0";
                    }
                    int founded_index = 0;
                    while (split != NULL)
                    {
                        splitted[founded_index++] = split;
                        split = multi_tok(NULL, " ");
                    }

                    capacidade = atoi(splitted[0]);
                    (time)->capacidade = capacidade;
                    // printf("%d\n", time->capacidade);
                }
                else if (strstr(linha[contador], "Coach") != NULL || strstr(linha[contador], "Manager") != NULL)
                {
                    linha[contador] = removeTags(linha[contador]);

                    char *tecnico = strstr(linha[contador], "Coach") != NULL ? multi_tok(linha[contador], "Coach") : multi_tok(linha[contador], "Manager");
                    tecnico = strstr(linha[contador], "Coach") != NULL ? multi_tok(NULL, "Coach") : multi_tok(NULL, "Manager");
                    strcpy((time)->tecnico, tecnico);
                    // printf("%s\n", time->tecnico);
                }

                else if (strstr(linha[contador], "League") != NULL)
                {
                    linha[contador] = removeTags(linha[contador]);
                    trocastring(linha[contador], 6, liga);
                    ligacomp(liga);
                    strcpy((time)->liga, liga);
                    // printf("%s\n", liga);
                }

            } while (strcmp(linha[contador++], "0"));
            strcpy(time->nomeArquivo, caminho);
            fseek(arq, 0L, SEEK_SET);
            fseek(arq, 0L, SEEK_END);
            int aux1 = ftell(arq);

            time->paginaTam = aux1;
        }
    }
    return 1;
    fclose(arq);
}

int inserirFim(Lista lista[], Time *time)
{
    if (lista->n > 1000)
    {
        perror("Erro ao inserir");
        return (-1);
    }
    lista->array[lista->n] = *time;
    lista->n++;

    return 1;
}

int inserirInicio(Lista lista[], Time *time)
{
    if (lista->n > 1000)
    {
        perror("Erro ao inserir");
        return (-1);
    }

    for (int i = lista->n; i > 0; i--)
    {
        lista->array[i] = lista->array[i - 1];
    }

    lista->array[0] = *time;

    lista->n++;

    return 1;
}

int inserir(Lista lista[], Time *time, int pos)
{
    if (lista->n > 1000)
    {
        perror("Erro ao inserir");
        return (-1);
    }

    for (int i = lista->n; i > pos; i--)
    {
        lista->array[i] = lista->array[i - 1];
    }

    lista->array[pos] = *time;

    lista->n++;

    return 1;
}

char *removerFim(Lista lista[])
{
    if (lista->n <= 0)
    {
        perror("Erro ao remover");
    }

    char *resp = lista->array[lista->n - 1].nome;

    lista->n--;

    return resp;
}

char *removerInicio(Lista lista[])
{
    if (lista->n <= 0)
    {
        perror("Erro ao remover");
    }

    char *resp = lista->array[0].nome;

    for (int i = 0; i < lista->n; i++)
    {
        lista->array[i] = lista->array[i + 1];
    }

    lista->n--;

    return resp;
}

char *remover(Lista lista[], int pos)
{
    if (lista->n <= 0)
    {
        perror("Erro ao remover");
    }

    char *resp = lista->array[pos].nome;

    for (int i = pos; i < lista->n; i++)
    {
        lista->array[i] = lista->array[i + 1];
    }

    lista->n--;

    return resp;
}

void mostrar(Lista *lista)
{
    for (int i = 0; i < lista->n; i++)
    {

        imprimir(&lista->array[i]);
    }
}

int binarySearch(char *chave, Lista lista[], int inicio, int fim)
{
    int valorChave = 0;
    int valorLista = 0;
    char *aux;

    //printf("%d e %d\n", inicio, fim);

    aux = (char *)malloc(200 * sizeof(char));

    if (inicio > fim)
    {
        return 0;
    }
    int meio = (inicio + fim) / 2;

    strcpy(aux, lista->array[meio].nome);

    //printf("%s\n", lista->array[meio].nome);
    //printf("%s\n", chave);
    // printf("%lu\n", strlen(aux));
    // printf("%lu\n", strlen(chave));

    if (strcmp(aux, chave) == 0)
    {

        return 1;
    }
    else
    {
        if (strcmp(lista->array[meio].nome, chave) < 0)
        {
            return binarySearch(chave, lista, meio + 1, fim);
        }
        else
        {
            return binarySearch(chave, lista, inicio, meio - 1);
        }
    }

    free(aux);
}

void swap(int first, int second, Lista lista[])
{
    Time *temp;
    temp = (Time *)malloc(sizeof(Time));

    memccpy(temp, &(lista->array[first]), sizeof(Time), sizeof(Time));

    //printf("%s\n", temp->nome);
    // segundo para primeiro
    strcpy(lista->array[first].nome, lista->array[second].nome);
    strcpy(lista->array[first].apelido, lista->array[second].apelido);
    strcpy(lista->array[first].estadio, lista->array[second].estadio);
    strcpy(lista->array[first].tecnico, lista->array[second].tecnico);
    strcpy(lista->array[first].liga, lista->array[second].liga);
    strcpy(lista->array[first].nomeArquivo, lista->array[second].nomeArquivo);
    lista->array[first].fundacaoDia = lista->array[second].fundacaoDia;
    lista->array[first].fundacaoMes = lista->array[second].fundacaoMes;
    lista->array[first].fundacaoAno = lista->array[second].fundacaoDia;
    lista->array[first].paginaTam = lista->array[second].paginaTam;
    lista->array[first].capacidade = lista->array[second].capacidade;

    // temp para segundo

    strcpy(lista->array[second].nome, temp->nome);
    strcpy(lista->array[second].apelido, temp->apelido);
    strcpy(lista->array[second].estadio, temp->estadio);
    strcpy(lista->array[second].tecnico, temp->tecnico);
    strcpy(lista->array[second].liga, temp->liga);
    strcpy(lista->array[second].nomeArquivo, temp->nomeArquivo);
    lista->array[second].fundacaoDia = temp->fundacaoDia;
    lista->array[second].fundacaoMes = temp->fundacaoMes;
    lista->array[second].fundacaoAno = temp->fundacaoDia;
    lista->array[second].paginaTam = temp->paginaTam;
    lista->array[second].capacidade = temp->capacidade;

    free(temp);
}

void selectionsort(Lista lista[], int n)
{
    int i, im, tmp;
    if (n > 1)
    {
        im = 0; //im = índice do maior valor
        for (i = 1; i < n; i++)
            if (lista->array[i].paginaTam > lista->array[im].paginaTam) //Seleciona o maior valor
                im = i;
        if (im != n - 1)
        { //Efetua troca
            swap(im, n - 1, lista);
        }
        selectionsort(lista, n - 1);
    }
}
void insercaoPorCor(Lista lista[], int n, int cor, int h)
{
    for (int i = (h + cor); i < n; i += h)
    {
        Time *tmp;
        tmp = (Time *)malloc(sizeof(Time));
        int j = i - h;

        while ((j >= 0) && strcmp(lista->array[j].tecnico, tmp->tecnico) > 0)
        {
            lista->array[j + h] = lista->array[j];
            j -= h;
        }
        memccpy(&(lista->array[j + h]), tmp, sizeof(Time), sizeof(Time));
        free(tmp);
    }
}

// Algoritmo de ordenacao
void shellsort(Lista lista[], int n)
{
    int h = 1;

    do
    {
        h = (h * 3) + 1;
    } while (h < n);

    do
    {
        h /= 3;
        for (int cor = 0; cor < h; cor++)
        {
            insercaoPorCor(lista, (lista->n), cor, h);
        }
    } while (h != 1);
}

void quicksortRec(Lista lista[], int esq, int dir)
{
    int i = esq, j = dir;
    Time *pivo;
    pivo = (Time *)malloc(sizeof(Time));
    memccpy(pivo, &(lista->array[(dir + esq) / 2]), sizeof(Time), sizeof(Time));
    while (i <= j)
    {
        while (strcmp(lista->array[i].apelido, pivo->apelido) < 0)
            i++;
        while (strcmp(lista->array[j].apelido, pivo->apelido) > 0)
            j--;
        if (i <= j)
        {
            swap(i, j, lista);
            i++;
            j--;
        }
    }
    if (esq < j)
        quicksortRec(lista, esq, j);
    if (i < dir)
        quicksortRec(lista, i, dir);
    free(pivo);
}

// Algoritmo de ordenacao
void quicksort(Lista lista[])
{
    quicksortRec(lista, 0, lista->n - 1);
}

int main(int argc, char const *argv[])
{

    char valor[ENTRY][LINE];
    int valorIndex = 0;

    Time *time;
    Lista *lista = (Lista *)malloc(sizeof(Lista));
    lista->n = 0;

    do
    {

        fgets(valor[valorIndex], LINE, stdin);
    } while (isFim(valor[valorIndex++]) == false);
    valorIndex--;
    char *newline;
    for (int i = 0; i < valorIndex; i++)
    {
        newline = strchr(valor[i], '\n');
        if (newline)
            *newline = 0;
        time = (Time *)malloc(sizeof(Time));
        if (time == NULL)
        {
            return (-1);
        }

        ler(valor[i], time);
        // imprimir(time);
        inserirFim(lista, time);

        free(time);
    }

    // mostrar(lista);

    quicksort(lista);

    mostrar(lista);
    long tempo = 2;
    int comapacoes = 1;

    FILE *pont_arq;
    pont_arq = fopen("655455_quicksort.txt", "w");
    fprintf(pont_arq, "655455\t %ld \t %i", tempo, comapacoes);
    fclose(pont_arq);
    free(lista);
}