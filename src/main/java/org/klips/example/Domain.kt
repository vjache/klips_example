package org.klips.example

import org.klips.dsl.Facet
import org.klips.dsl.Fact


open class ParentOf(val personId1: Facet<Int>, val personId2: Facet<Int>) : Fact()
class FatherOf(personId1: Facet<Int>, personId2: Facet<Int>) : ParentOf(personId1, personId2)
class GrandFatherOf(val personId1: Facet<Int>, val personId2: Facet<Int>) : Fact()
class MotherOf(personId1: Facet<Int>, personId2: Facet<Int>) : ParentOf(personId1, personId2)
class SiblingOf(val personId1: Facet<Int>, val personId2: Facet<Int>) : Fact()