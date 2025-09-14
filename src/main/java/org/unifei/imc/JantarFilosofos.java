package org.unifei.imc;

//o deadlock pode ser evitado apenas usando o monitor (lock e wait) do java,
//mas o starvation requer um fila de prioridade conforme implementação abaixo
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class JantarFilosofos {

    private enum Estado { PENSANDO, COM_FOME, COMENDO }

    private final int N;
    private final Estado[] estado;

    //lock explícito para mais controle
    private final Lock lock = new ReentrantLock(true); //o 'true' ativa o modo justo (fila de prioridade) para evitar starvation
    private final Condition[] self;

    public JantarFilosofos(int n) {
        this.N = n;
        this.estado = new Estado[n];
        this.self = new Condition[n];
        for (int i = 0; i < n; i++) {
            estado[i] = Estado.PENSANDO;
            self[i] = lock.newCondition(); //cada filósofo tem sua própria condição de espera
        }
    }

    private int esq(int i) { return (i + N - 1) % N; }
    private int dir(int i) { return (i + 1) % N; }

    private boolean podeComer(int i) {
        return estado[esq(i)] != Estado.COMENDO && estado[dir(i)] != Estado.COMENDO; //só se vizinhos não estão comendo
    }

    public void pegarGarfos(int i) throws InterruptedException {
        lock.lock();
        try {
            estado[i] = Estado.COM_FOME;

            while (!podeComer(i)) {
                self[i].await(); //libera o lock e aguarda um 'signal'
            }

            estado[i] = Estado.COMENDO;
        } finally {
            lock.unlock(); //garante a liberação do lock
        }
    }

    public void devolverGarfos(int i) {
        lock.lock();
        try {
            estado[i] = Estado.PENSANDO;

            //acorda os vizinhos específicos que agora podem comer
            int e = esq(i);
            if (estado[e] == Estado.COM_FOME && podeComer(e)) {
                self[e].signal();
            }

            int d = dir(i);
            if (estado[d] == Estado.COM_FOME && podeComer(d)) {
                self[d].signal();
            }
        } finally {
            lock.unlock(); //garante a liberação do lock
        }
    }
}