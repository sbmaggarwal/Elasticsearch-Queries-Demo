package com.shubham.elasticsearchmappingdemo.dal

import com.shubham.elasticsearchmappingdemo.model.Person
import org.springframework.data.domain.Pageable
import org.springframework.data.elasticsearch.annotations.Query
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository : ElasticsearchRepository<Person, String> {

    @Query("{ \"query\": { \"query_string\" : { \"fields\" : [\"name\"], \"query\":\"*?0*\"}}}")
    fun freeTextSearchPerson(text: String, pageable: Pageable): List<Person>
}