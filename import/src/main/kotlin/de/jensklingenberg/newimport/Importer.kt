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
import de.jensklingenberg.newimport.argument.ArgBoolean
import de.jensklingenberg.newimport.argument.ArgStringNumber
import de.jensklingenberg.newimport.control.CreateCloneOfImport
import de.jensklingenberg.newimport.control.CreateCloneOfMenuImport
import de.jensklingenberg.newimport.control.DeleteThisCloneImport
import de.jensklingenberg.newimport.control.ForeverImport
import de.jensklingenberg.newimport.control.IfElseImport
import de.jensklingenberg.newimport.control.IfImport
import de.jensklingenberg.newimport.control.RepeatImport
import de.jensklingenberg.newimport.control.RepeatUntilImport
import de.jensklingenberg.newimport.control.SensingTouchingObjectMenuImport
import de.jensklingenberg.newimport.control.StartAsCloneImport
import de.jensklingenberg.newimport.control.StopImport
import de.jensklingenberg.newimport.control.WaitImport
import de.jensklingenberg.newimport.control.WaitUntilImport
import de.jensklingenberg.newimport.control.WhileImport
import de.jensklingenberg.newimport.data.AddToList
import de.jensklingenberg.newimport.data.ChangevariablebyImport
import de.jensklingenberg.newimport.data.DeleteAllOfImport
import de.jensklingenberg.newimport.data.DeleteOfList
import de.jensklingenberg.newimport.data.HideVariableImport
import de.jensklingenberg.newimport.data.HidelistImport
import de.jensklingenberg.newimport.data.InsertAt
import de.jensklingenberg.newimport.data.ItemoflistImport
import de.jensklingenberg.newimport.data.LengthoflistImport
import de.jensklingenberg.newimport.data.ListContainsImport
import de.jensklingenberg.newimport.data.ReplaceItemImport
import de.jensklingenberg.newimport.data.SetVariableImport
import de.jensklingenberg.newimport.data.ShowListImport
import de.jensklingenberg.newimport.data.ShowVariable
import de.jensklingenberg.newimport.event.BroadcastAndWaitImport
import de.jensklingenberg.newimport.event.BroadcastImport
import de.jensklingenberg.newimport.event.WhenBackdropSwitchesToImport
import de.jensklingenberg.newimport.event.WhenBroadcastReceivedImport
import de.jensklingenberg.newimport.event.WhenFlag
import de.jensklingenberg.newimport.event.WhenGreaterThanImport
import de.jensklingenberg.newimport.event.WhenKey
import de.jensklingenberg.newimport.event.WhenStageClickedImport
import de.jensklingenberg.newimport.event.WhenThisSpriteClicked
import de.jensklingenberg.newimport.looks.ChangeEffectBy
import de.jensklingenberg.newimport.looks.ClearGrahpiceffectsImport
import de.jensklingenberg.newimport.looks.CostumNumberNameImport
import de.jensklingenberg.newimport.looks.CostumeImport
import de.jensklingenberg.newimport.looks.GoToImport
import de.jensklingenberg.newimport.looks.HideImport
import de.jensklingenberg.newimport.looks.ItemNumOfListImport
import de.jensklingenberg.newimport.looks.NextbackdropImport
import de.jensklingenberg.newimport.looks.SayForSecsImport
import de.jensklingenberg.newimport.looks.SetEffectToImport
import de.jensklingenberg.newimport.looks.SetSizeImport
import de.jensklingenberg.newimport.looks.SizeImport
import de.jensklingenberg.newimport.looks.SwitchbackdroptoImport
import de.jensklingenberg.newimport.looks.SwitchcostumetoImport
import de.jensklingenberg.newimport.looks.ThinkForSecsImport
import de.jensklingenberg.newimport.motion.ChangeXbyImport
import de.jensklingenberg.newimport.motion.ChangeybyImport
import de.jensklingenberg.newimport.motion.DirectionImport
import de.jensklingenberg.newimport.motion.GlideSecsToXYImport
import de.jensklingenberg.newimport.motion.GlideToImport
import de.jensklingenberg.newimport.motion.GlideToMenuImport
import de.jensklingenberg.newimport.motion.GotoxyImport
import de.jensklingenberg.newimport.motion.MovestepsImport
import de.jensklingenberg.newimport.motion.PointInDirectionImport
import de.jensklingenberg.newimport.motion.PointTowardsImport
import de.jensklingenberg.newimport.motion.PointTowardsMenuImport
import de.jensklingenberg.newimport.motion.SetRotationStyleImport
import de.jensklingenberg.newimport.motion.SetxImport
import de.jensklingenberg.newimport.motion.SetyImport
import de.jensklingenberg.newimport.motion.TurnLeftImport
import de.jensklingenberg.newimport.motion.TurnRightImport
import de.jensklingenberg.newimport.motion.XPostionImport
import de.jensklingenberg.newimport.motion.YPostionImport
import de.jensklingenberg.newimport.operator.AddImport
import de.jensklingenberg.newimport.operator.AndImport
import de.jensklingenberg.newimport.operator.ContainsImport
import de.jensklingenberg.newimport.operator.DivideImport
import de.jensklingenberg.newimport.operator.EqualsImport
import de.jensklingenberg.newimport.operator.GtImport
import de.jensklingenberg.newimport.operator.JoinImport
import de.jensklingenberg.newimport.operator.LengthofWordImport
import de.jensklingenberg.newimport.operator.LetterOfImport
import de.jensklingenberg.newimport.operator.LtImport
import de.jensklingenberg.newimport.operator.MathOpImport
import de.jensklingenberg.newimport.operator.ModImport
import de.jensklingenberg.newimport.operator.MultiplyImport
import de.jensklingenberg.newimport.operator.NotImport
import de.jensklingenberg.newimport.operator.OrImport
import de.jensklingenberg.newimport.operator.RandomImport
import de.jensklingenberg.newimport.operator.RoundImport
import de.jensklingenberg.newimport.operator.SubtractImport
import de.jensklingenberg.newimport.pen.ClearImport
import de.jensklingenberg.newimport.pen.PenChangePenColorParamByImport
import de.jensklingenberg.newimport.pen.PenDownImport
import de.jensklingenberg.newimport.pen.PenMenuColorParamImport
import de.jensklingenberg.newimport.pen.PenSetPenColorParamToImport
import de.jensklingenberg.newimport.pen.PenUpImport
import de.jensklingenberg.newimport.pen.SetPenColorToColor
import de.jensklingenberg.newimport.pen.SetPenSizeImport
import de.jensklingenberg.newimport.pen.StampImport
import de.jensklingenberg.newimport.procedures.CallImport
import de.jensklingenberg.newimport.procedures.DefinitionImport
import de.jensklingenberg.newimport.procedures.PrototypeImport
import de.jensklingenberg.newimport.sensing.AnswerImport
import de.jensklingenberg.newimport.sensing.AskAndWaitImport
import de.jensklingenberg.newimport.sensing.BackdropNumberNameImport
import de.jensklingenberg.newimport.sensing.ChangeSizeByImport
import de.jensklingenberg.newimport.sensing.ColorIsTouchingColorImport
import de.jensklingenberg.newimport.sensing.CurrentImport
import de.jensklingenberg.newimport.sensing.DaySinceImport
import de.jensklingenberg.newimport.sensing.DistanceToImport
import de.jensklingenberg.newimport.sensing.DistanceToMenuImport
import de.jensklingenberg.newimport.sensing.GoForwardBackwardLayersImport
import de.jensklingenberg.newimport.sensing.KeyoptionsImport
import de.jensklingenberg.newimport.sensing.KeypressedImport
import de.jensklingenberg.newimport.sensing.LoudnessImport
import de.jensklingenberg.newimport.sensing.MouseDownImport
import de.jensklingenberg.newimport.sensing.MouseXImport
import de.jensklingenberg.newimport.sensing.MouseYImport
import de.jensklingenberg.newimport.sensing.NextCostumeImport
import de.jensklingenberg.newimport.sensing.OfImport
import de.jensklingenberg.newimport.sensing.OfObjectMenuImport
import de.jensklingenberg.newimport.sensing.ResetTimerImport
import de.jensklingenberg.newimport.sensing.SetDragModeImport
import de.jensklingenberg.newimport.sensing.SwitchBackdropAndWaitImport
import de.jensklingenberg.newimport.sensing.TimerImport
import de.jensklingenberg.newimport.sensing.TouchingColorImport
import de.jensklingenberg.newimport.sensing.TouchingObjectImport
import de.jensklingenberg.newimport.sensing.UsernameImport
import de.jensklingenberg.newimport.sound.ChangeSoundEffectByImport
import de.jensklingenberg.newimport.sound.ChangeVolumeByImport
import de.jensklingenberg.newimport.sound.ClearEffectsImport
import de.jensklingenberg.newimport.sound.PlaySoundImport
import de.jensklingenberg.newimport.sound.SetVolumeToImport
import de.jensklingenberg.newimport.sound.SoundPlayUntilDoneImport
import de.jensklingenberg.newimport.sound.SoundSetEffectToImport
import de.jensklingenberg.newimport.sound.SoundSoundsMenuImport
import de.jensklingenberg.newimport.sound.StopAllSoundsImport
import de.jensklingenberg.newimport.sound.VolumeImport
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonPrimitive
import looks.ShowImport
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.Locale
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
    myList.add(WhenThisSpriteClicked())
    myList.add(WhenStageClickedImport())
    myList.add(WhenBackdropSwitchesToImport())
    myList.add(WhenGreaterThanImport())
    myList.add(WhenBroadcastReceivedImport())
    myList.add(MovestepsImport())
    myList.add(GotoxyImport())
    myList.add(GlideToImport())
    myList.add(GlideToMenuImport())
    myList.add(SetRotationStyleImport())
    myList.add(XPostionImport())
    myList.add(YPostionImport())
    myList.add(ChangeybyImport())
    myList.add(ChangeXbyImport())
    myList.add(SoundSoundsMenuImport())
    myList.add(GlideSecsToXYImport())
    myList.add(ChangevariablebyImport())
    myList.add(HidelistImport())

    myList.add(CostumeImport())
    myList.add(SwitchcostumetoImport())
    myList.add(ItemoflistImport())

    //motion
    myList.add(PointInDirectionImport())
    myList.add(TurnRightImport())
    myList.add(TurnLeftImport())
    myList.add(DirectionImport())
    myList.add(PointTowardsImport())
    myList.add(PointTowardsMenuImport())

    //Pen
    myList.add(StampImport())
    myList.add(SetPenColorToColor())
    myList.add(PenUpImport())
    myList.add(PenDownImport())
    myList.add(PenSetPenColorParamToImport())
    myList.add(PenMenuColorParamImport())
    myList.add(PenChangePenColorParamByImport())
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
    myList.add(IfElseImport())
    myList.add(CostumNumberNameImport())

    //operator
    myList.add(EqualsImport())
    myList.add(DivideImport())
    myList.add(ContainsImport())
    myList.add(MathOpImport())
    myList.add(LtImport())
    myList.add(JoinImport())
    myList.add(GtImport())
    myList.add(AddImport())
    myList.add(RandomImport())
    myList.add(ModImport())

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
    myList.add(ThinkForSecsImport())
    myList.add(GoToImport())
    myList.add(ChangeEffectBy())
    myList.add(SayForSecsImport())
    myList.add(ChangeSizeByImport())
    myList.add(NextCostumeImport())
    myList.add(SwitchBackdropAndWaitImport())
    myList.add(MouseXImport())
    myList.add(MouseYImport())
    myList.add(GoForwardBackwardLayersImport())
    myList.add(BackdropNumberNameImport())

    //sound
    myList.add(PlaySoundImport())
    myList.add(SoundSetEffectToImport())
    myList.add(SoundPlayUntilDoneImport())
    myList.add(SetVolumeToImport())
    myList.add(VolumeImport())
    myList.add(ChangeVolumeByImport())
    myList.add(ChangeSoundEffectByImport())
    myList.add(StopAllSoundsImport())
    myList.add(ClearEffectsImport())

    //data
    myList.add(HideVariableImport())

    myList.add(SizeImport())

    //control
    myList.add(IfImport())
    myList.add(RepeatImport())
    myList.add(DeleteThisCloneImport())
    myList.add(StartAsCloneImport())
    myList.add(RepeatUntilImport())
    myList.add(ForeverImport())
    myList.add(WaitUntilImport())
    myList.add(WaitImport())
    myList.add(StopImport())
    myList.add(CreateCloneOfImport())
    myList.add(CreateCloneOfMenuImport())
    myList.add(WhileImport())
    myList.add(SensingTouchingObjectMenuImport())

    myList.add(ClearGrahpiceffectsImport())
    myList.add(ReplaceItemImport())

    myList.add(ItemNumOfListImport())

    //data
    myList.add(LengthoflistImport())
    myList.add(ShowListImport())
    myList.add(DeleteAllOfImport())
    myList.add(AddToList())
    myList.add(ListContainsImport())
    myList.add(InsertAt())
    myList.add(DeleteOfList())
    myList.add(ShowVariable())

    myList.add(ArgStringNumber())
    myList.add(ArgBoolean())
    myList.add(SetVariableImport())
    myList.add(CallImport())

    myList.add(KeypressedImport())

    myList.add(KeyoptionsImport())


    //sensing
    myList.add(DaySinceImport())
    myList.add(AnswerImport())
    myList.add(LoudnessImport())
    myList.add(TimerImport())
    myList.add(ResetTimerImport())
    myList.add(OfImport())
    myList.add(OfObjectMenuImport())
    myList.add(CurrentImport())
    myList.add(UsernameImport())
    myList.add(MouseDownImport())
    myList.add(AskAndWaitImport())
    myList.add(TouchingObjectImport())
    myList.add(TouchingColorImport())
    myList.add(ColorIsTouchingColorImport())
    myList.add(DistanceToImport())
    myList.add(SetDragModeImport())
    myList.add(DistanceToMenuImport())

    //event
    myList.add(BroadcastImport())
    myList.add(BroadcastAndWaitImport())

    myList.add(DefaultImporter())

    val cleaned = projectJson
    val scratchProject2 = json.decodeFromString<ScratchProject>(cleaned)

    val scratchProject = scratchProject2//json.decodeFromString<ScratchProject>(cleaned)

    val wrapperFolder = "/Users/jens.klingenberg/Code/2024/LLVMPoet/wrapper/"

    scratchProject.targets.forEachIndexed { index, target ->
        val builder = StringBuilder()
        builder.append("fun ProjectBuilder.addSprite${index}(){\n")
        builder.append("        addCostumes(listOf(")
        builder.append(target.costumes.mapIndexed { index, costume -> target.name + "Costume" + index }
            .joinToString(", ") { it })
        builder.append("))\n")
        builder.append("spriteBuilder(\"${target.name}\"){\n")
        println("Sprite" + target.name)
        val colorName = target.name + "Costume"
        val test = target.costumes.mapIndexed { index, costume ->
            "val ${(colorName + index).replace("^", "").replace(">", "")} = Costume(\n" +
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
        File("${wrapperFolder}/costumes/").mkdirs()
        File("${wrapperFolder}/src/").mkdirs()

        File("${wrapperFolder}/costumes/Costumes${target.name}.kt").writeText(test)


        target.blocks.forEach { (topLevelId, topLevelBlock) ->
            if (topLevelBlock is BlockFull && topLevelBlock.topLevel) {
                builder.append("scriptBuilder{\n")
                extracted(topLevelId, target, myList, builder)
                builder.append("}\n\n")
            }

        }

        builder.append("}\n}\n")
        File("${wrapperFolder}/src/${target.name}.kt").writeText(builder.toString())
        return@forEachIndexed

        val nodeClassName = ClassName("de.jensklingenberg.scrako.common", "Node")
        val reporterBlock = ClassName("de.jensklingenberg.scrako.common", "ReporterBlock")

        target.blocks.forEach { (_, block) ->
            val blockOr = block as BlockFull
            var name = blockOr.opcode.substringAfter("_")
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
            if (name == "Call") {
                name = blockOr.mutation?.proccode?.substringBefore(" ")
                    ?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
                    ?: name
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
                    MUTABLE_MAP.parameterizedBy(String::class.asTypeName(), BlockFull::class.asTypeName())
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
                    //val tt = id?.contentOrNull?.let { target.blocks[it]?.fields?.get(key) }
                    //  callInput = "\\\"" + tt?.get(0) + "\\\""
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
                        .addParameter("block", BlockFull::class.asTypeName())
                        .addParameter(
                            "myList",
                            List::class.asTypeName().parameterizedBy(ImportNode::class.asTypeName())
                        )
                        .addParameter("id", String::class.asTypeName())
                        .addParameter("target", Target::class.asTypeName())
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
    builder: StringBuilder
) {
    //Convert Map<String, Block> to Map<String, BlockFull>
    val blocks = target.blocks.mapValues { (_, value) ->
        value as? BlockFull
    }
    val mut = mutableMapOf<String, BlockFull>()
    var foundId = topLevelId
    var hasNext = blocks[topLevelId]?.next?.isNotEmpty() ?: false

    if (hasNext == false) {
        mut[topLevelId] = blocks[topLevelId]!!
    }

    while (hasNext) {
        val myBlock = blocks[foundId]
        if (myBlock != null) {
            mut[foundId] = myBlock
            foundId = myBlock.next ?: ""
            hasNext = foundId.isNotEmpty()
        } else {
            hasNext = false
        }
    }


    mut.forEach { (id, block) ->
        myList.find { it.opCodeSupported(block.opcode) }?.visit(
            builder,
            target,
            block,
            myList,
            id
        )
            ?: throw IllegalStateException("No importer found for ${block.opcode}")
    }
}