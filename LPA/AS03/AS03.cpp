/**
 * AS03 - Estrutura de Dados
 * @author THIAGO HENRIQUE DE CASTRO OLIVEIRA
 * @version 1 08/2021
 * COMPLEXIDADE 
 */

#include <string>
#include <iostream>

using namespace std;

void _containsSubstring(string _string, string _substring)
{

    cout << (_string.find(_substring) != string::npos ? "Yes\n" : "No\n");
}

void preKMP(string pattern, int f[])
{
    int m = pattern.length(), k;
    f[0] = -1;
    for (int i = 1; i < m; i++)
    {
        k = f[i - 1];
        while (k >= 0)
        {
            if (pattern[k] == pattern[i - 1])
                break;
            else
                k = f[k];
        }
        f[i] = k + 1;
    }
}

//check whether target string contains pattern
bool KMP(string target, string pattern)
{
    int m = pattern.length();
    int n = target.length();
    int f[m];
    preKMP(pattern, f);
    int i = 0;
    int k = 0;
    while (i < n)
    {
        if (k == -1)
        {
            i++;
            k = 0;
        }
        else if (target[i] == pattern[k])
        {
            i++;
            k++;
            if (k == m)
                return 1;
        }
        else
            k = f[k];
    }
    return 0;
}

int main()
{
    int entries;
    cin >> entries;

    string _string;
    int _substringEntries;
    string _substring;

    for (int i = 0; i < entries; i++)
    {
        cin >> _string;
        cin >> _substringEntries;

        for (int i = 0; i < _substringEntries; i++)
        {
            cin >> _substring;
            //cout << (KMP(_string, _substring) ? "Yes\n" : "No\n");
            _containsSubstring(_string, _substring);
        }
    }
}
