package com.exadel.erusak.scala.common

import java.io.File

/**
 * @author erusak.
 */
object FilesMatcher {
  private def fileList = new File("/home/kasur/tmp").listFiles
  private def matchFiles(matcher: (String) => Boolean) =
    for(file <- fileList; if matcher(file.getName))
      yield file

  def matchEnds(query: String) = matchFiles(_.endsWith(query))
}
