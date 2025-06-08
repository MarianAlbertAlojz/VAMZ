package com.example.fitnessflowapp.navigation


/**
 * enum trieda reprezentujuca jednotlive kroky uvodneho nastavenia -setupu
 *
 * kazdy krok ma svoju  route a index, ktory sa pouziva
 * pri nacitavani obsahu (title, description) zo SetupPages
 *
 * @param route navigacna hodnota pre kazdu obrazovku
 * @param pageIndex index pouzivany na vybranie obsahu z getPages
 */
enum class SetupStep(val route: String, val pageIndex: Int) {
    Setup("setup", 0),
    Gender("setup_gender", 1),
    Age("setup_age", 2),
    Weight("setup_weight", 3),
    Height("setup_height", 4),
    Goal("setup_goal", 5),
    Activity("setup_activity", 6),
    Profile("setup_profile", 7)
}