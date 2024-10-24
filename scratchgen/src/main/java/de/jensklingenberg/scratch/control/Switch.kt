package de.jensklingenberg.scratch.control

import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.looks.StringReporter
import de.jensklingenberg.scratch.operator.OperatorEquals


fun NodeBuilder.switch(block: String, operatorSpec: SwitchContext.() -> Unit) =
    switch(StringReporter(block), operatorSpec)

fun NodeBuilder.switch(block: ReporterBlock, operatorSpec: SwitchContext.() -> Unit) {
    val test = SwitchContext().apply(operatorSpec)

    if (test.mutableList.size == 1) {
        ifThen(OperatorEquals(block, test.mutableList.first().operatorSpec)) {
            test.mutableList.first().leftStack(this)
        }
    } else {
        ifElse(OperatorEquals(block, test.mutableList.first().operatorSpec), {
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

fun SwitchContext.case(block: ReporterBlock, leftStack: NodeBuilder.() -> Unit) = addChild(Case(block, leftStack))
fun SwitchContext.case(operatorSpec: String, leftStack: NodeBuilder.() -> Unit) =
    addChild(Case(StringReporter(operatorSpec), leftStack))

class SwitchContext {
    val mutableList = mutableListOf<Case>()

    fun addChild(whenFlagClicked: Case) {
        mutableList.add(whenFlagClicked)
    }
}

data class Case(val operatorSpec: ReporterBlock, val leftStack: NodeBuilder.() -> Unit)