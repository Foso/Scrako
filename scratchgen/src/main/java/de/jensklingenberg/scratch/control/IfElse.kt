package de.jensklingenberg.scratch.control


import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.createSubStack
import de.jensklingenberg.scratch.looks.StringReporter
import de.jensklingenberg.scratch.model.Block
import de.jensklingenberg.scratch.operator.OperatorEquals
import java.util.UUID

fun NodeBuilder.ifElse(
    operatorSpec: ReporterBlock,
    leftStack: NodeBuilder.() -> Unit,
    rightStack: NodeBuilder.() -> Unit,
) = addChild(
    IfElse(
        operatorSpec,
        leftStack = NodeBuilder().apply(leftStack).childs,
        rightStack = NodeBuilder().apply(rightStack).childs
    )
)

fun NodeBuilder.switch(block: ReporterBlock,operatorSpec: SwitchContext.() -> Unit) {
    val test = SwitchContext().apply(operatorSpec)

    if(test.mutableList.size == 1){
        ifThen(OperatorEquals(block,test.mutableList.first().operatorSpec)){
            test.mutableList.first().leftStack(this)
        }
    }else{
        ifElse(OperatorEquals(block,test.mutableList.first().operatorSpec),{
            test.mutableList.first().leftStack(this)
        },{
            switch(block) {
                test.mutableList.drop(1).forEach {
                    case(it.operatorSpec,it.leftStack)
                }
            }
        })
    }
}

fun SwitchContext.case(operatorSpec: ReporterBlock,leftStack: NodeBuilder.() -> Unit,) = addChild(Case(operatorSpec,leftStack))
fun SwitchContext.case(operatorSpec: String,leftStack: NodeBuilder.() -> Unit,) = addChild(Case(StringReporter(operatorSpec),leftStack))

class SwitchContext{
    val mutableList = mutableListOf<Case>()

    fun addChild(whenFlagClicked: Case) {
        mutableList.add(whenFlagClicked)
    }
}

data class Case(val operatorSpec: ReporterBlock,val leftStack: NodeBuilder.() -> Unit)

class IfElse(
    private val operatorSpec: ReporterBlock,
    private val leftStack: List<Node>,
    private val rightStack: List<Node>
) : Node {

    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        val name2 = identifier.toString()
        val newNext = nextUUID
        val operatorUUID = UUID.randomUUID()
        val leftUUIDs = leftStack.map { UUID.randomUUID() }
        val rightUUIDs = rightStack.map { UUID.randomUUID() }

        visitors[name2] = BlockSpec(
            opcode = OpCode.control_if_else,
            inputs = mapOf(
                "CONDITION" to createSubStack(operatorUUID.toString()),
                "SUBSTACK" to createSubStack(leftUUIDs.first().toString()),
                "SUBSTACK2" to createSubStack(rightUUIDs.first().toString())
            )
        ).toBlock(newNext, parent, context.topLevel)
        operatorSpec.visit(visitors, name2, operatorUUID, null, context)

        leftStack.mapIndexed { childIndex, visitor ->
            val nextchild =
                childIndex != leftStack.lastIndex

            val nextUUID = if (nextchild) leftUUIDs[childIndex + 1] else null
            visitor.visit(
                visitors,
                parent = name2,
                leftUUIDs[childIndex],
                nextUUID,
                context
            )
        }

        rightStack.mapIndexed { childIndex, visitor ->
            val nextchild =
                childIndex != rightStack.lastIndex

            val nextUUID = if (nextchild) rightUUIDs[childIndex + 1] else null
            visitor.visit(
                visitors,
                parent = name2,
                rightUUIDs[childIndex],
                nextUUID,
                context
            )
        }

    }
}

fun NodeBuilder.ifThen(
    block: ReporterBlock,
    leftStack: NodeBuilder.() -> Unit
) = addChild(IfE(block, leftStack = NodeBuilder().apply(leftStack).childs))

class IfE(
    private val reporterBlock: ReporterBlock,
    private val leftStack: List<Node>
) : Node {

    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        val newNext = nextUUID
        val operatorUUID = UUID.randomUUID()
        val leftUUIDs = leftStack.map { UUID.randomUUID() }

        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.control_if,
            inputs = mapOf(
                "CONDITION" to createSubStack(operatorUUID.toString()),
                "SUBSTACK" to createSubStack(leftUUIDs.firstOrNull().toString())
            )
        ).toBlock(newNext, parent, context.topLevel)
        reporterBlock.visit(visitors, identifier.toString(), operatorUUID, null, context)

        leftStack.mapIndexed { childIndex, visitor ->
            val nextchild =
                childIndex != leftStack.lastIndex

            val nextUUID = if (nextchild) leftUUIDs[childIndex + 1] else null
            visitor.visit(
                visitors,
                parent = identifier.toString(),
                leftUUIDs[childIndex],
                nextUUID,
                context
            )
        }

    }
}