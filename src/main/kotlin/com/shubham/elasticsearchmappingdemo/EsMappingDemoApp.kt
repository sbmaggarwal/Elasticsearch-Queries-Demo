package com.shubham.elasticsearchmappingdemo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class EsMappingDemoApp

fun main(args: Array<String>) {
    SpringApplication.run(EsMappingDemoApp::class.java, *args)
}