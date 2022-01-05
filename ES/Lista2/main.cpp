#include <stdio.h>
#include <stdlib.h>
#include <string>
#include <iostream>
#include <vector>

using namespace std;

class Pluviometro
{
private:
    string tipo;
    int peso = 0, capacidade = 0;

public:
    Pluviometro(string t)
    {
        this->tipo = t;
    }
    string getTipo()
    {
        return this->tipo;
        if (this->tipo == "A")
            this->peso = 10, this->capacidade = 5;
        else if (this->tipo == "B")
            this->peso = 15, this->capacidade = 8;
        else if (this->tipo == "C")
            this->peso = 20, this->capacidade = 10;
    }

    int getPeso()
    {
        return this->peso;
    }
    int getCapacidade()
    {
        return this->capacidade;
    }
};

class Caminhao
{
private:
    int peso;
    vector<Pluviometro> _p;

public:
    virtual ~Caminhao() {}
    virtual void inserePluviometro(Pluviometro p);
    int getPeso() { return this->peso; };
    void setPeso(int p) { this->peso += p; };
    void setPluviometro(Pluviometro p)
    {
        this->_p.push_back(p);
    }
    vector<Pluviometro> getPluviometro()
    {
        return this->_p;
    }
};

class CaminhaoAlfa : public Caminhao
{
public:
    void inserePluviometro(Pluviometro p) override
    {
        if (this->getPeso() <= 5000)
            this->setPluviometro(p), this->setPeso(p.getPeso());
    }
};
class CaminhaoBeta : public Caminhao
{
public:
    void inserePluviometro(Pluviometro p) override
    {
        vector<Pluviometro> pluviometros = this->getPluviometro();
        bool flag = true;

        for (int i = 0; i < pluviometros.size(); i++)
        {
            if (pluviometros[i].getTipo() == p.getTipo())
                flag = false;
        }

        if (flag)
            this->setPluviometro(p), this->setPeso(p.getPeso());
    }
};

int main()
{
}