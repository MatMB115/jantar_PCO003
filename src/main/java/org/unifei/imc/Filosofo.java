package org.unifei.imc;

public class Filosofo implements Runnable {
    private final int id;
    private final JantarFilosofos mesa;
    private final int pensarMs;
    private final int comerMs;

    public Filosofo(int id, JantarFilosofos mesa, int pensarMs, int comerMs) {
        this.id = id;
        this.mesa = mesa;
        this.pensarMs = pensarMs;
        this.comerMs = comerMs;
    }

    private void pensar() throws InterruptedException {
        Thread.sleep(pensarMs);
    }

    private void comer() throws InterruptedException {
        System.out.println("Filósofo " + id + " comendo");
        Thread.sleep(comerMs);
        System.out.println("Filósofo " + id + " terminou");
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                pensar();
                mesa.pegarGarfos(id);
                try {
                    comer();
                } finally {
                    //se cair em exception garante devolução do garfo
                    mesa.devolverGarfos(id);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}