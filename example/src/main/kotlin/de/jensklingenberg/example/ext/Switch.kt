package de.jensklingenberg.example.ext

import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.builder.SpriteScriptBuilder
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.StringBlock
import de.jensklingenberg.scratch3.control.ifElse
import de.jensklingenberg.scratch3.control.ifThen
import de.jensklingenberg.scratch3.operator.OperatorEquals


fun SpriteScriptBuilder.switch(block: String, operatorSpec: Switch.() -> Unit) =
    switch(StringBlock(block), operatorSpec)

fun SpriteScriptBuilder.switch(block: ReporterBlock, operatorSpec: Switch.() -> Unit) {
    val test = Switch().apply(operatorSpec)

    if (test.mutableList.size == 1) {
        ifThen(OperatorEquals(block, test.mutableList.first().operatorSpec)) {
            test.mutableList.first().leftStack(this)
        }
    } else {
        ifElse(OperatorEquals(block, test.mutableList.first().operatorSpec), leftStack = {
            test.mutableList.first().leftStack(this)
        }, {
            switch(block) {
                test.mutableList.drop(1).forEach {
                    case(it.operatorSpec, it.leftStack)
                }
            }
        })
    }
}
fun Switch.case(block: Int, leftStack: CommonScriptBuilder.() -> Unit) = addChild(Case(IntBlock(block), leftStack))

fun Switch.case(block: ReporterBlock, leftStack: CommonScriptBuilder.() -> Unit) = addChild(Case(block, leftStack))
fun Switch.case(operatorSpec: String, leftStack: CommonScriptBuilder.() -> Unit) =
    addChild(Case(StringBlock(operatorSpec), leftStack))

class Switch internal constructor(){
    val mutableList = mutableListOf<Case>()

    fun addChild(whenFlagClicked: Case) {
        mutableList.add(whenFlagClicked)
    }
}

data class Case(val operatorSpec: ReporterBlock, val leftStack: CommonScriptBuilder.() -> Unit)