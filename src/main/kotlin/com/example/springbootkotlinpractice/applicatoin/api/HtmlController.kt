package com.example.springbootkotlinpractice.applicatoin.api

import com.example.springbootkotlinpractice.domain.article.Article
import com.example.springbootkotlinpractice.domain.article.ArticleRepository
import com.example.springbootkotlinpractice.domain.user.User
import com.example.springbootkotlinpractice.domain.util.format
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
// an extension function to write model["title"] = "Blog" instead of writing model.addAttribute("title", "Blog")
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.server.ResponseStatusException

@Controller
@RequestMapping("/page")
class HtmlController(private val repository: ArticleRepository) {

    @GetMapping
    fun blog(model: Model): String {
        model["title"] = "Blog"
        model["articles"] = repository.findAllByOrderByAddedAtDesc().map { it.render() }
        return "blog"
    }

    @GetMapping("/article/{slug}")
    fun article(@PathVariable slug: String, model: Model): String {
        val article = repository
            .findBySlug(slug)
            ?.render()
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "This article does not exist")
        model["title"] = article.title
        model["article"] = article
        return "article"
    }

    fun Article.render() = RenderedArticle(
        slug,
        title,
        headline,
        content,
        author,
        addedAt.format()
    )

    data class RenderedArticle(
        val slug: String,
        val title: String,
        val headline: String,
        val content: String,
        val author: User,
        val addedAt: String
    )
}