package dev.krupa.mister_updater

import dev.krupa.mister_updater.github.StandardCore
import org.kohsuke.github.GitHub
import java.nio.file.Path

fun main() {
    val client = GitHub.connect()

    val cores = listOf(
        StandardCore(client, "Menu_MiSTer", "menu"),
        StandardCore(client, "Minimig-AGA_MiSTer", "Minimig"),
        StandardCore(client, "Amstrad-PCW_MiSTer", "Amstrad-PCW"),
        StandardCore(client, "AtariST_MiSTer", "AtariST"),
        StandardCore(client, "C64_MiSTer", "C64"),
        StandardCore(client, "ZX-Spectrum_MISTer", "ZX-Spectrum"),
        StandardCore(client, "Amstrad_MiSTer", "Amstrad"),
        StandardCore(client, "SAM-Coupe_MiSTer", "SAMCoupe"),
        StandardCore(client, "MemTest_MiSTer", "memtest"),
    )

    val output = Path.of(".", "staging")
    output.toFile().mkdirs()

    cores.forEach {
        it.download(output)
    }

}

