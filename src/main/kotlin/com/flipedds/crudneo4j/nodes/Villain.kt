package com.flipedds.crudneo4j.nodes

import org.springframework.data.neo4j.core.schema.GeneratedValue
import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Relationship

@Node
class Villain(var name: String?, var realName: String?) {
    @Id
    @GeneratedValue
    var id: Long? = null

    @Relationship(type = "CONFLICT_WITH", direction = Relationship.Direction.INCOMING)
    var conflictedBy: MutableSet<Hero>? = null
}
