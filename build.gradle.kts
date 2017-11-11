group = "name.kropp"
version = "0.1"

plugins {
  application
  kotlin("jvm") version "1.1.60"
}

repositories {
  mavenCentral()
}

dependencies {
  compile(kotlin("stdlib"))

  testCompile("junit", "junit", "4.12")
}