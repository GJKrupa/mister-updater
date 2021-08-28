package dev.krupa.mister_updater

import java.nio.file.Path

/**
 * Interface representing a source for updating a MiSTer core.
 */
interface Core {

    fun download(location: Path)

}
