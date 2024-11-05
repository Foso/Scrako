package de.jensklingenberg.newimport

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.MUTABLE_MAP
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asClassName
import com.squareup.kotlinpoet.asTypeName
import de.jensklingenberg.example.newimport.SayImport
import de.jensklingenberg.newimport.argument.ArgStringNumber
import de.jensklingenberg.newimport.control.ForeverImport
import de.jensklingenberg.newimport.control.IfImport
import de.jensklingenberg.newimport.control.If_elseImport
import de.jensklingenberg.newimport.control.RepeatImport
import de.jensklingenberg.newimport.control.RepeatUntilImport
import de.jensklingenberg.newimport.data.AddToList
import de.jensklingenberg.newimport.data.ChangevariablebyImport
import de.jensklingenberg.newimport.data.DaySinceImport
import de.jensklingenberg.newimport.data.DeleteAllOfImport
import de.jensklingenberg.newimport.data.HidelistImport
import de.jensklingenberg.newimport.data.ItemoflistImport
import de.jensklingenberg.newimport.data.LengthoflistImport
import de.jensklingenberg.newimport.data.ReplaceItemImport
import de.jensklingenberg.newimport.data.SetVariableImport
import de.jensklingenberg.newimport.data.ShowListImport
import de.jensklingenberg.newimport.event.BroadcastAndWaitImport
import de.jensklingenberg.newimport.event.BroadcastImport
import de.jensklingenberg.newimport.event.WhenBroadcastReceived
import de.jensklingenberg.newimport.event.WhenFlag
import de.jensklingenberg.newimport.event.WhenKey
import de.jensklingenberg.newimport.looks.ClearGrahpiceffects
import de.jensklingenberg.newimport.looks.CostumeImport
import de.jensklingenberg.newimport.looks.ItemNumOfListImport
import de.jensklingenberg.newimport.looks.SetEffectToImport
import de.jensklingenberg.newimport.looks.SetSizeImport
import de.jensklingenberg.newimport.looks.SwitchcostumetoImport
import de.jensklingenberg.newimport.motion.ChangeXbyImport
import de.jensklingenberg.newimport.motion.ChangeybyImport
import de.jensklingenberg.newimport.motion.MovestepsImport
import de.jensklingenberg.newimport.motion.PointInDirectionImport
import de.jensklingenberg.newimport.motion.SetxImport
import de.jensklingenberg.newimport.motion.SetyImport
import de.jensklingenberg.newimport.operator.DivideImport
import de.jensklingenberg.newimport.operator.JoinImport
import de.jensklingenberg.newimport.operator.LengthofWordImport
import de.jensklingenberg.newimport.operator.LtImport
import de.jensklingenberg.newimport.operator.MathOpImport
import de.jensklingenberg.newimport.operator.ModImport
import de.jensklingenberg.newimport.operator.MultiplyImport
import de.jensklingenberg.newimport.operator.NotImport
import de.jensklingenberg.newimport.operator.OrImport
import de.jensklingenberg.newimport.operator.SubtractImport
import de.jensklingenberg.newimport.pen.ClearImport
import de.jensklingenberg.newimport.pen.PenUpImport
import de.jensklingenberg.newimport.pen.SetPenColorToColor
import de.jensklingenberg.newimport.pen.StampImport
import de.jensklingenberg.newimport.procedures.DefinitionImport
import de.jensklingenberg.newimport.procedures.PrototypeImport
import de.jensklingenberg.newimport.sensing.AnswerImport
import de.jensklingenberg.newimport.sensing.KeyoptionsImport
import de.jensklingenberg.newimport.sensing.KeypressedImport
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.contentOrNull
import looks.HideImport
import looks.NextbackdropImport
import looks.ShowImport
import looks.SwitchbackdroptoImport
import de.jensklingenberg.newimport.motion.GotoxyImport
import de.jensklingenberg.newimport.motion.XPostionImport
import de.jensklingenberg.newimport.motion.YPostionImport
import de.jensklingenberg.newimport.operator.AddImport
import de.jensklingenberg.newimport.operator.AndImport
import de.jensklingenberg.newimport.operator.CostumNumberNameImport
import de.jensklingenberg.newimport.operator.LetterOfImport
import de.jensklingenberg.newimport.operator.RoundImport
import de.jensklingenberg.newimport.pen.SetPenSizeImport
import de.jensklingenberg.newimport.operator.EqualsImport
import de.jensklingenberg.newimport.operator.RandomImport
import de.jensklingenberg.newimport.pen.PenDownImport
import operator.GtImport
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.zip.ZipInputStream


val json = Json {
    ignoreUnknownKeys = true
    coerceInputValues = true
    explicitNulls = false
}

fun importer(sb3Path: String) {
    var projectJson: String = ""


    ZipInputStream(FileInputStream(sb3Path)).use { zis ->
        var entry = zis.nextEntry

        while (entry != null) {
            if (entry.name == "project.json") {
                val stringBuilder = StringBuilder()
                InputStreamReader(zis).use { isr ->
                    val buffer = CharArray(1024)
                    var length: Int
                    while (isr.read(buffer).also { length = it } != -1) {
                        stringBuilder.appendRange(buffer, 0, length)
                    }
                }
                projectJson = stringBuilder.toString()

            }

            //Zipentry to file
            try {
                zis.closeEntry()
                entry = zis.nextEntry
            } catch (e: Exception) {
                //e.printStackTrace()
                entry = null
            } finally {

            }

        }
    }
    val myList = mutableListOf<ImportNode>()
    //event
    myList.add(WhenFlag())
    myList.add(WhenKey())
    myList.add(WhenBroadcastReceived())
    myList.add(MovestepsImport())
    myList.add(GotoxyImport())
    myList.add(XPostionImport())
    myList.add(YPostionImport())
    myList.add(ChangeybyImport())
    myList.add(ChangeXbyImport())
    myList.add(ChangevariablebyImport())
    myList.add(HidelistImport())

    myList.add(CostumeImport())
    myList.add(SwitchcostumetoImport())
    myList.add(ItemoflistImport())

    //motion
    myList.add(PointInDirectionImport())

    //Pen
    myList.add(StampImport())
    myList.add(SetPenColorToColor())
    myList.add(PenUpImport())
    myList.add(PenDownImport())
    myList.add(SetPenSizeImport())
    myList.add(ClearImport())


    //looks
    myList.add(NextbackdropImport())
    myList.add(SwitchbackdroptoImport())
    myList.add(HideImport())
    myList.add(SayImport())
    myList.add(DefinitionImport())
    myList.add(SetxImport())
    myList.add(SetyImport())
    myList.add(PrototypeImport())
    myList.add(ShowImport())
    myList.add(If_elseImport())
    myList.add(CostumNumberNameImport())

    //operator
    myList.add(EqualsImport())
    myList.add(DivideImport())
    myList.add(MathOpImport())
    myList.add(LtImport())
    myList.add(JoinImport())
    myList.add(GtImport())
    myList.add(AddImport())
    myList.add(RandomImport())
    myList.add(ModImport())
    myList.add(ArgStringNumber())
    myList.add(AndImport())
    myList.add(OrImport())
    myList.add(LengthofWordImport())
    myList.add(MultiplyImport())
    myList.add(SubtractImport())
    myList.add(NotImport())
    myList.add(LetterOfImport())
    myList.add(RoundImport())

    //looks
    myList.add(SetSizeImport())
    myList.add(SetEffectToImport())

    //control
    myList.add(IfImport())
    myList.add(RepeatImport())
    myList.add(RepeatUntilImport())
    myList.add(ForeverImport())

    myList.add(ClearGrahpiceffects())
    myList.add(ReplaceItemImport())

    myList.add(ItemNumOfListImport())

    //data
    myList.add(LengthoflistImport())
    myList.add(ShowListImport())
    myList.add(DeleteAllOfImport())
    myList.add(AddToList())


    myList.add(SetVariableImport())
    myList.add(CallImport())

    myList.add(KeypressedImport())
    myList.add(AnswerImport())
    myList.add(KeyoptionsImport())


    //sensing
    myList.add(DaySinceImport())
    //event
    myList.add(BroadcastImport())
    myList.add(BroadcastAndWaitImport())

    myList.add(DefaultImporter())
    val scratchProject = json.decodeFromString<ScratchProject>(projectJson)

    val wrapperFolder = "/Users/jens.klingenberg/Code/2024/LLVMPoet/wrapper/"

    scratchProject.targets.forEachIndexed { index, target ->
        val builder = StringBuilder()
        builder.append("spriteBuilder(\"${target.name}\"){\n")
        println("Sprite" + target.name)
        val colorName = target.name + "Costume"
        val test = target.costumes.mapIndexed { index, costume ->
            "val _${(colorName + index).replace("^", "").replace(">", "")} = Costume(\n" +
                    "    name = \"${costume.name}\",\n" +
                    "    bitmapResolution = ${costume.bitmapResolution},\n" +
                    "    dataFormat = \"${costume.dataFormat}\",\n" +
                    "    assetId = \"${costume.assetId}\",\n" +
                    "    md5ext = \"${costume.md5ext}\",\n" +
                    "    rotationCenterX = ${costume.rotationCenterX},\n" +
                    "    rotationCenterY = ${costume.rotationCenterY}\n" +
                    ")"
        }.joinToString("\n") {
            it
        }

        File("${wrapperFolder}Costumes${target.name}.kt").writeText(test)


        target.blocks.filter { it.value.topLevel }.forEach { (topLevelId, topLevelBlock) ->
            builder.append("scriptBuilder{\n")
            extracted(topLevelId, target, myList, builder, scratchProject)
            builder.append("}\n\n")
        }

        builder.append("}\n\n")
        File("${wrapperFolder}${target.name}.kt").writeText(builder.toString())
        return@forEachIndexed

        val nodeClassName = ClassName("de.jensklingenberg.scrako.common", "Node")
        val reporterBlock = ClassName("de.jensklingenberg.scrako.common", "ReporterBlock")

        target.blocks.forEach { (_, blockOr) ->

            var name = blockOr.opcode.substringAfter("_").capitalize()
            if (name == "Call") {
                name = blockOr.mutation?.proccode?.substringBefore(" ")?.capitalize() ?: name
            }

            if (name == "Prototype") {
                return@forEach
            }
            val packageName = blockOr.opcode.substringBefore("_")
            val className = ClassName(packageName, name)
            val cBlockClassName = ClassName("de.jensklingenberg.scrako.common", "CBlock")

            val specci = blockOr.fields.map {
                ParameterSpec.builder(it.key.lowercase(), String::class.asTypeName()).build()
            }
            val specciProp = blockOr.fields.map {
                PropertySpec.builder(it.key.lowercase(), String::class.asTypeName()).initializer(it.key.lowercase())
                    .build()
            }
            val inputparaSpecs = blockOr.inputs.entries.map {
                ParameterSpec.builder(it.key.lowercase(), reporterBlock).build()
            }
            val inputpropSpecs = blockOr.inputs.entries.map {
                PropertySpec.builder(it.key.lowercase(), reporterBlock).initializer(it.key.lowercase()).build()
            }

            val constrfunSpec = FunSpec.constructorBuilder()
                .addParameters(specci + inputparaSpecs)
                .build()

            val newFields = blockOr.fields.entries.map { entry ->
                "\"${entry.key}\" to listOf(${entry.key.lowercase()},null)"
            }
                .joinToString("\n") { it }
            val newInputs =
                blockOr.inputs.entries.mapIndexed { index, entry -> "\"${entry.key}\" to setValue(${entry.key.lowercase()}, ${entry.key.lowercase()}Id, context) " }
                    .joinToString(",\n") { it }


            val repl =
                blockOr.inputs.entries.map { entry ->
                    "val ${entry.key.lowercase()}Id = UUID.randomUUID().toString()"
                }.joinToString("\n") { it }


            val wer = blockOr.inputs.entries.mapIndexed { index, entry ->
                "${entry.key.lowercase()}.visit(visitors, identifier, ${entry.key.lowercase()}Id, null, context)"
            }.joinToString("\n") { it }

            val mutation = if (blockOr.mutation != null) {
                """mutation = Mutation(
                tagName = "${blockOr.mutation?.tagName}",
                children = listOf(),
                proccode = "",
                argumentids = "${blockOr.mutation?.argumentids}",
                argumentnames = "[]",
                argumentdefaults = "[]",
                warp = "false"
            ),"""
            } else {
                ""
            }

            val visitFunSpec = FunSpec.builder("visit")
                .addModifiers(KModifier.OVERRIDE)
                .addParameter(
                    "visitors",
                    MUTABLE_MAP.parameterizedBy(String::class.asTypeName(), Block::class.asTypeName())
                )
                .addParameter("parent", String::class.asClassName().copy(nullable = true))
                .addParameter("identifier", String::class.asClassName())
                .addParameter("nextUUID", String::class.asClassName().copy(nullable = true))
                .addParameter("context", ClassName("de.jensklingenberg.scrako.common", "Context"))
                .addCode(
                    """
        $repl
        visitors[identifier] = BlockSpec(
            opcode = "${blockOr.opcode}",
            inputs = mapOf($newInputs),
            fields = mapOf($newFields),
            $mutation
        ).toBlock(nextUUID, identifier)
        $wer
    """.trimIndent()
                )
                .build()

            val commonScriptType = ClassName("de.jensklingenberg.scrako.builder", "CommonScriptBuilder")
            val fields1 = blockOr.fields.map { it.key.lowercase() }.joinToString()
            val inputs1 = blockOr.inputs.map { it.key.lowercase() }.joinToString()

            val listI = listOf(fields1, inputs1).joinToString { it }.removePrefix(", ")

            val extFunSpec = FunSpec.builder(name.lowercase())
                .receiver(commonScriptType)
                .addParameters(specci + inputparaSpecs)
                .returns(Unit::class.asTypeName())
                .addStatement("addNode(${name}($listI))")
                .build()

            val builder = TypeSpec.classBuilder(name)
                .addSuperinterface(nodeClassName)
                .addProperties(specciProp + inputpropSpecs)
                .addModifiers(KModifier.PRIVATE)
                .addFunction(visitFunSpec)
                .primaryConstructor(constrfunSpec)

            blockOr.inputs["SUBSTACK"]?.let {
                builder.addSuperinterface(cBlockClassName)
            }

            var nodeClass = builder.build()

            val source = try {
                FileSpec
                    .builder(className)
                    .addImport("java.util", "UUID")
                    .addImport("de.jensklingenberg.scrako.common", "BlockSpec")
                    .addImport("de.jensklingenberg.scrako.common", "setValue")
                    .addType(nodeClass)
                    .addFunction(extFunSpec)
                    .build().toString()
            } catch (e: Exception) {
                println("Error: $e")
                ""
            }

            File(wrapperFolder + "$packageName/").mkdirs()
            File(wrapperFolder + "$packageName/" + name + ".kt").writeText(source)

            val importclassName = ClassName(packageName, name + "Import")

            var callInput = ""
            blockOr.inputs.entries.forEach {
                val key = it.key
                if (it.value[0].toString() == "3") {
                    val id = it.value[1] as? JsonPrimitive
                    val tt = id?.contentOrNull?.let { target.blocks[it]?.fields?.get(key) }
                    callInput = "\\\"" + tt?.get(0) + "\\\"" ?: ""
                }
            }


            val claClas = blockOr.opcode.substringAfter("_")
            val importClasstype = TypeSpec.classBuilder(importclassName)
                .addSuperinterface(ClassName("de.jensklingenberg.imports", "ImportNode"))
                .addProperty(
                    PropertySpec.builder("opCode", String::class.asTypeName()).addModifiers(KModifier.OVERRIDE)
                        .initializer("\"${blockOr.opcode}\"")
                        .build()
                )
                .addFunction(
                    FunSpec.builder("visit")
                        .addModifiers(KModifier.OVERRIDE)
                        .addParameter("builder", StringBuilder::class.asTypeName())
                        .addParameter("scratchProject", ScratchProject::class.asTypeName())
                        .addParameter("block", Block::class.asTypeName())
                        .addParameter(
                            "myList",
                            List::class.asTypeName().parameterizedBy(ImportNode::class.asTypeName())
                        )
                        .addParameter("id", String::class.asTypeName())
                        .addParameter("target", de.jensklingenberg.scrako.model.Target::class.asTypeName())
                        .addCode("builder.append(\"$claClas($callInput)\\n\")")
                        .build()
                )
                .build()

            val importSource = FileSpec
                .builder(importclassName)
                .addType(importClasstype)
                .build()
                .toString()

            File(wrapperFolder + "/import/$packageName/").mkdirs()
            File(wrapperFolder + "/import/$packageName/" + name + ".kt").writeText(importSource)
        }


        builder.append("}\n\n")
    }
    //println(builder)
}

fun extracted(
    topLevelId: String,
    target: Target,
    myList: List<ImportNode>,
    builder: StringBuilder,
    scratchProject: ScratchProject
) {
    val mut = mutableMapOf<String, Block>()
    var foundId = topLevelId
    var hasNext = target.blocks[topLevelId]?.next?.isNotEmpty() ?: false

    if(hasNext == false){
        mut[topLevelId] = target.blocks[topLevelId]!!
    }

    while (hasNext) {
        val myBlock = target.blocks[foundId]
        if (myBlock != null) {
            mut[foundId] = myBlock
            foundId = myBlock.next ?: ""
            hasNext = foundId.isNotEmpty()
        } else {
            hasNext = false
        }
    }


    target.blocks.forEach { (t, u) ->
        if (t == foundId) {
          //  mut[t] = u
          //  foundId = u.next ?: ""
        }
    }

    mut.forEach { (id, block) ->
        myList.find { it.opCodeSupported(block.opcode) }?.visit(
            builder,
            scratchProject,
            target,
            block,
            myList,
            id
        )
            ?: throw IllegalStateException("No importer found for ${block.opcode}")
    }
}