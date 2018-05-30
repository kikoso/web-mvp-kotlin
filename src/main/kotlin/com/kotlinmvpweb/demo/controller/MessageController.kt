package com.kotlinmvpweb.demo.controller

import com.kotlinmvpweb.demo.model.Greeting
import com.kotlinmvpweb.demo.model.Message
import com.kotlinmvpweb.demo.repository.MessageRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.concurrent.atomic.AtomicLong
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class MessageController(private val messageRepository: MessageRepository) {

    val counter = AtomicLong()

    @GetMapping("/greeting")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String) =
            Greeting(counter.incrementAndGet(), "Hello, $name")


    @GetMapping("/messages")
    fun getAllmessages(): List<Message> =
            messageRepository.findAll()


    @PostMapping("/messages")
    fun createNewMessage(@Valid @RequestBody message: Message): Message =
            messageRepository.save(message)


    @GetMapping("/messages/{id}")
    fun getMessageById(@PathVariable(value = "id") messageId: Long): ResponseEntity<Message> {
        return messageRepository.findById(messageId).map { message ->
            ResponseEntity.ok(message)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/messages/{id}")
    fun updateMessageById(@PathVariable(value = "id") messageId: Long,
                          @Valid @RequestBody newMessage: Message): ResponseEntity<Message> {

        return messageRepository.findById(messageId).map { existingMessage ->
            val updatedMessage: Message = existingMessage
                    .copy(title = newMessage.title, content = newMessage.content)
            ResponseEntity.ok().body(messageRepository.save(updatedMessage))
        }.orElse(ResponseEntity.notFound().build())

    }

    @DeleteMapping("/messages/{id}")
    fun deleteMessageById(@PathVariable(value = "id") messageId: Long): ResponseEntity<Void> {

        return messageRepository.findById(messageId).map { message  ->
            messageRepository.delete(message)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())

    }
}