package dev.krupa.mister_updater.github

import dev.krupa.mister_updater.Core
import org.kohsuke.github.GitHub
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption

open class GitHubCommittedCore(
    private val github: GitHub,
    private val repository: String,
    private val path: String,
    private val name: String,
    private val filter: Regex = Regex(".*")
): Core {
    override fun download(location: Path) {
        println("Reading from $this")
        val repo = github.getRepository(repository)
        val last = repo.getDirectoryContent(path, repo.defaultBranch)
            .filter { filter.matches(it.name) }
            .toMutableList()
            .maxByOrNull { it.name }!!
        val output = location.resolve(name)
        var tree = repo.getCommit(repo.defaultBranch).tree

        val pathComponents = last.path.split("/")
        pathComponents.dropLast(1).forEach {
            tree = tree.getEntry(it).asTree()
        }

        val entry = tree.getEntry(pathComponents.last())

        println("Copying ${last.path} to $output")

        entry.readAsBlob().use {
            Files.copy(it, output, StandardCopyOption.REPLACE_EXISTING)
        }
    }

    override fun toString(): String {
        return "${repository}/${path}/${filter}"
    }

}
