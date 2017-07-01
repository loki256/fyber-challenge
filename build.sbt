name := "fyber-challenge"

organization := "com.github.loki256"

version := "0.0.1"

scalaVersion := "2.11.8"

scalacOptions ++= Seq(
  "-target:jvm-1.8",
  "-encoding", "UTF-8",
  "-unchecked",
  "-deprecation",
  "-Xfuture",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-Ywarn-unused",
  "-Ywarn-unused-import"
)

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.1" % "test" withSources() withJavadoc()
)

initialCommands := "import com.github.loki256.fyberchallenge._"
