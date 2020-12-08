package lesson6;

import kotlin.NotImplementedError;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

@SuppressWarnings("unused")
public class JavaGraphTasks {
    /**
     * Эйлеров цикл.
     * Средняя
     *
     * Дан граф (получатель). Найти по нему любой Эйлеров цикл.
     * Если в графе нет Эйлеровых циклов, вернуть пустой список.
     * Соседние дуги в списке-результате должны быть инцидентны друг другу,
     * а первая дуга в списке инцидентна последней.
     * Длина списка, если он не пуст, должна быть равна количеству дуг в графе.
     * Веса дуг никак не учитываются.
     *
     * Пример:
     *
     *      G -- H
     *      |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     *
     * Вариант ответа: A, E, J, K, D, C, H, G, B, C, I, F, B, A
     *
     * Справка: Эйлеров цикл -- это цикл, проходящий через все рёбра
     * связного графа ровно по одному разу
     */
    public static List<Graph.Edge> findEulerLoop(Graph graph) {
        throw new NotImplementedError();
    }

    /**
     * Минимальное остовное дерево.
     * Средняя
     *
     * Дан связный граф (получатель). Найти по нему минимальное остовное дерево.
     * Если есть несколько минимальных остовных деревьев с одинаковым числом дуг,
     * вернуть любое из них. Веса дуг не учитывать.
     *
     * Пример:
     *
     *      G -- H
     *      |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     *
     * Ответ:
     *
     *      G    H
     *      |    |
     * A -- B -- C -- D
     * |    |    |
     * E    F    I
     * |
     * J ------------ K
     */
    public static Graph minimumSpanningTree(Graph graph) {
        throw new NotImplementedError();
    }

    /**
     * Максимальное независимое множество вершин в графе без циклов.
     * Сложная
     *
     * Дан граф без циклов (получатель), например
     *
     *      G -- H -- J
     *      |
     * A -- B -- D
     * |         |
     * C -- F    I
     * |
     * E
     *
     * Найти в нём самое большое независимое множество вершин и вернуть его.
     * Никакая пара вершин в независимом множестве не должна быть связана ребром.
     *
     * Если самых больших множеств несколько, приоритет имеет то из них,
     * в котором вершины расположены раньше во множестве this.vertices (начиная с первых).
     *
     * В данном случае ответ (A, E, F, D, G, J)
     *
     * Если на входе граф с циклами, бросить IllegalArgumentException
     *
     * Эта задача может быть зачтена за пятый и шестой урок одновременно
     */

    //Ресурсоемкость = O(N); Трудоемкость = O(N^2), где N - количество вершин

    public static Set<Graph.Vertex> largestIndependentVertexSet(Graph graph) {
        Set<Set<Graph.Vertex>> independentVertexSets = new HashSet<>(); //set для хранения множеств независимых вершин

        Set<Graph.Vertex> result = new HashSet<>();

        for (Graph.Vertex vertexFirst : graph.getVertices()){
            Set<Graph.Vertex> independentVertexes = new HashSet<>(); //set для хранения независимых вершин в текущем цикле
            Set<Graph.Vertex> usedNeighbour = new HashSet<>(); //set для вершин, соседних с уже добавленными
            for (Graph.Vertex vertexSecond : graph.getVertices()){
                //добавляет, если вершины соседние, и вторая вершина еще не занята соседними
                if (!graph.getNeighbors(vertexFirst).contains(vertexSecond) && !usedNeighbour.contains(vertexSecond)) {
                    independentVertexes.add(vertexSecond); //добавляет вершину в текущую последовательность
                    usedNeighbour.addAll(graph.getNeighbors(vertexSecond)); //добавляет соседние вершины, как запрещенные
                }
            }
            independentVertexSets.add(independentVertexes);
        }

        for (Set<Graph.Vertex> set : independentVertexSets) { //поиск наибольшего среди множеств
            if (set.size() > result.size()){
                result = set;
            }
        }
        return result;
    }

    /**
     * Наидлиннейший простой путь.
     * Сложная
     *
     * Дан граф (получатель). Найти в нём простой путь, включающий максимальное количество рёбер.
     * Простым считается путь, вершины в котором не повторяются.
     * Если таких путей несколько, вернуть любой из них.
     *
     * Пример:
     *
     *      G -- H
     *      |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     *
     * Ответ: A, E, J, K, D, C, H, G, B, F, I
     */

    //Трудоемкость = O(V!), Ресурсоемкость = O(V^2), где V - количество вершин
    public static Path longestSimplePath(Graph graph) {
        Set<Graph.Vertex> vertices = graph.getVertices();
        Stack<Path> allPaths = new Stack<>();
        for (Graph.Vertex vertex: vertices) allPaths.push(new Path(vertex)); //все пути

        Path longestPath = new Path();
        int maxLength = 0;
        while(!allPaths.isEmpty()){
            Path tempPath = allPaths.pop();
            if (tempPath.getLength() > maxLength){
                longestPath = tempPath;
                maxLength = longestPath.getLength();
            }
            Set<Graph.Vertex> neighbours = graph.getNeighbors(tempPath.getVertices().get(tempPath.getLength())); //соседи последней вершины
            for (Graph.Vertex neighbour : neighbours){
                if(!tempPath.contains(neighbour)) allPaths.push(new Path(tempPath, graph, neighbour));
            }
        }
        return longestPath;
    }


    /**
     * Балда
     * Сложная
     *
     * Задача хоть и не использует граф напрямую, но решение базируется на тех же алгоритмах -
     * поэтому задача присутствует в этом разделе
     *
     * В файле с именем inputName задана матрица из букв в следующем формате
     * (отдельные буквы в ряду разделены пробелами):
     *
     * И Т Ы Н
     * К Р А Н
     * А К В А
     *
     * В аргументе words содержится множество слов для поиска, например,
     * ТРАВА, КРАН, АКВА, НАРТЫ, РАК.
     *
     * Попытаться найти каждое из слов в матрице букв, используя правила игры БАЛДА,
     * и вернуть множество найденных слов. В данном случае:
     * ТРАВА, КРАН, АКВА, НАРТЫ
     *
     * И т Ы Н     И т ы Н
     * К р а Н     К р а н
     * А К в а     А К В А
     *
     * Все слова и буквы -- русские или английские, прописные.
     * В файле буквы разделены пробелами, строки -- переносами строк.
     * Остальные символы ни в файле, ни в словах не допускаются.
     */
    static public Set<String> baldaSearcher(String inputName, Set<String> words) {
        throw new NotImplementedError();
    }
}
