fun main() {

    val inputCoder: String? = inputCoder()

    val size: Int? = inputCoder?.length

    val inputCodebreaker: String? = inputCoderBreaker(size)

    compareTwoLetters(inputCoder, inputCodebreaker, size)


}

fun inputCoder(): String? {
    println("Codemaker, input any sequence of letters")
    return readLine()
}

fun inputCoderBreaker(sizeOfInputtedLetters: Int?): String? {

    println("\nCodebreaker, input any sequence of letters consisting of $sizeOfInputtedLetters")

    //Переменная для хранения символов, которые ввел codebreaker
    var inputDecoder = readLine()

    //длина символов, которые были введены codebreaker
    var sizeOfInputtedDecoderLetters = inputDecoder?.length

    var switcher = true

    //Проверяем количество символов
    while (switcher) {
        if (sizeOfInputtedDecoderLetters == sizeOfInputtedLetters) {
            switcher = false
        } else {
            println("Sorry, you entered the wrong number of characters. You must enter $sizeOfInputtedLetters characters")
            inputDecoder = readLine()
            sizeOfInputtedDecoderLetters = inputDecoder?.length
        }
    }
    return inputDecoder
}

fun compareTwoLetters(inputCoder: String?, inputCoderBreaker: String?, size: Int?) {

    //Сравниваем введенные строки
    if (inputCoder.equals(inputCoderBreaker)) {
        println("Letters are equal. You win!")
        return
    }

    //Переменная, которая хранит символы, совпадающие по индексу
    val saveIndexByLetters = saveIndexByLetters(inputCoder, inputCoderBreaker)

    //Переменная, которая хранит символы, не совпадающие по индексу
    val saveLettersNotIndex = saveLettersNotIndex(inputCoder, inputCoderBreaker)

    inputResult(inputCoder, saveIndexByLetters, saveLettersNotIndex, size)

}

fun saveIndexByLetters(inputCoder: String?, inputCoderBreaker: String?): Map<Int, Char> {
    //Переменная, которая хранит символы, совпадающие по индексу
    val list = mutableMapOf<Int, Char>()

    for (indexCoderLetters in inputCoder?.indices!!) {
        for (indexKit in inputCoderBreaker?.indices!!) {
            //Сравниваем индексы
            if (indexCoderLetters == indexKit) {
                //если индексы равны, то сравниваем символы
                if (inputCoder[indexCoderLetters] == inputCoderBreaker[indexKit]) {

                    //если символы равны, то записываем их в переменную, которая хранит одинаковые символы по индексу
                    list[indexKit] = inputCoderBreaker[indexKit]
                }
            }
        }
    }
    return list
}

fun saveLettersNotIndex(inputCoder: String?, inputCoderBreaker: String?): MutableSet<Char> {

    //Переменная, которая хранит символы, не совпадающие по индексу
    val list2 = mutableSetOf<Char>()

    for (indexCoderLetters in inputCoder?.indices!!) {
        for (indexKit in inputCoderBreaker?.indices!!) {
            //Сравниваем индексы
            if (indexCoderLetters != indexKit) {
                //Если индексы не равны, то сравниваем символы
                if (inputCoder[indexCoderLetters] == inputCoderBreaker[indexKit]) {
                    //если символы равны, то записываем их в переменную, которая хранит одинаковые символы по индексу
                    list2.add(inputCoderBreaker[indexKit])
                }
            }
        }
    }
    return list2
}

fun inputResult(inputCoder: String?, saveIndexByLetters: Map<Int, Char>, saveLettersNotIndex: MutableSet<Char>, size: Int?) {

    if (saveIndexByLetters.isEmpty() && saveLettersNotIndex.size == 0) {

        println("Unfortunately, none of the letters match.")

        compareTwoLetters(inputCoder, inputCoderBreaker(size), size)

    } else if (saveLettersNotIndex.size != 0 && saveIndexByLetters.isEmpty()) {

        iterate(saveLettersNotIndex)

        compareTwoLetters(inputCoder, inputCoderBreaker(size), size)

    } else if (saveLettersNotIndex.size == 0) {

        iterate(saveIndexByLetters)

        compareTwoLetters(inputCoder, inputCoderBreaker(size), size)

    } else {

        iterate(saveLettersNotIndex)

        iterate(saveIndexByLetters)

        compareTwoLetters(inputCoder, inputCoderBreaker(size), size)
    }
}

fun iterate(saveLettersNotIndex: MutableSet<Char>) {

    print("Letters that are present in the word that was entered by the coder : ")

    for (element in saveLettersNotIndex) {
        print("$element ")
    }

}

fun iterate(saveIndexByLetters: Map<Int, Char>) {

    println("\nLetters that match by index : ")

    for ((key, value) in saveIndexByLetters) {
        println("index is $key, letter is $value")
    }

}