package com.translate.ideaplugin.api

import java.util

import com.translate.ideaplugin.config.LanguageConfigurable
import org.apache.http.client.config.RequestConfig
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.utils.URLEncodedUtils
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.message.{BasicNameValuePair, BasicHeader}
import org.apache.http.util.EntityUtils

/**
  * Created by yasudayousuke on 12/29/15.
  */
object WordMeaningApi {
  def accessWikitionaryApi(word: String) = {
    val socketTimeout = 3;
    val connectionTimeout = 3;
    val userAgent = "My Http Client 0.1";
    // request configuration
    val requestConfig = RequestConfig.custom()
      .setConnectTimeout(connectionTimeout)
      .setSocketTimeout(socketTimeout)
      .build();
    // headers
    val headers = new util.ArrayList[BasicHeader]();
    headers.add(new BasicHeader("Accept-Charset","utf-8"));
    headers.add(new BasicHeader("Accept-Language","ja, en;q=0.8"));
    headers.add(new BasicHeader("User-Agent",userAgent));
    val parameters = new util.ArrayList[BasicNameValuePair]();
    parameters.add(new BasicNameValuePair("action", "raw"));
    parameters.add(new BasicNameValuePair("title", word))
    val query = URLEncodedUtils.format(parameters, "UTF-8");
    val url = "https://en.wiktionary.org/w/api.php?" + query;
    val httpGet = new HttpGet("https://en.wiktionary.org/w/index.php?" + query)
    val httpclient = HttpClientBuilder.create().build()
    EntityUtils.
      toString(httpclient.
        execute(httpGet).
        getEntity(), "UTF-8")
  }

  def parseDescription(text: String, lang: String):Option[String] = {
    val pattern = if (lang == LanguageConfigurable.DEFAULT_LANG)  "^\\{\\{trans-top.*" else  s"^\\*:? ${lang}: \\{\\{.*"
    val line = text.split("\n") find (_.matches(pattern))
    line match{
      case Some(string) =>
        lang match {
          case LanguageConfigurable.DEFAULT_LANG =>
            Some(string.replace("{{trans-top|", "").replace("}}", ""))
          case default =>
            val array = string.split("""\|""")
            if (array.length > 3) Some(string.split("""\|""")(2)) else None
        }
      case None => None
    }
  }

  def fetchMeaning(word: String, lang: String = LanguageConfigurable.DEFAULT_LANG): Option[String] = {
    parseDescription(accessWikitionaryApi(word), lang)
  }
}
