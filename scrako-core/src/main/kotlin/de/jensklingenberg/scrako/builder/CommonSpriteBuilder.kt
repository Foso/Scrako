package de.jensklingenberg.scrako.builder

import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ScratchList
import de.jensklingenberg.scrako.common.ScratchVariable
import de.jensklingenberg.scrako.common.backdrop
import de.jensklingenberg.scrako.common.createTarget
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Costume
import de.jensklingenberg.scrako.model.Sound
import de.jensklingenberg.scrako.model.Target
import java.io.File
import java.util.UUID


open class CommonSpriteBuilder(open val name: String) {
    internal val commonScriptBuilders = mutableListOf<CommonScriptBuilder>()

    private val variableMap = mutableMapOf<String, UUID>()
    private val listMap = mutableMapOf<String, ScratchList>()
    var config: Config = Config()

    internal fun addVariable(name: String) {
        variableMap[name] = UUID.randomUUID()
    }

    internal fun addList(list: ScratchList) {
        listMap[list.name] = list
    }

    internal fun build(context: Context, isStage: Boolean): Target {
        val functionsMap = mutableListOf<ArgumentDataHolder>()
        commonScriptBuilders.forEach {
            it.functionsMap.forEach { function ->
                function.value.forEach { argument2 ->
                    functionsMap.add(
                        ArgumentDataHolder(
                            argument2.name,
                            UUID.randomUUID().toString(),
                            function.key,
                            argument2.type
                        )
                    )
                }
            }
        }

        val nodeListList = commonScriptBuilders.map { it.getNodes() }

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

        val targetLists = when (isStage) {
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
        val blocks = createBlocksMap(
            nodeListList,
            Context(
                variableMap = allVariables,
                lists = allLists,
                functions = functionsMap,
                broadcastMap = context.broadcastMap,
                inputPath = context.inputPath,
            )
        )

        val costumesList = config.costumes.ifEmpty { listOf(backdrop) }.map {
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

        val soundsList = config.sounds.map {
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
            layerOrder = if (isStage) 0 else context.layer,
            rotationStyle = "all around",
            volume = 100
        )
    }


    private fun createBlocksMap(blockSpecs: List<List<Node>>, context: Context): Map<String, BlockFull> {
        return blockSpecs.map {

            val blockFullMap = mutableMapOf<String, BlockFull>()
            val uuids = it.map { UUID.randomUUID().toString() }
            it.forEachIndexed { index, blockSpec ->

                val parent = when {
                    index == 0 -> null
                    else -> uuids[index - 1]
                }

                val nextNode = if (index != it.lastIndex) uuids[index + 1] else null

                blockSpec.visit(blockFullMap, parent, uuids[index], nextNode, context)

            }
            blockFullMap
        }.flatMap { it.toList() }.toMap()
    }
}

class StageSpriteBuilder : CommonSpriteBuilder("Stage")
class SpriteBuilder(override val name: String) : CommonSpriteBuilder(name)

fun SpriteBuilder.scriptBuilder(builder: SpriteScriptBuilder.() -> Unit): CommonScriptBuilder {
    val spriteScriptBuilder = SpriteScriptBuilder()
    builder.invoke(spriteScriptBuilder)
    commonScriptBuilders.add(spriteScriptBuilder)
    return spriteScriptBuilder
}

fun StageSpriteBuilder.scriptBuilder(builder: StageScriptBuilder.() -> Unit): CommonScriptBuilder {
    val spriteScriptBuilder = StageScriptBuilder()
    builder.invoke(spriteScriptBuilder)
    commonScriptBuilders.add(spriteScriptBuilder)
    return spriteScriptBuilder
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

