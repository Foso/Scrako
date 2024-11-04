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
    val myVar = createVariable("myVar")
    ...
}
```

# Broadcasts

```kotlin
projectBuilder {
    val paint = createBroadcast("paint")
    ...
}
```

# All sprite lists

```kotlin
projectBuilder {
    val myVar = createGlobalList("myVar")
    ...
}
```

You can create a global variable in the scope of the projectBuilder

# Single sprite lists

```kotlin
scriptBuilder {
    val myVar = createList("myVar")
    ...
}
```


# Hello World

<p align="center">
  <img src ="https://raw.githubusercontent.com/Foso/Scrako/master/docs/hello.png"  />
</p>

```kotlin
projectBuilder {
    spriteBuilder("foo"){
        scriptBuilder {
            whenFlagClicked()
            say("Hello!")
        }
    }
}
```

# Costumes


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
