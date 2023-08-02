package com.example.drugstore.pharmacy.cache

import com.example.drugstore.AbstractIntegrationContainerBaseTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate


class RedisTemplateTest extends AbstractIntegrationContainerBaseTest{

    @Autowired
    private RedisTemplate redisTemplate

    def "RedisTemplate String operations"(){
        given:
        def valueOperations = redisTemplate.opsForValue()
        def key = "stringKey"
        def value = "hello"

        when:
        valueOperations.set(key, value)

        then:
        def result = valueOperations.get(key)
        result == value
    }

    def "RedisTemplate set operations"(){
        given:
        def setOperations = redisTemplate.opsForSet()
        def key = "setKey"

        when:
        setOperations.add(key,"h","e","l","l","o")

        then:
        def size = setOperations.size(key)
        size == 4
    }

    def "RedisTemplate hash operations"() {
        given:
        def hashOperations = redisTemplate.opsForHash()
        def key = "hashKey"

        when:
        hashOperations.put(key,"subKey","value")

        then:
        def result = hashOperations.get(key, "subKey")
        result == "value"

        def entries = hashOperations.entries(key)
        entries.keySet().contains("subKey")
        entries.values().contains("value")

        def size = hashOperations.size(key)
        size == entries.size()


    }
}
