package org.unifei.imc;

public class Main {
    public static void main(String[] args) {
        final int N = 5;
        final JantarFilosofos mesa = new JantarFilosofos(N);

        //uma thread por filósofo
        for (int i = 0; i < N; i++) {
            Filosofo f = new Filosofo(i, mesa, 2000, 3000);
            Thread t = new Thread(f, "Filosofo-" + i);
            t.start();
        }
    }
}
