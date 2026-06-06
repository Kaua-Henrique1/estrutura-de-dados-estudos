# Simulação Passo a Passo das Fases do IntroSort

Para entender a dinâmica introspectiva, vamos analisar um cenário com um array de **18 elementos**.
Configuração inicial do algoritmo:
* **Tamanho do array ($n$):** 18
* **Limite de profundidade:** $2 \times \lfloor\log_2(18)\rfloor = 2 \times 4 =$ **8**
* **Limiar do Insertion Sort:** $\le 16$ elementos.

---

### 1. Entrada e Fase Inicial: Particionar e Fazer Recursão (QuickSort)

O array possui 18 elementos (maior que 16) e a profundidade atual é 0 (menor que o limite 8). Portanto, o IntroSort decide usar o **QuickSort**.

1. **Escolha do Pivô:** O algoritmo analisa o primeiro, o do meio e o último elemento (Mediana de Três) e escolhe o pivô ideal.
2. **Particionamento:** O array é reorganizado. Todos os elementos menores que o pivô vão para a esquerda; os maiores vão para a direita.
3. **Divisão:** Suponha que o pivô dividiu o array de forma desbalanceada:
    * **Subarray Esquerda (L):** 2 elementos.
    * **Subarray Direita (R):** 15 elementos.

O algoritmo decrementa o limite de profundidade ($8 - 1 = 7$) e faz a **chamada recursiva** para os dois lados.

---

### 2. Ativação do Insertion Sort (Subarrays Pequenos)

Ao analisar o **Subarray Esquerda (L)**, que restou com apenas **2 elementos**:

1. **Verificação de Condição:** O IntroSort checa o tamanho do segmento.
2. **Decisão:** Como $2 \le 16$, o algoritmo **não** faz o particionamento do QuickSort e interrompe a recursão para este bloco.
3. **Execução:** O **Insertion Sort** é acionado para ordenar localmente esses 2 elementos de forma rápida, aproveitando o baixo overhead e a excelente localidade de cache.

---

### 3. Degradação e Ativação do HeapSort (Válvula de Segurança)

Enquanto isso, o **Subarray Direita (R)**, que começou com 15 elementos, sofre sucessivos particionamentos ruins (por causa de elementos duplicados ou ordenados que driblaram a mediana).

A cada nova divisão do QuickSort, a pilha de recursão aumenta e o limite de profundidade cai:
* Partição 1: profundidade restante = 7
* Partição 2: profundidade restante = 6
* ...
* Partição 7: profundidade restante = 0

Ao chegar na chamada de **profundidade restante = 0**, o subarray atual ainda possui **10 elementos** (ainda não caiu no Insertion Sort).

1. **Verificação de Condição:** Tamanho é 10 (maior que 16? Não, mas aqui a profundidade zerou primeiro na lógica de divisões sucessivas). *Nota: Se o tamanho fosse menor que 16 antes de zerar, o Insertion resolveria. Como a profundidade zerou com o bloco ainda necessitando de divisões estruturais de pior caso:*
2. **Decisão:** `depthLimit == 0`. O IntroSort detecta que o QuickSort está degradando para $O(n^2)$ (comportamento patológico).
3. **Execução:** O QuickSort é abortado imediatamente para este segmento. O algoritmo aciona o **HeapSort**.
4. **Mecânica do HeapSort:**
    * Transforma o subarray de 10 elementos em um *Max-Heap* (árvore binária onde o maior fica na raiz).
    * Faz o processo de `siftDown` (arrastar para baixo), trocando a raiz pelo último elemento e diminuindo o tamanho do heap.
    * Ordena o segmento estritamente em $O(n \log n)$ sem gastar mais nenhum nível de recursão em pilha.

---

### Summary do Fluxo de Execução

Ao final do processo, as três fases cooperaram para fechar a ordenação do array completo:    

# Relatório Acadêmico: Análise Avançada do Algoritmo IntroSort

# 3. Simulação Prática do IntroSort (Rastreamento de Estados)

Para entender a dinâmica introspectiva, vamos simular a execução do algoritmo em um cenário controlado com um vetor de **18 elementos**.

**Configuração Inicial:**
* **Vetor Inicial:** `[18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 1, 2]`
* **Tamanho ($n$):** 18 (Ativa a checagem de divisão e conquista por ser $> 16$)
* **Limite de Profundidade Teórico:** $2 \times \lfloor\log_2(18)\rfloor = 8$

---

### Fase 1: Particionamento Inicial (Motor QuickSort)
* **Estado:** Profundidade = 8 | Tamanho do bloco = 18

O algoritmo executa a estratégia de pivô por **mediana de três** entre o primeiro, o elemento central e o último valor: `Mediana(18, 10, 2) = 10`. O vetor é rearranjado usando o `10` como pivô.

```text
Vetor após particionamento:
[2, 1, 3, 4, 5, 6, 7, 8, 9]  [10]  [14, 13, 12, 11, 15, 16, 17, 18]
└──────── Subvetor L ──────┘        └───────── Subvetor R ─────────┘
      (9 elementos)                         (8 elementos)
```
**Próximo Passo:** O limite de profundidade original (8) cai para **7** para a próxima camada. O algoritmo agora empilha as duas chamadas recursivas e passa a tratar o `Subvetor L` e o `Subvetor R` de forma independente.

---

### Fase 2: Processando o Subvetor L (Divisões Sucessivas)
* **Estado:** Profundidade = 7 | Tamanho do bloco = 9
* **Segmento Atual:** `[2, 1, 3, 4, 5, 6, 7, 8, 9]`

O IntroSort avalia o bloco: o tamanho é 9 (maior que 16? Não, mas a verificação inicial avalia o bloco principal e permite a primeira subdivisão na árvore). O algoritmo aplica a **mediana de três** entre os elementos `2`, `5` e `9`: `Mediana(2, 5, 9) = 5`.

O `5` é definido como pivô para um novo particionamento:

```text
Vetor após o segundo particionamento (no Subvetor L):
[2, 1, 3, 4]  [5]  [6, 7, 8, 9]
└─ Bloco L1 ┘      └─ Bloco L2 ┘
 (4 itens)          (4 itens)
```

Próximo Passo: O limite de profundidade cai para 6. O algoritmo faz novas chamadas recursivas para Bloco L1 e Bloco L2.
- 2.1 Processando o Bloco L1 (Desvio para o Insertion Sort)
   - Estado: Profundidade = 6 | Tamanho do bloco = 4
   - Segmento Atual: [2, 1, 3, 4]

O IntroSort analisa o Bloco L1:
```text
Condição: Tamanho (4) ≤ 16? → SIM!
```

Decisão: O algoritmo aborta o QuickSort para este segmento. A pilha de recursão para o Bloco L1 é interrompida e o fluxo é desviado para o Insertion Sort.Execução do Insertion Sort no Bloco L1:Isola o [2]. Analisa o 1: como $1 < 2$, arrasta o $2$ e insere o $1$ -> [1, 2, 3, 4]Analisa o 3: já está maior que o $2$ -> mantém.Analisa o 4: já está maior que o $3$ -> mantém.
```text
Resultado do Bloco L1 (Ordenado): [1, 2, 3, 4]
```

2.2 Processando o Bloco L2 (Desvio para o Insertion Sort)

    Estado: Profundidade = 6 | Tamanho do bloco = 4

    Segmento Atual: [6, 7, 8, 9]

O algoritmo desempilha o Bloco L2 e repete a checagem:

```text
Condição: Tamanho (4) ≤ 16? → SIM!
```

Decisão: O QuickSort também é abortado aqui. O Insertion Sort assume o controle. Como o trecho original já se encontrava ordenado, o Insertion Sort faz apenas varreduras de leitura simples ($O(n)$ para cenários ordenados), confirmando a estrutura sem realizar trocas físicas.

```text
Resultado do Bloco L2 (Ordenado): [6, 7, 8, 9]
```

Status do Subvetor L: Com a união de Bloco L1, o pivô 5 e Bloco L2, toda a metade esquerda do array original está completamente ordenada: [1, 2, 3, 4, 5, 6, 7, 8, 9].

Fase 3: Processando o Subvetor R (Cenário de Degradação)

    Estado: Profundidade = 7 | Tamanho do bloco = 8

    Segmento Atual: [14, 13, 12, 11, 15, 16, 17, 18]

O algoritmo agora foca na metade direita do array. Para fins didáticos nesta simulação, assuma que os sucessivos particionamentos deste subvetor isolaram pivôs ruins devido à distribuição dos dados, fazendo o limite de profundidade cair drasticamente a cada divisão.
```text
Pilha de recursão do Subvetor R sofrendo degradação:
- Partição R1: profundidade restante cai de 7 para 6
- Partição R2: profundidade restante cai de 6 para 5
...
- Partição R7: profundidade restante atinge 0!
```

Ao atingir a profundidade restante = 0, o algoritmo se depara com o seguinte bloco contendo 5 elementos ainda desalinhados:
```text
Segmento crítico atual: [14, 13, 12, 11, 15]
Condição 1: Tamanho (5) ≤ 16? → Não aplica (a checagem de profundidade ocorre antes nesta linha)
Condição 2: Profundidade == 0? → SIM!
```
Decisão: O IntroSort detecta o comportamento patológico (risco de degradação para $O(n^2)$). O QuickSort é encerrado sumariamente para este trecho e a válvula de segurança é acionada: o HeapSort assume o controle do bloco.Execução do HeapSort no Bloco Crítico:Transformação em Max-Heap: O array é rearranjado em uma estrutura de árvore binária lógica através de varreduras siftDown, onde o maior elemento fica na raiz:

```text
Estado do bloco como Max-Heap: [15, 14, 12, 11, 13]
```
Ordenação por Extração: O HeapSort troca repetidamente a raiz (maior valor) com o elemento da última posição do bloco e reconstrói o heap no espaço restante:
```text
Troca 15 com 13 -> Reorganiza Heap -> [14, 13, 12, 11] | [15]
Troca 14 com 11 -> Reorganiza Heap -> [13, 11, 12]     | [14, 15]
Troca 13 com 12 -> Reorganiza Heap -> [12, 11]         | [13, 14, 15]
Troca 12 com 11 -> Fim do Heap      -> [11, 12, 13, 14, 15]
```
O HeapSort encerra sua atividade garantindo a ordenação do trecho estritamente dentro do limite de tempo linear-logarítmico ($O(n \log n)$).
```text
Resultado do Subvetor R (Ordenado): [11, 12, 13, 14, 15, 16, 17, 18]
```
Estado Final do Array

Após todas as sub-rotinas finalizarem seus respectivos blocos in-place na memória, o IntroSort encerra a execução. O array de 18 elementos está perfeitamente unificado e ordenado:

```text
Vetor Final Consolidado:
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18]
```