package com.exadel.erusak.core.dry

import java.nio.file.Files
import java.nio.file.Files.createTempFile

import com.exadel.erusak.core.dry.DryCodeDomain._
import org.scalatest.{ShouldMatchers, FlatSpec}

/**
 * @author erusak.
 */
class DryTestSuite extends FlatSpec with ShouldMatchers {

  val emails = Seq(
    Email("Subject 1", "Text 1", "erusak1@exadel.com", "erusak1@exadel.com"),
    Email("Subject 2", "Text 2", "erusak2@exadel.com", "erusak2@exadel.com"),
    Email("Subject 3", "Text 3", "erusak3@exadel.com", "erusak3@exadel.com")
  )

  "File" should "be created, operated upon and closed" in {
    val tempFile = createTempFile("blabla", null)
    val os = Files.newOutputStream(tempFile)
    withCloseable(os) {stream =>
      stream.write(111)
    }

    val is = Files.newInputStream(tempFile)
    withCloseable(is) { stream =>
      stream.read() should be ( 111 )
    }
  }

  "sendBy filter" should "pass" in {
    newMailsForUser(emails, sendBy("kasur")) should be ('empty)
    newMailsForUser(emails, sendBy("erusak1@exadel.com")) should contain (Email("Subject 1", "Text 1", "erusak1@exadel.com", "erusak1@exadel.com"))
  }

}
