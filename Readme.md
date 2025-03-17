# Scrako

Kotlin library/DSL for text based generation of Scratch (lang) projects

## Show some :heart: and star the repo to support the project

[![GitHub stars](https://img.shields.io/github/stars/Foso/Scrako.svg?style=social&label=Star)](https://github.com/Foso/Scrako) [![GitHub forks](https://img.shields.io/github/forks/Foso/Scrako.svg?style=social&label=Fork)](https://github.com/Foso/Scrako/fork)

## Scrako Packages

| Project     |                                                                                    Version                                                                                    |
|-------------|:-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
| Scrako Core | [![Maven Central](https://img.shields.io/maven-central/v/de.jensklingenberg.scrako/scrako-core)](https://central.sonatype.com/artifact/de.jensklingenberg.scrako/scrako-core) |
| Scratch3 | [![Maven Central](https://img.shields.io/maven-central/v/de.jensklingenberg.scrako/scratch3)](https://central.sonatype.com/artifact/de.jensklingenberg.scrako/scratch3) |

# Hello World

You can also find a example project in the examples folder

<p align="center">
  <img src ="https://raw.githubusercontent.com/Foso/Scrako/main/docs/hello.png"  />
</p>

```kotlin
projectBuilder {
    spriteBuilder("foo") {
        scriptBuilder {
            whenFlagClicked()
            say("Hello!")
        }
    }
}
```

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

# Custom blocks

## Define custom block

```kotlin
define(
    name = "foo",
    withoutRefresh = true,
    arguments = listOf(Argument("bar", ArgumentType.NUMBER_OR_TEXT), Argument("baz", ArgumentType.BOOLEAN))
) {
// your code here
}
```

## Get arguments

<p align="center">
  <img src ="https://raw.githubusercontent.com/Foso/Scrako/main/docs/img.png"  />
</p>

```kotlin
define(
    "foo",
    withoutRefresh = true,
    arguments = listOf(Argument("bar", ArgumentType.NUMBER_OR_TEXT), Argument("baz", ArgumentType.BOOLEAN))
) {
    val (bar, baz) = getArgs()
    say(bar)
    say(baz)
}
```

Use destructuring to get the arguments from getArgs()

## Call custom block

```kotlin
call("foo", listOf(StringBlock("Hello"), StringBlock("World") eq "true"))
```

# Development tips

Scrako is only building the project file.
I use TurboWarp Desktop to run the project.
https://desktop.turbowarp.org/
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

# Dependencies

* scratch3 - Contains all blocks from Scratch 3.0 + pen extensions
* turbo-warp - Contains scratch3 + some turbo-warp blocks
