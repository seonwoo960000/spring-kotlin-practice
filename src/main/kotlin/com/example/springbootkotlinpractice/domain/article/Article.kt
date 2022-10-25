package com.example.springbootkotlinpractice.domain.article

import com.example.springbootkotlinpractice.domain.user.User
import com.example.springbootkotlinpractice.domain.util.toSlug
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

// JPA is not designed to be used with immutable classes(data keyword)


@Entity
class Article(
    var title: String,
    var headline: String,
    var content: String,
    @ManyToOne var author: User,
    var slug: String = title.toSlug(), // we can use the extension function
    var addedAt: LocalDateTime = LocalDateTime.now(), // parameters with default values are defined at the last position in order to make it possible to omit them when using positional arguments
    @Id @GeneratedValue var id: Long? = null
)