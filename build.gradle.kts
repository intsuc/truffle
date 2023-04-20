plugins {
  java
}

group = "dev.mcenv"
version = "0.1.0"

repositories {
  mavenCentral()
}

dependencies {
}

tasks.compileJava {
  options.release.set(17)
}
