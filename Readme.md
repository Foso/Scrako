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
    val myVar = getGlobalVariable("myVar")
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


