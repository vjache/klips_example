package org.klips.example

import org.klips.dsl.RuleSet
import org.klips.dsl.facet
import org.klips.dsl.get
import org.klips.engine.util.Log

class MyRules : RuleSet(Log()){

    init {

        val p1 = ref<Int>("p1")
        val p2 = ref<Int>("p2")
        val p3 = ref<Int>("p3")
        rule(name = "Siblings") {
            +ParentOf(p1, p2)
            +ParentOf(p1, p3)
            guard(p2 gt p3)
            effect { sol ->
                val sibs = +SiblingOf(p2, p3)
                println("Hello siblings!(${sol[p1]}) : ${sibs.substitute(sol.arg)}")
            }
        }

        rule(name = "Grandfather") {
            +ParentOf(p1, p2)
            +ParentOf(p2, p3)
            effect { sol ->
                val sibs = +GrandFatherOf(p1, p3)
                println("Hello grand father! : ${sibs.substitute(sol.arg)}")
            }
        }

        rule ("Sibling.Symmetry") {
            +SiblingOf(p1, p2)
            effect {
                +SiblingOf(p2, p1)
            }
        }

        rule ("Sibling.Transitivity") {
            +SiblingOf(p1, p2)
            +SiblingOf(p2, p3)
            effect {
                +SiblingOf(p1, p3)
            }
        }

    }

}

fun main(args: Array<String>) {
    MyRules().input.flush("Siblings", "Sibling.Symmetry", "Sibling.Transitivity") {
        +FatherOf(1.facet, 2.facet)
        +FatherOf(1.facet, 3.facet)
        +FatherOf(2.facet, 4.facet)
    }
}