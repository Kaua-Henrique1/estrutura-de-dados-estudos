# Relatório Acadêmico: Simulação Prática do Algoritmo Bogo Sort

## Rastreamento de Estados (Passo a Passo)

Para demonstrar a mecânica caótica e ineficiente do Bogo Sort, simularemos a execução com um vetor minúsculo de **4 elementos** ($N = 4$). O algoritmo opera em um ciclo contínuo de duas fases: **Verificar** e **Embaralhar**.

**Configuração Inicial:**
* **Vetor Original:** `[4, 2, 3, 1]`
* **Tamanho ($N$):** 4
* **Permutações Possíveis ($N!$):** $4! = 24$ combinações possíveis no espaço de busca.

---

### Iteração 1: A Primeira Tentativa

**Fase de Verificação:**
O algoritmo percorre o vetor da esquerda para a direita, comparando o elemento atual com o anterior.
* `v[1] < v[0]` $\rightarrow$ $2 < 4$? **Verdadeiro.**
A ordem está errada logo no primeiro par. A varredura é interrompida.

**Fase de Embaralhamento:**
O gerador pseudoaleatório (`Random`) troca todos os elementos de posição de forma arbitrária.
* **Resultado do Embaralhamento:** `[3, 1, 4, 2]`

---

### Iteração 2: O Falso Positivo

**Fase de Verificação:**
* `v[1] < v[0]` $\rightarrow$ $1 < 3$? **Verdadeiro.**
A ordem falhou novamente no início.

**Fase de Embaralhamento:**
* **Resultado do Embaralhamento:** `[1, 2, 4, 3]`

---

### Iteração 3: Quase Lá

**Fase de Verificação:**
O vetor parece promissor no início. O algoritmo avança mais casas:
* `v[1] < v[0]` $\rightarrow$ $2 < 1$? Falso. (Correto até aqui)
* `v[2] < v[1]` $\rightarrow$ $4 < 2$? Falso. (Correto até aqui)
* `v[3] < v[2]` $\rightarrow$ $3 < 4$? **Verdadeiro.**
A ordem quebrou no último elemento. A varredura é interrompida.

**Fase de Embaralhamento:**
Como o Bogo Sort **não é adaptativo e não tem memória**, ele não aproveita o fato de que os três primeiros elementos estavam corretos. Ele destrói todo o progresso e embaralha o vetor inteiro novamente.
* **Resultado do Embaralhamento:** `[3, 1, 4, 2]`

> **Nota Analítica:** Observe que o gerador aleatório recriou **exatamente a mesma sequência incorreta da Iteração 1**. Isso demonstra o risco teórico do pior caso $O(\infty)$, onde o algoritmo pode entrar em ciclos viciosos de má sorte.

---

### Iteração X: O "Milagre" Estatístico

Após múltiplas iterações frustradas (podem ser 5, 20 ou 100 tentativas para um $N=4$), o gerador aleatório finalmente alinha os planetas.

**Resultado do Embaralhamento:** `[1, 2, 3, 4]`

**Fase de Verificação Final:**
* `v[1] < v[0]` $\rightarrow$ $2 < 1$? Falso.
* `v[2] < v[1]` $\rightarrow$ $3 < 2$? Falso.
* `v[3] < v[2]` $\rightarrow$ $4 < 3$? Falso.

A varredura atinge o final do vetor (índice 3) sem encontrar nenhum erro. A variável booleana `ordenado` permanece `true`. O laço `while` é quebrado.

**Fim da Execução.**
* **Vetor Final:** `[1, 2, 3, 4]`