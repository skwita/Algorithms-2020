package lesson2;

import kotlin.NotImplementedError;
import kotlin.Pair;

import java.util.Arrays;

@SuppressWarnings("unused")
public class JavaAlgorithms {
    /**
     * Получение наибольшей прибыли (она же -- поиск максимального подмассива)
     * Простая
     *
     * Во входном файле с именем inputName перечислены цены на акции компании в различные (возрастающие) моменты времени
     * (каждая цена идёт с новой строки). Цена -- это целое положительное число. Пример:
     *
     * 201
     * 196
     * 190
     * 198
     * 187
     * 194
     * 193
     * 185
     *
     * Выбрать два момента времени, первый из них для покупки акций, а второй для продажи, с тем, чтобы разница
     * между ценой продажи и ценой покупки была максимально большой. Второй момент должен быть раньше первого.
     * Вернуть пару из двух моментов.
     * Каждый момент обозначается целым числом -- номер строки во входном файле, нумерация с единицы.
     * Например, для приведённого выше файла результат должен быть Pair(3, 4)
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public Pair<Integer, Integer> optimizeBuyAndSell(String inputName) {
        throw new NotImplementedError();
    }

    /**
     * Задача Иосифа Флафия.
     * Простая
     *
     * Образовав круг, стоят menNumber человек, пронумерованных от 1 до menNumber.
     *
     * 1 2 3
     * 8   4
     * 7 6 5
     *
     * Мы считаем от 1 до choiceInterval (например, до 5), начиная с 1-го человека по кругу.
     * Человек, на котором остановился счёт, выбывает.
     *
     * 1 2 3
     * 8   4
     * 7 6 х
     *
     * Далее счёт продолжается со следующего человека, также от 1 до choiceInterval.
     * Выбывшие при счёте пропускаются, и человек, на котором остановился счёт, выбывает.
     *
     * 1 х 3
     * 8   4
     * 7 6 Х
     *
     * Процедура повторяется, пока не останется один человек. Требуется вернуть его номер (в данном случае 3).
     *
     * 1 Х 3
     * х   4
     * 7 6 Х
     *
     * 1 Х 3
     * Х   4
     * х 6 Х
     *
     * х Х 3
     * Х   4
     * Х 6 Х
     *
     * Х Х 3
     * Х   х
     * Х 6 Х
     *
     * Х Х 3
     * Х   Х
     * Х х Х
     *
     * Общий комментарий: решение из Википедии для этой задачи принимается,
     * но приветствуется попытка решить её самостоятельно.
     */
    static public int josephTask(int menNumber, int choiceInterval) { throw new NotImplementedError(); }

    /**
     * Наибольшая общая подстрока.
     * Средняя
     *
     * Дано две строки, например ОБСЕРВАТОРИЯ и КОНСЕРВАТОРЫ.
     * Найти их самую длинную общую подстроку -- в примере это СЕРВАТОР.
     * Если общих подстрок нет, вернуть пустую строку.
     * При сравнении подстрок, регистр символов *имеет* значение.
     * Если имеется несколько самых длинных общих подстрок одной длины,
     * вернуть ту из них, которая встречается раньше в строке first.
     */
    static public String longestCommonSubstring(String first, String second) {
        //трудоемкость и ресурсоемкость - O(KN) (K - длина первой строки, N - длина второй)
        int[][] substrings = new int[first.length()][second.length()];
        int maxLength = 0;
        StringBuilder substring = new StringBuilder();

        for (int i = 0; i < first.length(); i++){
            for (int j = 0; j < second.length(); j++){
                boolean isEquals = first.charAt(i) == second.charAt(j);
                if ((i == 0 || j == 0) && isEquals){
                    substrings[i][j] = 1;
                    break;
                }
                if (isEquals) {
                    substrings[i][j] = substrings[i - 1][j - 1] + 1;
                }
                if (substrings[i][j] >= maxLength) maxLength = substrings[i][j];
            }
        }

        for (int i = 0; i < first.length(); i++){
            for (int j = 0; j < second.length(); j++){
                if (substrings[i][j] == maxLength) {
                    int counter = maxLength - 1;
                    while (counter >= 0) {
                        substring.append(first.charAt(i - counter));
                        counter--;
                    }
                    return substring.toString();
                }
            }
        }
        return "";
    }

    /**
     * Число простых чисел в интервале
     * Простая
     *
     * Рассчитать количество простых чисел в интервале от 1 до limit (включительно).
     * Если limit <= 1, вернуть результат 0.
     *
     * Справка: простым считается число, которое делится нацело только на 1 и на себя.
     * Единица простым числом не считается.
     */
    static public int calcPrimesNumber(int limit) {
        //трудоемкость - O(n log log n) ресурскоемкость - O(n)
        if (limit <= 1) return 0;

        boolean[] primes;
        primes = new boolean[limit + 1];
        Arrays.fill(primes, true);
        primes[0] = false;
        primes[1] = false;
        for (int i = 2; i * i <= limit; i++){
            if (primes[i]){
                for(int j = i * i; j <= limit; j += i){
                    primes[j] = false;
                }
            }
        }

        int count = 0;
        for (boolean prime: primes){
            if (prime) count++;
        }

        return count;
    }
}
