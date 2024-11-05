package `operator`

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target
import java.lang.StringBuilder
import kotlin.String
import kotlin.collections.List

public class GtImport : ImportNode {
    override val opCode: String = "operator_gt"

    override fun visit(
        builder: StringBuilder,
        project: ScratchProject,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String,
    ) {

        builder.append("(")
        handle(builder, target, myList, project, blockOr.inputs["OPERAND1"]?.get(1))
        builder.append(" gt ")
        handle(builder, target, myList, project, blockOr.inputs["OPERAND2"]?.get(1))
        builder.append(")")
    }
}
