package com.translate.ideaplugin

import java.io.File
import java.nio.file.Paths

import com.intellij.openapi.vfs.{LocalFileSystem, VirtualFile, CharsetToolkit}
import org.jetbrains.plugins.scala.base.SimpleTestCase

/**
  * Created by yasudayousuke on 1/1/16.
  */
abstract class ProjectTestCase extends SimpleTestCase {
  def projTestDataPathString = {
    Paths.get(new File("./").getAbsolutePath,"..", "..", "testdata", "_project_data").toString;
    //FileUtilRt.loadFile(file, CharsetToolkit.UTF8, true)"testdata/"+filePath
  }

  def projectTestDataText(paths: String*) = {
    val source = scala.io.Source.fromFile(new File(
      Paths.get(
        projTestDataPathString, paths:_*).toString))
    source.getLines mkString "\n"
  }
}
