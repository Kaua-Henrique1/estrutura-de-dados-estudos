# Análise Teórica: Bogo Sort 

## 1. Introdução e Inspiração
* **O Algoritmo:** O Bogo Sort (também conhecido como *Stupid Sort* ou *Monkey Sort*) é um algoritmo de ordenação "meme", baseado no paradigma de "gerar e testar". 
* **Inspiração Teórica:** A sua grande inspiração teórica é o **Teorema do Macaco Infinito**, que sugere que um macaco digitando aleatoriamente em um teclado por um tempo infinito eventualmente produzirá um texto com sentido. No caso do algoritmo, ele embaralha os elementos do vetor aleatoriamente e verifica se ficaram ordenados. Se não, embaralha tudo de novo.
* **Origem e Nome:** Surgiu na comunidade acadêmica de computação, possivelmente na Carnegie Mellon University (CMU) entre as décadas de 1970 e 1980. O termo *Bogo Sort* vem da junção das palavras inglesas *"bogus"* (falso, ruim, sem sentido) e *"sort"* (ordenação).

---

## 2. Propriedades Técnicas do Algoritmo

* **In-Place:** **Sim.** O algoritmo reorganiza os elementos dentro do próprio vetor original, utilizando uma quantidade mínima e constante de memória extra $O(1)$.
* **Estabilidade:** **Não Estável.** O processo de embaralhamento rearranja as posições de forma totalmente aleatória, sem garantia de manter a ordem original de chaves idênticas.
* **Adaptativo:** **Não.** O algoritmo não aprende com as iterações passadas nem tira partido de subvetores já ordenados.

---

## 3. Matriz de Complexidade Assintótica

| Algoritmo | Melhor Caso | Caso Médio | Pior Caso | Espaço Auxiliar | Estabilidade | In-Place |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **Bogo Sort** | $O(n)$ | $O(n \cdot n!)$ | $O(\infty)$ | $O(1)$ | Instável | Sim |

* **Melhor Caso $O(n)$:** Ocorre se o vetor já for entregue totalmente ordenado. Faz apenas uma verificação e para.
* **Caso Médio $O(n \cdot n!)$:** A probabilidade de acertar na permutação correta por pura sorte exige um número gigantesco de tentativas, tornando-o inviável.
* **Pior Caso $O(\infty)$:** Como o embaralhamento é aleatório e sem memória, teoricamente ele pode rodar ao infinito sem nunca acertar a ordem.

---

## 4. Variante
* **Independência:** O Bogo Sort não é uma variante de nenhum algoritmo tradicional de ordenação. Trata-se de um algoritmo independente que utiliza embaralhamentos aleatórios para tentar encontrar uma sequência ordenada. 
* **Derivados:** Apesar disso, existem algoritmos derivados dele, como o **Bozo Sort**, que segue a mesma filosofia baseada em aleatoriedade (porém, em vez de embaralhar tudo, escolhe apenas dois elementos aleatórios e troca-os de lugar repetidamente).

---

## 5. Limitações e Aplicações
O Bogo Sort possui utilidade prática nula em sistemas comerciais. No entanto, é uma excelente ferramenta:
* Para ilustrar graficamente o impacto catastrófico de uma complexidade fatorial nas aulas de Estruturas de Dados.
* Para atuar como teste de estresse (*benchmark*) extremo em processadores.

## 5. Implementação em JAVA

``` java
package bogosort;

import java.util.Random;

public class BogoSort {
    public static void sort(int[] v) {
        if (v == null || v.length <= 1) return;

        Random rand = new Random();
        boolean ordenado = false;

        while (!ordenado) {

            ordenado = true;
            for (int i = 1; i < v.length; i++) {
                if (v[i] < v[i - 1]) {
                    ordenado = false;
                    break;
                }
            }

            if (!ordenado) {
                for (int i = 0; i < v.length; i++) {
                    int randomIndex = rand.nextInt(v.length);
                    int temp = v[i];
                    v[i] = v[randomIndex];
                    v[randomIndex] = temp;
                }
            }
        }
    }
}
