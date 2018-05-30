package com.kotlinmvpweb.demo.repository

import com.kotlinmvpweb.demo.model.Message
import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.JpaRepository

@Repository
interface MessageRepository : JpaRepository<Message, Long>