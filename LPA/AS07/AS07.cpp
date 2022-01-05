/**
 * AS07 - Força Bruta
 * @author THIAGO HENRIQUE DE CASTRO OLIVEIRA
 * @version 1 09/2021
 * COMPLEXIDADE O(N)
 */

#include <iostream>
#include <vector>
#include <algorithm>
#include <iomanip>
#include <climits>
#include <cmath>

using namespace std;

float getPointDistance(int a1, int a2, int b1, int b2)
{
    return sqrt(pow(b1 - a1, 2) + pow(b2 - a2, 2) * 1.0);
}

void _minDistance(int points[][2], int n)
{
    float min_distance = INT_MAX;
    float distance = 0;
    for (int i = 0; i < n; i++)
    {
        for (int j = i + 1; j < n; j++)
        {
            distance = getPointDistance(points[i][0], points[i][1], points[j][0], points[j][1]);

            if (distance < min_distance)
                min_distance = distance;
        }
    }
    min_distance < 10000 ? cout << fixed << setprecision(4) << min_distance << endl : cout << "INFINITY" << endl;
}

int main()
{

    int n_points;
    int p1, p2;
    cin >> n_points;
    while (n_points != 0)
    {
        int points[n_points][2];
        for (int i = 0; i < n_points; i++)
        {
            cin >> p1 >> p2;
            points[i][0] = p1;
            points[i][1] = p2;
        }

        _minDistance(points, n_points);
        cin >> n_points;
    }
}
