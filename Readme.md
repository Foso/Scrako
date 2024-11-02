# Scrako

# All sprite variables

```kotlin
projectBuilder {
    val myVar = getGlobalVariable("myVar")
    ...
}
```

You can create a global variable in the scope of the projectBuilder

# Single sprite variables

```kotlin
scriptBuilder {
    val myVar = getOrCreateVariable("myVar")
    ...
}
```

# Hello World

<p align="center">
  <img src ="https://raw.githubusercontent.com/Foso/Scrako/master/docs/hello.png"  />
</p>

```kotlin
scriptBuilder {
    whenFlagClicked()
    say("Hello!")
}
```


# Development tips
Scrako is only building the project file. 
I use TurboWarp Desktop to run the project.

This my setup for Mac but there should be similar commands for Windows and Linux.

```kotlin
private fun startTurboWarp(filePath: String) {
val processBuilder2 = ProcessBuilder("open", filePath)
processBuilder2.inheritIO()
val process2 = processBuilder2.start()
process2.waitFor()
}

private fun killTurboWarp() {
val processBuilder = ProcessBuilder("pkill", "-9", "TurboWarp")
processBuilder.inheritIO()
val process = processBuilder.start()
process.waitFor()
}
```