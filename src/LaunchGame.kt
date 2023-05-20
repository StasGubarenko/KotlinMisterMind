fun main() {

    val inputCoder: String? = inputCoder()

    val size: Int? = inputCoder?.length

    val inputCodebreaker: String? = inputCodebreaker(size)

    compareTwoLetters(inputCoder, inputCodebreaker, size)


}

fun inputCoder(): String? {
    println("Codemaker, input any sequence of letters")
    return readLine()
}

fun inputCodebreaker(sizeOfInputtedLetters: Int?): String? {

    println("\nCodebreaker, input any sequence of letters consisting of $sizeOfInputtedLetters")

    //Переменная для хранения символов, которые ввел codebreaker
    var inputCodebreaker = readLine()

    //длина символов, которые были введены codebreaker
    var sizeOfInputtedCodebreakerLetters = inputCodebreaker?.length

    var switcher = true

    //Проверяем количество символов
    while (switcher) {
        if (sizeOfInputtedCodebreakerLetters == sizeOfInputtedLetters) {
            switcher = false
        } else {
            println("Sorry, you entered the wrong number of characters. You must enter $sizeOfInputtedLetters characters")
            inputCodebreaker = readLine()
            sizeOfInputtedCodebreakerLetters = inputCodebreaker?.length
        }
    }
    return inputCodebreaker
}

fun compareTwoLetters(inputCoder: String?, inputCoderbreaker: String?, size: Int?) {

    //Сравниваем введенные строки
    if (inputCoder.equals(inputCoderbreaker)) {
        println("Letters are equal. You win!")
        return
    }

    //Переменная, которая хранит символы, совпадающие по индексу
    val matchingLetters = matchingLetters(inputCoder, inputCoderbreaker)

    //Переменная, которая хранит символы, не совпадающие по индексу
    val notMatchingLetters = notMatchingLetters(inputCoder, inputCoderbreaker)

    inputResult(inputCoder, matchingLetters, notMatchingLetters, size)

}

fun matchingLetters(inputCoder: String?, inputCodebreaker: String?): Map<Int, Char> {
    //Переменная, которая хранит символы, совпадающие по индексу
    val matchingLetters = mutableMapOf<Int, Char>()

    for (indexCoderLetters in inputCoder?.indices!!) {
        for (indexKit in inputCodebreaker?.indices!!) {
            //Сравниваем индексы
            if (indexCoderLetters == indexKit) {
                //если индексы равны, то сравниваем символы
                if (inputCoder[indexCoderLetters] == inputCodebreaker[indexKit]) {

                    //если символы равны, то записываем их в переменную, которая хранит одинаковые символы по индексу
                    matchingLetters[indexKit] = inputCodebreaker[indexKit]
                }
            }
        }
    }
    return matchingLetters
}

fun notMatchingLetters(inputCoder: String?, inputCodebreaker: String?): MutableSet<Char> {

    //Переменная, которая хранит символы, не совпадающие по индексу
    val notMatchingLetters = mutableSetOf<Char>()

    for (indexCoderLetters in inputCoder?.indices!!) {
        for (indexKit in inputCodebreaker?.indices!!) {
            //Сравниваем индексы
            if (indexCoderLetters != indexKit) {
                //Если индексы не равны, то сравниваем символы
                if (inputCoder[indexCoderLetters] == inputCodebreaker[indexKit]) {
                    //если символы равны, то записываем их в переменную, которая хранит одинаковые символы по индексу
                    notMatchingLetters.add(inputCodebreaker[indexKit])
                }
            }
        }
    }
    return notMatchingLetters
}

fun inputResult(inputCoder: String?, matchingLetters: Map<Int, Char>, notMatchingLetters: MutableSet<Char>, size: Int?) {

    if (matchingLetters.isEmpty() && notMatchingLetters.size == 0) {

        println("Unfortunately, none of the letters match.")

        compareTwoLetters(inputCoder, inputCodebreaker(size), size)

    } else if (notMatchingLetters.size != 0 && matchingLetters.isEmpty()) {

        iterate(notMatchingLetters)

        compareTwoLetters(inputCoder, inputCodebreaker(size), size)

    } else if (notMatchingLetters.size == 0) {

        iterate(matchingLetters)

        compareTwoLetters(inputCoder, inputCodebreaker(size), size)

    } else {

        iterate(notMatchingLetters)

        iterate(matchingLetters)

        compareTwoLetters(inputCoder, inputCodebreaker(size), size)
    }
}

fun iterate(notMatchingLetters: MutableSet<Char>) {

    print("Letters that are present in the word that was entered by the coder : ")

    for (element in notMatchingLetters) {
        print("$element ")
    }

}

fun iterate(matchingLetters: Map<Int, Char>) {

    println("\nLetters that match by index : ")

    for ((key, value) in matchingLetters) {
        println("index is $key, letter is $value")
    }

}
