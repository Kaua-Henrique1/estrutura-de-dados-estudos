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
  - Bogo Sort: muito pequenos (por exemplo, n = 3, 4, 5, 6, 8). Bogo é impossível para n grandes.
  - IntroSort: n = 10, 100, 1_000, 10_000, 100_000 (ajustar conforme hardware).

- Métricas a recolher:
  - tempo de execução (alta resolução)
  - número de comparações
  - número de trocas (ou permutações)
  - número de iterações/recursões

- Repetições e estatística:
  - executar cada experimento várias vezes (ex.: k = 30) e reportar média e desvio padrão.
  - para Bogo Sort, relatar também a distribuição (min, mediana, max) porque a média pode ser instável.

- Seeds e reprodutibilidade:
  - registrar e salvar as seeds usadas para gerar entradas aleatórias.

## Relatório e apresentação (entregáveis)

Itens mínimos a entregar:

- Código-fonte organizado (pasta `src/`), com README e instruções específicas.
- Scripts de experimentos e arquivos de resultados (CSV).
- Relatório em PDF (ou documento) cobrindo:
  1. Introdução e objetivo
  2. Metodologia
  3. Pseudocódigo e pontos-chave da implementação
  4. Resultados (tabelas e gráficos)
  5. Análise comparativa (interpretação dos resultados vs teoria)
  6. Conclusão e limitações
  7. Referências

- Slides (10–12 slides recomendados):
  - problema e motivação
  - métodos (pseudocódigo resumido)
  - principais resultados (gráficos)
  - discussão e conclusão

## Divisão de trabalho sugerida (dupla)

- Membro A:
  - pesquisa teórica e pseudocódigo, implementação e testes de Bogo Sort, rascunho do relatório (metodologia + pseudocódigo).
- Membro B:
  - pesquisa sobre IntroSort, implementação e testes de IntroSort, geração de gráficos e análise dos resultados.
- Ambos:
  - revisão do relatório e slides, ensaio da apresentação.

## Checklist (pré-entrega)

- [ ] Implementações funcionando e comentadas
- [ ] Scripts de benchmark reprodutíveis
- [ ] Resultados salvos em CSV
- [ ] Gráficos incluídos no relatório
- [ ] Relatório em PDF e slides prontos

## Estrutura de pastas sugerida

- src/
  - bogo/
  - introsort/
- experiments/
  - run.java
- results/
- docs/
  - relatório.md

## Referências e leituras recomendadas

- Documentação de algoritmos e livros-texto (por exemplo, Cormen et al. — Introduction to Algorithms).
- Artigos e posts sobre IntroSort e análise de QuickSort/HeapSort.

---
