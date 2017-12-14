package com.shubham.elasticsearchmappingdemo.controller

import com.shubham.elasticsearchmappingdemo.dal.PersonDalImpl
import com.shubham.elasticsearchmappingdemo.model.Person
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class PersonController {

    @Autowired
    lateinit var personDal: PersonDalImpl

    @RequestMapping(value = ["/add"], method = arrayOf(RequestMethod.POST))
    fun addPerson(@RequestParam name: String): Person {
        return personDal.addPerson(name);
    }

    @RequestMapping(value = ["/search"], method = arrayOf(RequestMethod.GET))
    fun getPerson(@RequestParam freeText: String,
                  @RequestParam analysed: Boolean): List<Person> {
        if(analysed)
            return personDal.searchPersonAnalysed(freeText)
        else
            return personDal.searchPersonNonAnalysed(freeText)
    }

    @RequestMapping(value = ["/native/search"], method = arrayOf(RequestMethod.GET))
    fun getNativePerson(@RequestParam freeText: String): List<Person> {
        return personDal.searchNativelyPerson(freeText)
    }
}