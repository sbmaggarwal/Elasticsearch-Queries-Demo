package com.shubham.elasticsearchmappingdemo.model

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.*
import javax.annotation.Generated

@Document(indexName="search_test", type = "person")
class Person {

    @Id
    @Generated
    var id: String? = null


    /**
     * name is stored as a main field and other field.
     * -> main field is analyzed which means it will be broken into tokens
     * -> other field is not_analyzed which means it will not be broken and can be matched
     *      only as a whole String. this field is accessed via name.verbal access parameter
     */
    @MultiField(
            mainField = Field(type = FieldType.String),
            otherFields = arrayOf(
                    InnerField(
                            index = FieldIndex.not_analyzed,
                            suffix = "verbal",
                            type = FieldType.String))
    )
    var name: String? = null
}