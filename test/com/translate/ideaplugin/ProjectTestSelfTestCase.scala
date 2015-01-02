package com.translate.ideaplugin

/**
  * Created by yasudayousuke on 1/10/16.
  */
class ProjectTestSelfTestCase extends ProjectTestCase{
  def testFileLoading: Unit = {
    val wiktionaryRawText = projectTestDataText("http_text", "en_wiktionary_raw_word.txt")
    assertMatches(wiktionaryRawText contains "{{wikipedia|dab=word (disambiguation)|word}}"){
      case true =>
    }
  }
}
