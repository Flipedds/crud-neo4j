package com.flipedds.crudneo4j.repositories

import com.flipedds.crudneo4j.nodes.Hero
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(collectionResourceRel = "heroes", path = "heroes")
interface HeroRepository : Neo4jRepository<Hero, Long> {
    fun findByName(name: String): Hero?
    fun findByRealName(realName: String): Hero?
}