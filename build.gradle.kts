import org.jetbrains.kotlin.gradle.dsl.Coroutines

group = "name.kropp"
version = "0.1"

plugins {
  application
  kotlin("jvm") version "1.1.51"
}

application {
  mainClassName = "MainKt"
}

repositories {
  mavenCentral()
  maven("https://dl.bintray.com/kotlin/ktor")
  maven("https://dl.bintray.com/kotlin/kotlinx")
}

kotlin {
  experimental.coroutines = Coroutines.ENABLE
}

dependencies {
  compile(kotlin("stdlib"))
  compile("io.ktor", "ktor-server-netty", "0.9.0")
  compile("ch.qos.logback", "logback-classic", "1.2.1")
  compile("net.sf.biweekly", "biweekly", "0.6.1")

  testCompile("junit", "junit", "4.12")
}