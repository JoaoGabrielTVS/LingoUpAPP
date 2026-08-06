package com.example.lingoup.viewmodel

fun varrer(perguntas:String): Array<String> {
    var tick = 0
    var pergunta1 = ""
    var pergunta2 = ""
    var pergunta3 = ""
    var pergunta4 = ""
    var pergunta5 = ""
    for (i in perguntas) {
        if (i == '/') {
            tick++;

        } else {
            when (tick) {
                0 -> pergunta1 += i
                1 -> pergunta2 += i
                2 -> pergunta3 += i
                3 -> pergunta4 += i
                4 -> pergunta5 += i
            }
        }

    }
    return arrayOf(pergunta1, pergunta2, pergunta3, pergunta4, pergunta5)


}