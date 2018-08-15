package com.line.fukuokabserver

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan
class FukuokaBServerApplication

fun main(args: Array<String>) {
    runApplication<FukuokaBServerApplication>(*args)
}
