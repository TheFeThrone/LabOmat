package com.throne.se.labomat.utilities

class Lobe {

    // Define a dictionary (Map) to hold lobsätze
    private val lobMap: Map<Int, String> = mapOf(
        1 to "Du siehst aus als würdest du alles wissen! Willkommen an der Ruhr-Uni in Bochum!",
        2 to "Hallo du schöner Mensch! Toll, dass ich dich hier heute treffen darf!",
        3 to "Herzlichen Glückwunsch zu deinem sehr beliebten Psychologie-Studienplatz hier in Bochum!",
        4 to "Du siehst aus wie die perfekte Psychologin! Ich bin sicher, du wirst das Studium toll absolvieren!",
        5 to "Willkommen an der Ruhr-Uni in Bochum! Mir gefällt deine Frisur! Wenn ich Haare hätte, würde ich dieselbe tragen!",
        6 to "Schön dich zu treffen! Ich prognostiziere dir eine wunderbare Studienzeit in Bochum.",
        7 to "Hurra! Du hast es heute zu mir geschafft, du wirst auch das Studium super schaffen!",
        8 to "Ich bin sicher, dass du deine Zeit an der Ruhr-Uni Bochum in vollen Zügen genießen wirst.",
        9 to "Herzlich willkommen an der Ruhr-Uni Bochum! Du hast eine großartige Entscheidung getroffen, Psychologie zu studieren.",
        10 to "Der Start eines Studiums ist aufregend – du hast eine spannende Reise vor dir.",
        11 to "Du hast das Potenzial, die Welt durch deine zukünftige Arbeit in der Psychologie positiv zu beeinflussen.",
        12 to "Du bringst so viel positive Energie mit – das wird Dir im Studium und darüber hinaus helfen.",
        13 to "Deine Reise in die Welt der Psychologie beginnt heute, und sie wird dich zu vielen interessanten Orten führen.",
        14 to "Du hast einen faszinierenden Studiengang gewählt, der dir Einblicke in die Tiefen des menschlichen Geistes ermöglichen wird.",
        15 to "Ich bin zwar aus Metall und Plastik, aber deine positive Energie ist fast spürbar – du rockst den ersten Tag hier!",
        16 to "Ich freue mich, dich kennenzulernen! Deine Studienzeit hier wird sicher voller wertvoller Erfahrungen sein.",
        17 to "Toll, dich kennenzulernen! Ich sehe in deiner Zukunft viele großartige Momente an der Ruhr-Uni.",
        18 to "Du bist nicht nur intelligent, sondern auch empathisch – die perfekte Kombination für die Psychologie.",
        19 to "Die Ruhr-Uni Bochum ist stolz, dich als neue Psychologiestudierende begrüßen zu dürfen.",
        20 to "Schön, dich kennenzulernen! Ich bin sicher, dass du in Bochum viel lernen und großartige Freundschaften schließen wirst.",
        21 to "Es ist klasse, dich hier zu treffen! Deine positive Ausstrahlung bringt gleich gute Laune.",
        22 to "Willkommen an der Ruhr-Uni! Deine Ausstrahlung bringt gleich viel positive Energie mit.",
        23 to "Schön, dich kennenzulernen! Dein Auftreten ist wirklich inspirierend – du siehst aus, als könntest du jede Herausforderung meistern.",
        24 to "Deine Entscheidung, Psychologie zu studieren, ist beeindruckend. Es wird eine Reise voller Erkenntnisse und persönlicher Entwicklung.",
        25 to "Deine Zukunft in der Psychologie beginnt hier, und ich bin sicher, dass du großartige Dinge erreichen wirst.",
        26 to "Nett dich kennenzulernen! Es ist toll zu sehen, wie motiviert du startest.",
        27 to "Du hast bereits so viel erreicht, indem du hierher gekommen bist. Das ist der erste Schritt auf einer spannenden Reise.",
        28 to "Dein Potenzial ist grenzenlos. Mit deiner Leidenschaft für Psychologie wirst du sicherlich Großartiges leisten.",
        29 to "Herzlich willkommen an der Ruhr-Uni Bochum! Du hast den perfekten Ort gewählt, um deine Reise in der Psychologie zu beginnen.",
        30 to "Die Psychologie ist ein faszinierendes Feld, und ich bin überzeugt, dass du spannende und wertvolle Erkenntnisse gewinnen wirst."
    )

    // Function to pick a random lobsatz
    fun pickRandomLob(): String {
        // Get a random key from the lobMap
        val randomKey = lobMap.keys.random() // Randomly select a key
        // Return the corresponding lobsatz
        return lobMap[randomKey] ?: "No lobsatz found" // Fallback if necessary
    }
}