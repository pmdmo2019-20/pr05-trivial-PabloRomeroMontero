package es.iessaladillo.pedrojoya.pr05_trivial.base

import es.iessaladillo.pedrojoya.pr05_trivial.ui.about.AboutFragment

class Database() {


//    preguntas del quiz sacadas del siguiente enlace:
//    https://www.tutorialspoint.com/android/android_online_quiz.htm


    private val questionsList: List<Question> = listOf(
        Question(
            "What is Android?",
            "Android is a stack of software's for mobility",
            "Google mobile device name",
            "Virtual machine",
            "None of the above",
            1
        ),
        Question(
            "How many sizes are supported by Android?",
            "Android supported all sizes",
            "Android does not support all sizes",
            "Android supports small,normal, large and extra-large sizes",
            "Size is undefined in android",
            3
        ),
        Question(
            "What are the return values of onStartCommand() in android services?",
            " START_STICKY",
            "START_NOT_STICKY",
            "START_REDELIVER_INTENT",
            " All of the above",
            4
        ),
        Question(
            "What is the life cycle of broadcast receivers in android?",
            "send intent()",
            "onRecieve()",
            "implicitBroadcast()",
            "sendBroadcast(), sendOrderBroadcast(), and sendStickyBroadcast()",
            2
        ),
        Question(
            "Which features are considered while creating android application?",
            "Screen Size",
            "Input configuration",
            "Platform Version",
            "All of above",
            4
        ),
        Question(
            "What is APK in android?",
            "Android packages",
            "Android pack",
            "Android packaging kit",
            "None of the above",
            3
        ),
        Question(
            "How to fix crash using log cat in android?",
            "Gmail",
            "log cat contains the exception name along with the line number",
            "Google search",
            "None of the above.",
            2
        ),
        Question(
            "What is a GCM in android?",
            " Goggle Could Messaging for chrome",
            " Goggle Count Messaging",
            "Goggle Message pack",
            "None of the above",
            1
        ),
        Question(
            " Is it mandatory to call onCreate() and onStart() in android?",
            "No, we can write the program without writing onCreate() and onStart()",
            "Yes, we should call onCreate() and onStart() to write the program",
            "At least we need to call onCreate() once",
            "None of the above",
            1
        ),
        Question(
            "What are the main components in android?",
            "Activity",
            "Services",
            "Broadcast Receiver",
            "All of above",
            4
        )
    )

    companion object {
        fun newInstance(): Database =
            Database()
    }


    fun getQuestions(numberQuestions: Int): MutableList<Question> {
        val shuffledList: List<Question> = questionsList.shuffled()
        val listaAEnviar: MutableList<Question> = mutableListOf()
        var counter = 0
        for (question in shuffledList) {
            listaAEnviar.add(question)
            counter++
            if (counter == numberQuestions) break
        }
        return listaAEnviar
    }


}