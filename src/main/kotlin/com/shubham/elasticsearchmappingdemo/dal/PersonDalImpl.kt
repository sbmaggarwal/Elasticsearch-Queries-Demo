package com.shubham.elasticsearchmappingdemo.dal

import com.shubham.elasticsearchmappingdemo.model.Person
import org.elasticsearch.index.query.QueryBuilders
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder
import org.springframework.stereotype.Repository

@Repository
class PersonDalImpl(
        @Autowired var personRepository: PersonRepository,
        @Autowired var esTemplate: ElasticsearchTemplate) {

    fun searchPersonAnalysed(text: String): List<Person> {

        val query = NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("name", text))
                .build()
        return esTemplate.queryForList(query, Person::class.java)
    }

    fun searchPersonNonAnalysed(text: String): List<Person> {

        val query = NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.wildcardQuery("name.verbal", "*$text*"))
                .build()
        return esTemplate.queryForList(query, Person::class.java)
    }

    fun searchNativelyPerson(text: String): List<Person> {
        return personRepository.freeTextSearchPerson(
                text, PageRequest(0, 100,
                Sort(Sort.Order(Sort.Direction.ASC, "name"))))
    }

    fun addPerson(name: String): Person {
        var person = Person()
        person.name = name
        person = personRepository.save(person)
        return person
    }
}