## Description

Challenge for the Position of Scala Developer at Fyber.

## Compiling

This project uses sbt and scala 2.11

To compile and run unit tests run

        sbt compile test

## Running

To run the code using sbt you can either run sbt interactively and then run program with arguments:

        sbt
        run data_scala.txt

or run it with one command:

        sbt "run-main com.github.loki256.fyberchallenge.Main data_scala.txt"
