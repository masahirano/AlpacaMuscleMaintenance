package com.example.alpacamusclemaintenance.testing

interface Robot<out T : ResultRobot> {

    infix fun verifyThat(verification: T.() -> Unit)
}

interface ResultRobot
