package de.jensklingenberg.scrako.builder

import de.jensklingenberg.scrako.model.Costume
import de.jensklingenberg.scrako.model.ScratchProject
import kotlinx.serialization.json.Json
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.file.Files
import java.nio.file.StandardCopyOption
import java.security.MessageDigest
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream


internal fun copyFiles(inputPath: String, targetPath: String, costumes: List<Costume>) {
    val soundFiles = File(inputPath + "sounds").listFiles()

    soundFiles?.forEach {
        Files.copy(it.toPath(), File("$targetPath/${it.name}").toPath(), StandardCopyOption.REPLACE_EXISTING)
    }

    costumes.forEach {
        val fileName = if(it.isCustom) it.name else it.assetId
        try {
            val fil = File(inputPath+"sprites/${fileName}.${it.dataFormat}")

            Files.copy(fil.toPath(), File(targetPath+"/${it.assetId}.${it.dataFormat}").toPath(), StandardCopyOption.REPLACE_EXISTING)
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
}

internal fun getFileMD5(file: File): String {
    val buffer = ByteArray(1024)
    val md = MessageDigest.getInstance("MD5")
    FileInputStream(file).use { fis ->
        var bytesRead: Int
        while (fis.read(buffer).also { bytesRead = it } != -1) {
            md.update(buffer, 0, bytesRead)
        }
    }
    val md5Bytes = md.digest()
    return md5Bytes.joinToString("") { "%02x".format(it) }
}

fun writeProject(scratchProject: ScratchProject, inputPath: String, targetPath: String, fileName: String) {

    copyFiles(inputPath, targetPath, emptyList())

    val text = Json.encodeToString(ScratchProject.serializer(), scratchProject)

    File("$targetPath/project.json").writeText(text)

    val filesToZip = File(targetPath).listFiles()?.filter { !it.path.endsWith("sb3") }?.toList() ?: emptyList()
    val outputZipFile = File("$targetPath/$fileName")
    zipFiles(filesToZip, outputZipFile)

}

internal fun zipFiles(files: List<File>, outputZipFile: File) {

    ZipOutputStream(FileOutputStream(outputZipFile)).use { zipOut ->
        files.forEach { file ->
            FileInputStream(file).use { fis ->
                val zipEntry = ZipEntry(file.name)
                zipOut.putNextEntry(zipEntry)
                fis.copyTo(zipOut)
            }
        }
    }
}


