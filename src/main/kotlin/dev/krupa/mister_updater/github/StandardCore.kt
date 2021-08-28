package dev.krupa.mister_updater.github

import org.kohsuke.github.GitHub

class StandardCore: GitHubCommittedCore {

    constructor(github: GitHub, repo: String, prefix: String): super(
        github,
        "MiSTer-devel/$repo",
        "releases",
        "${prefix}.rbf",
        Regex("${prefix}_[0-9]*\\.rbf"
        )
    )

}
