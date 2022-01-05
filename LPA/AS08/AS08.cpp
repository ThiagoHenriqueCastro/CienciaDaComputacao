/**
 * AS07 - Força Bruta
 * @author THIAGO HENRIQUE DE CASTRO OLIVEIRA
 * @version 1 09/2021
 * COMPLEXIDADE O(N)
 */

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int plays = 0;
vector<int> sequence;

vector<int> _merge(vector<int> v1, vector<int> v2)
{
    int i = 0, j = 0, aux1 = v1.size(), aux2 = v2.size();
    vector<int> result;

    while (i < aux1 && j < aux2)
    {
        if (v1[i] < v2[j])
        {
            result.push_back(v1[i]);
            i++;
        }
        else
        {
            result.push_back(v2[j]);
            plays += (int)(v1.size()) - i;
            j++;
        }
    }

    while (i < aux1)
    {
        result.push_back(v1[i]);
        i++;
    }

    while (j < aux2)
    {
        result.push_back(v2[j]);
        j++;
    }

    return result;
}

vector<int> _mergeSort(int esquerda, int direita)
{

    vector<int> result;
    int meio = (esquerda + direita) / 2;

    if (esquerda + 1 >= direita)
    {
        result.push_back(sequence[esquerda]);
        return result;
    }

    vector<int> v1 = _mergeSort(esquerda, meio);
    vector<int> v2 = _mergeSort(meio, direita);
    result = _merge(v1, v2);

    return result;
}

int main()
{
    int n, number;

    cin >> n;
    while (n != 0)
    {
        plays = 0;
        sequence.clear();

        for (int i = 0; i < n; i++)
        {
            cin >> number;
            sequence.push_back(number);
        }

        _mergeSort(0, n);

        if (plays % 2)
            cout << "Marcelo" << endl;
        else
            cout << "Carlos" << endl;

        cin >> n;
    }
}
