package de.jensklingenberg.scrako.builder

import de.jensklingenberg.scrako.common.MotionBlock
import de.jensklingenberg.scrako.common.ScratchList
import de.jensklingenberg.scrako.common.ScratchVariable
import de.jensklingenberg.scrako.common.createTarget
import de.jensklingenberg.scrako.model.Backdrop
import de.jensklingenberg.scrako.model.Costume
import de.jensklingenberg.scrako.model.Costume2
import de.jensklingenberg.scrako.model.Sound
import de.jensklingenberg.scrako.model.Sound2
import de.jensklingenberg.scrako.model.Target
import java.io.File
import java.util.UUID


open class CommonSpriteBuilder {
    internal val commonScriptBuilders = mutableListOf<CommonScriptBuilder>()
    internal var name: String = ""
    private val variableMap = mutableMapOf<String, UUID>()
    private val listMap = mutableMapOf<String, ScratchList>()
    private val costumesList = mutableListOf<Costume2>()
    private var sounds = mutableListOf<Sound2>()
    var config: Config = Config()

    fun addSounds(sound: List<Sound2>) {
        sounds.addAll(sound)
    }

    internal fun addVariable(name: String) {
        variableMap[name] = UUID.randomUUID()
    }

    internal fun addList(list: ScratchList) {
        listMap[list.name] = list
    }

    internal fun build(context: Context, isStage: Boolean): Target {
        if (costumesList.isEmpty()) {
            throw IllegalArgumentException("You need to add at least one costume for $name")
        }
        val functionsMap = mutableListOf<Argumenti>()
        commonScriptBuilders.forEach {
            it.functionsMap.forEach { function ->
                function.value.forEach { argument2 ->
                    functionsMap.add(
                        Argumenti(
                            argument2.name,
                            UUID.randomUUID().toString(),
                            function.key,
                            argument2.type
                        )
                    )
                }

            }

        }

        val nodeListList = commonScriptBuilders.map { it.childs }

        nodeListList.flatten().forEach {
            if (isStage && it is MotionBlock) {
                throw IllegalArgumentException("MotionBlock for Stage not allowed")
            }
        }

        val allVariables = variableMap + context.variableMap
        val allLists = listMap + context.lists

        val targetVariables = variableMap.map {
            if (context.variableMap.containsKey(it.key)) {
                it.key to null
            } else {
                it.key to it.value
            }
        }.toMap()
            .filter { it.value != null }
            .map { it.key to it.value!! }.toMap()

        val targetLists = when(isStage) {
            true -> allLists
            false -> listMap.map {
                if (context.lists.containsKey(it.key)) {
                    it.key to null
                } else {
                    it.key to it.value
                }
            }.toMap()
                .filter { it.value != null }
                .map { it.key to it.value!! }
                .toMap()
        }
        val blocks = createBlocks23(
            nodeListList,
            Context(
                variableMap = allVariables,
                lists = allLists,
                functions = functionsMap,
                broadcastMap = context.broadcastMap,
                inputPath = context.inputPath,
            )
        )

        val costumesList = costumesList.map {
            Costume(
                name = it.name,
                bitmapResolution = it.bitmapResolution,
                dataFormat = it.dataFormat,
                assetId = it.assetId ?: getFileMD5(File(context.inputPath + "/sprites/${it.name}.${it.dataFormat}")),
                rotationCenterX = it.rotationCenterX,
                rotationCenterY = it.rotationCenterY,
                isCustom = it.assetId == null
            )
        }

        val soundsList = sounds.map {
            Sound(
                name = it.name,
                assetId = it.assetId ?: getFileMD5(File(context.inputPath + "/sprites/${it.name}.${it.dataFormat}")),
                dataFormat = it.dataFormat,
                format = it.format,
                rate = it.rate,
                sampleCount = it.sampleCount,
                isCustom = it.assetId == null
            )
        }

        return createTarget(
            blocks = blocks,
            name = name,
            comments = emptyList(),
            lists = targetLists,
            variables = targetVariables,
            costumes = costumesList,
            broadcasts = context.broadcastMap,
            sounds = soundsList,
            size = config.size,
            x = config.posX,
            y = config.posY,
            visible = if (isStage) false else config.visible,
            direction = config.direction,
            isStage = isStage,
            layerOrder = if (isStage) 0 else 1
        )
    }

    internal fun addCostumeList(costume: Costume2) {
        costumesList.add(costume)
    }

}

class StageSpriteBuilder : CommonSpriteBuilder()
class SpriteBuilder : CommonSpriteBuilder()

fun SpriteBuilder.addCostumes(costume: List<Costume2>) {
    costume.forEach { addCostumeList(it) }
}

fun StageSpriteBuilder.addBackdrop(costume: List<Backdrop>) {
    costume.forEach { addCostumeList(it) }
}

fun CommonSpriteBuilder.createList(name: String, contents: List<String> = emptyList()): ScratchList {
    val element = ScratchList(name, contents)
    addList(element)
    return element
}

fun CommonSpriteBuilder.createVariable(name: String): ScratchVariable {
    val element = ScratchVariable(name)
    addVariable(name)
    return element
}

