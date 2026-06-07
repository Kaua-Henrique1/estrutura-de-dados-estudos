# Relatório Acadêmico: Análise Avançada do Algoritmo IntroSort

## Simulação Prática do IntroSort (Rastreamento de Estados)

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

> Decisão: O algoritmo aborta o QuickSort para este segmento. A pilha de recursão para o Bloco L1
> é interrompida e o fluxo é desviado para o Insertion Sort. 
 
### Execução do Insertion Sort no Bloco L1:
- Isola o **[2]**. Analisa o 1: como $1 < 2$, arrasta o $2$ e insere o $1$ -> [1, 2, 3, 4]
- Analisa o 3: já está maior que o $2$ -> mantém.
- Analisa o 4: já está maior que o $3$ -> mantém.
```text
Resultado do Bloco L1 (Ordenado): [1, 2, 3, 4]
```

### 2.2 Processando o Bloco L2 (Desvio para o Insertion Sort)

| Parâmetro | Valor |
| :--- | :--- |
| **Estado** | Profundidade = 6 \| Tamanho do bloco = 4 |
| **Segmento Atual** | `[6, 7, 8, 9]` |

O algoritmo desempilha o **Bloco L2** e repete a checagem de controle:

> **Condição:** $\text{Tamanho } (4) \le 16 \longrightarrow$ **SIM!**

#### Decisão e Execução
O **QuickSort** é abortado neste ponto e o **Insertion Sort** assume o controle adaptativo.
Como o trecho original já se encontrava previamente ordenado, o Insertion Sort realiza apenas varreduras de leitura simples — apresentando complexidade linear $O(n)$ para cenários ordenados —, confirmando a estrutura sem a necessidade de efetuar trocas físicas na memória.

* **Resultado do Bloco L2 (Ordenado):** `[6, 7, 8, 9]`

#### Status do Subvetor L
Com a união harmônica do *Bloco L1*, o *pivô 5* e o *Bloco L2*, toda a metade esquerda do array original encontra-se completamente consolidada e ordenada:
`[1, 2, 3, 4, 5, 6, 7, 8, 9]`

---

### Fase 3: Processando o Subvetor R (Cenário de Degradação)

| Parâmetro | Valor |
| :--- | :--- |
| **Estado** | Profundidade = 7 \| Tamanho do bloco = 8 |
| **Segmento Atual** | `[14, 13, 12, 11, 15, 16, 17, 18]` |

O algoritmo agora direciona o foco para a metade direita do array. Para fins didáticos nesta simulação, assume-se que os sucessivos particionamentos deste subvetor isolaram pivôs ruins devido à distribuição desfavorável dos dados, fazendo o limite de profundidade cair drasticamente a cada nova divisão.

#### 📉 Linha do Tempo: Pilha de Recursão do Subvetor R
```text
[Início] Profundidade restante: 7
   ├── Partição R1: profundidade restante cai para 6
   ├── Partição R2: profundidade restante cai para 5
   └── ...
   └── Partição R7: profundidade restante atinge 0! 🚨
```

Ao atingir a **profundidade restante = 0**, o algoritmo se depara com o seguinte bloco crítico contendo 5 elementos ainda desalinhados:

> **Segmento Crítico Atual:** `[14, 13, 12, 11, 15]`
> * **Condição 1:** $\text{Tamanho } (5) \le 16 \longrightarrow$ Não se aplica (a checagem de profundidade ocorre de forma prioritária nesta linha)
> * **Condição 2:** $\text{Profundidade} == 0 \longrightarrow$ **SIM!**

#### Válvula de Segurança Ativada
O **IntroSort** detecta o comportamento patológico com risco iminente de degradação da complexidade para tempo quadrático $O(n^2)$. O QuickSort é encerrado sumariamente para este trecho e o **HeapSort** assume o controle do bloco para garantir o pior caso ótimo.

#### Execução do HeapSort no Bloco Crítico

1. **Transformação em Max-Heap:** O array é rearranjado em uma estrutura de árvore binária lógica através de varreduras descendentes (*siftDown*), posicionando o maior elemento na raiz.
    * **Estado do bloco como Max-Heap:** `[15, 14, 12, 11, 13]`

2. **Ordenação por Extração:** O HeapSort troca repetidamente a raiz (maior valor) com o elemento da última posição ativa do bloco, reconstruindo o heap no espaço restante:
   ```text
   Troca 15 com 13 ──> Reorganiza Heap ──> [14, 13, 12, 11]     | [15]
   Troca 14 com 11 ──> Reorganiza Heap ──> [13, 11, 12]         | [14, 15]
   Troca 13 com 12 ──> Reorganiza Heap ──> [12, 11]             | [13, 14, 15]
   Troca 12 com 11 ──> Fim do Heap     ──> [11, 12, 13, 14, 15]
   ```

O HeapSort encerra sua atividade garantindo a ordenação do trecho estritamente dentro do limite de tempo linear-logarítmico estável: $O(n \log n)$.

* **Resultado do Subvetor R (Ordenado):** `[11, 12, 13, 14, 15, 16, 17, 18]`

---

### Estado Final do Array

Após todas as sub-rotinas finalizarem a execução de seus respectivos blocos de forma isolada e *in-place* diretamente na memória, o IntroSort encerra suas atividades. O array de 18 elementos está perfeitamente unificado, estável e ordenado.

#### Vetor Final Consolidado
```json
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18]
```