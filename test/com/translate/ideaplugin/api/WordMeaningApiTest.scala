package com.translate.ideaplugin.api

import com.translate.ideaplugin.ProjectTestCase
import com.translate.ideaplugin.config.LanguageConfigurable

/**
  * Created by yasudayousuke on 1/10/16.
  */
class WordMeaningApiTest extends ProjectTestCase{
  def testParseDescription: Unit = {
    val result = WordMeaningApi.parseDescription(
      projectTestDataText("http_text", "en_wiktionary_raw_word.txt"),
      LanguageConfigurable.DEFAULT_LANG)
    assertMatches(
      result == Some("unit of language")){
      case true =>
    }
  }
}
