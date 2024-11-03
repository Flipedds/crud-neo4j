package com.flipedds.crudneo4j

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@EnableTransactionManagement
@EnableNeo4jRepositories
@SpringBootApplication
class CrudNeo4jApplication

fun main(args: Array<String>) {
    runApplication<CrudNeo4jApplication>(*args)
}
