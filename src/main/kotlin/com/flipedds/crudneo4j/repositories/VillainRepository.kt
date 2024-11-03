package com.flipedds.crudneo4j.repositories

import com.flipedds.crudneo4j.nodes.Villain
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(collectionResourceRel = "villains", path = "villains")
interface VillainRepository : PagingAndSortingRepository<Villain, Long>,
    CrudRepository<Villain, Long> {
        fun findByName(name: String): Villain?
        fun findByRealName(realName: String): Villain?
}