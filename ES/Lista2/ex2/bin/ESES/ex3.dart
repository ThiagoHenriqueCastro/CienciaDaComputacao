// Ex3 Lista 3
// Grasp

import 'dart:math';

class Experimento {
  List<Function> sequencia = {} as List<Function>;
  int micro = 10000;

  void clone() {}
  void run() {}
}

class ExperimentoMonitorado extends Experimento {
  int contaMicro() {
    return micro;
  }

  ExperimentoMonitorado cloneMutant() {
    var copia = ExperimentoMonitorado();
    copia.micro = micro;
    copia.sequencia = sequencia;
    var seed = Random().nextInt(copia.sequencia.length);
    copia.sequencia.removeAt(seed);

    return copia;
  }
}

void testaExperimento(ExperimentoMonitorado b, int n) {
  var melhores = {} as List<ExperimentoMonitorado>;
  var clones = {} as List<ExperimentoMonitorado>;
  for (var j = 0; j < n; j++) {
    melhores.add(b);

    for (var i = 0; i < melhores.length; i++) {
      clones.add(melhores[i].cloneMutant());
    }

    for (var e in clones) {
      e.run();
      melhores.add(e);
    }
    for (var e in melhores) {
      e.run();
      melhores.add(e);
    }
  }
}

void main(List<String> arguments) {}
