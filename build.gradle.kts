plugins {
  java
  application
}

group = "dev.mcenv"
version = "0.1.0"

repositories {
  mavenCentral()
  maven {
    url = uri("https://maven.pkg.github.com/mcenv/spy")
    credentials {
      username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_ACTOR")
      password = project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")
    }
  }
  maven("https://libraries.minecraft.net")
}

dependencies {
  implementation("dev.mcenv:spy:0.1.2")
  implementation("org.graalvm.truffle:truffle-api:22.3.2")
}

tasks.compileJava {
  options.release.set(17)
}

application {
  mainClass.set("dev.mcenv.truffle.Main")
}
