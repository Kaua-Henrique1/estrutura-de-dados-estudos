# estrutura-de-dados-estudos

Este repositório contém o material de estudo e os artefatos gerados na implementação, testes e documentação dos algoritmos Bogo Sort e IntroSort.

## Objetivo

Organizar as pesquisas, implementações e experimentos necessários para produzir um relatório e uma apresentação comparativa entre Bogo Sort e IntroSort. O README descreve o que pesquisar, como executar experimentos reprodutíveis e o que entregar no relatório/slides.

## Algoritmos em estudo
- Bogo Sort
- IntroSort

## O que pesquisar para cada algoritmo

Para ambos os algoritmos, cada membro da dupla deve coletar e documentar os itens abaixo (padrão de saída: arquivo Markdown ou nota):

- Definição e origem (histórico rápido).
- Pseudocódigo claro e comentado.
- Complexidade (pior caso, caso médio, melhor caso) e justificação matemática/intuitiva.
- Propriedades: estável? in-place? adaptativo? online/offline?
- Estrutura interna (por exemplo, IntroSort: quando trocar de QuickSort para HeapSort e uso de Insertion Sort para subarrays pequenos).
- Vantagens, limitações e possíveis usos (se existirem).
- Implementações de referência e bibliografia (livros, artigos, entradas de wikipédia, links).

## Experimentos e métricas (como executar)

Objetivo: comparar comportamento empírico e confirmar as previsões teóricas.

- Casos de teste (por tipo de entrada):
  - arrays ordenados
  - arrays inversamente ordenados
  - arrays aleatórios (com seed controlada)
  - arrays com muitos elementos duplicados

- Tamanhos sugeridos:
  - Bogo Sort: muito pequenos (por exemplo, n = 3, 4, 5, 10, 12). Bogo é impossível para n grandes.
  - IntroSort: n = 10, 100, 1_000, 10_000, 100_000 (ajustar conforme hardware).

- Repetições e estatística:
  - executar cada experimento várias vezes (ex.: k = 30) e reportar média e desvio padrão.
  - para Bogo Sort, relatar também a distribuição (min, mediana, max) porque a média pode ser instável.

---

## Como compilar o Projeto:
```bash
cd /home/jaua/Documents/dev/estrutura-de-dados-estudos/estrutura-de-dados-estudos
mvn -DskipTests compile```
```

## Como rodar Projeto:
```bash
java -cp target/classes:target/test-classes TestesSimplesTempo
```
