import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.StringBlock
import de.jensklingenberg.scratch3.control.ifElse
import de.jensklingenberg.scratch3.control.ifThen
import de.jensklingenberg.scratch3.operator.OperatorEquals


fun ScriptBuilder.switch(block: String, operatorSpec: Switch.() -> Unit) =
    switch(StringBlock(block), operatorSpec)

fun ScriptBuilder.switch(block: ReporterBlock, operatorSpec: Switch.() -> Unit) {
    val test = Switch().apply(operatorSpec)

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

fun Switch.case(block: ReporterBlock, leftStack: ScriptBuilder.() -> Unit) = addChild(Case(block, leftStack))
fun Switch.case(operatorSpec: String, leftStack: ScriptBuilder.() -> Unit) =
    addChild(Case(StringBlock(operatorSpec), leftStack))

class Switch {
    val mutableList = mutableListOf<Case>()

    fun addChild(whenFlagClicked: Case) {
        mutableList.add(whenFlagClicked)
    }
}

data class Case(val operatorSpec: ReporterBlock, val leftStack: ScriptBuilder.() -> Unit)