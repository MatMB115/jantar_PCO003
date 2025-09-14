# Jantar dos Filósofos - PCO003
>Aluno: Matheus Martins Batista
> 
>Professor(s): Bruno Guazzelli Batista e Rodrigo Maximiano Antunes de Almeida
>
>https://github.com/MatMB115/jantar_PCO003

## Objetivo
Execução da atividade avaliativa de PCO003 sobre o problema clássico de exclusão mútua do "Jantar dos Filósofos" previnindo Deadlock e Starvation.

## Pré-requisitos

Para compilar e executar este projeto manualmente, você precisará de:

- **Java Development Kit (JDK) versão 11 ou superior.**

Para verificar se o JDK está instalado e configurado corretamente, abra um terminal e execute:
```bash
# java
java --version
# compilador
javac --version
```

## Como Compilar o Programa

As instruções a seguir partem do princípio de o usuário está no terminal (ou cmd), dentro da pasta raiz do projeto (a pasta que contém o diretório `src`).

---

###  Instruções para Linux

1.  **Abra o Terminal.**

2.  **Navegue até a pasta raiz do projeto.**
    ```bash
    cd /path/da/atividade/jantar_PCO003
    ```

3.  **Compile o código-fonte.**
    O comando compila todos os arquivos `.java` localizados em `src/main/java` e move as classes resultantes para diretório `target/classes`.
    ```bash
    javac -d target/classes src/main/java/org/unifei/imc/*.java
    ```

4.  **Execute o programa.**
    ```bash
    java -cp target/classes org.unifei.imc.Main
    ```

---

### Instruções para Windows

1.  **Abra o Prompt de Comando (CMD) ou o PowerShell.**

2.  **Navegue até a pasta raiz do projeto.**
    ```powershell
    cd C:\caminho\para\a\atividade\jantar_PCO003
    ```

3.  **Compile o código-fonte.**
    Este comando compila todos os arquivos `.java` e coloca os arquivos `.class` resultantes no diretório `target/classes`.
    ```powershell
    javac -d target/classes src/main/java/org/unifei/imc/*.java
    ```

4.  **Execute o programa.**
    ```powershell
    java -cp target/classes org.unifei.imc.Main
    ```

## Abordagem Adotada
A solução implementada utiliza primitivas de concorrência da biblioteca `java.util.concurrent` para garantir solução adequada, resolvendo os dois desafios do problema: deadlock e starvation.
Vale ressaltar que Deadlock pode ser resolvido com monitor das primitivas do Java (synchronized, wait() e notifyAll()), mas Starvation requer uma fila de prioridade para garantir que todos os filósofos comam. Ambas as soluções estão alinhadas com o que Andrew Tanenbaum (Deadlock) e Silberschatz, Galvin e Gagne (Starvation) propõem em seus livros de Sistemas Operacionais.

### Deadlock e Starvation

- Deadlock: O deadlock é evitado ao quebrar a condição de "posse e espera". Através de uma seção crítica protegida por um lock, um filósofo só muda para o estado "Comendo" após verificar atomicamente que ambos os seus vizinhos não estão comendo. Se ele não puder pegar os dois "garfos" de uma vez, ele espera sem segurar nenhum recurso, quebrando assim a condição de espera circular que causa o impasse.

- Starvation: A starvation é resolvida com uma política de justiça, implementada pelo `new ReentrantLock(true)`. Este "lock justo" organiza as threads dos filósofos que querem acesso à mesa em uma fila do tipo "primeiro a chegar, primeiro a ser servido". Ao garantir que a thread que está esperando há mais tempo seja a próxima a obter o lock, o sistema assegura que nenhum filósofo seja perpetuamente ignorado, pois sua vez de tentar comer é garantida. O processo pode ser implementado com um fila de prioridade manualmente, mas como Java já dispõe de uma implementação justa para isso e a ferramenta foi utilizada.

### Diagrama de Sequência
[![](https://mermaid.ink/img/pako:eNqVU12r00AQ_SvDPlWoJU0_E_CC9LaCkFbwTXORMTtJF5PddLOp2tIf5LM_4f4xZ9PbeouI-NJszsw5Z-akexSZkSRi0dCuJZ3RvcLCYpXqGq1TmapRO1gBNrBSpWlMbj6qh9tq4qtv-YT20tOkujSmhu5HIritJZSpXr28u1vFUJNu0PZenIHEAwXaN2iZ2VMMY-bUHh1Bkurk3EKNQ-nN4RUsNsmn1SZZcmPpoOYNFqYiy1R4_AE5lo3xvLMZfkXlgCpoqMw9H0r1mSyC4fGyL6leGzayqtg6MDmwlWmdNZCr8vGnX6YzAEe2UhotEDT8LNUB7XV-3i7btcpSJwloADNjpe9Irh17JuHNtKkmLf-24XJ9v0m1pOdZPOWXdfzf8Unam3JP_5Hgu-X6_evO4FJkkJqdjxAkn6U6D-gD3quD0lvj6y1ZydEgR-EYAKzdJWoW6RKmh0GjCib2Lvs912BdUu4fEvIPidsYGBJ9UVglRey_NvUFB1KhfxfHVAOkwm2polTEfJSUY1u6VKT6xDz-034wphKxsy0zrWmL7VWnrSWbPN2CK2rZkezCtNqJeDQOOxERH8U3EYfRfDCbhNE0CINJMJsP--I7o-PBNBgPw3EwjcLhaD6dnfri0NkGg2gacl80Ho4m4ygMR31BkkOxyfkudlfy9AuKwjZO?type=png)](https://mermaid.live/edit#pako:eNqVU12r00AQ_SvDPlWoJU0_E_CC9LaCkFbwTXORMTtJF5PddLOp2tIf5LM_4f4xZ9PbeouI-NJszsw5Z-akexSZkSRi0dCuJZ3RvcLCYpXqGq1TmapRO1gBNrBSpWlMbj6qh9tq4qtv-YT20tOkujSmhu5HIritJZSpXr28u1vFUJNu0PZenIHEAwXaN2iZ2VMMY-bUHh1Bkurk3EKNQ-nN4RUsNsmn1SZZcmPpoOYNFqYiy1R4_AE5lo3xvLMZfkXlgCpoqMw9H0r1mSyC4fGyL6leGzayqtg6MDmwlWmdNZCr8vGnX6YzAEe2UhotEDT8LNUB7XV-3i7btcpSJwloADNjpe9Irh17JuHNtKkmLf-24XJ9v0m1pOdZPOWXdfzf8Unam3JP_5Hgu-X6_evO4FJkkJqdjxAkn6U6D-gD3quD0lvj6y1ZydEgR-EYAKzdJWoW6RKmh0GjCib2Lvs912BdUu4fEvIPidsYGBJ9UVglRey_NvUFB1KhfxfHVAOkwm2polTEfJSUY1u6VKT6xDz-034wphKxsy0zrWmL7VWnrSWbPN2CK2rZkezCtNqJeDQOOxERH8U3EYfRfDCbhNE0CINJMJsP--I7o-PBNBgPw3EwjcLhaD6dnfri0NkGg2gacl80Ho4m4ygMR31BkkOxyfkudlfy9AuKwjZO)
