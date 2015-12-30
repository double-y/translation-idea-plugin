package com.translate.ideaplugin

import java.net.URI
import javax.swing.event.HyperlinkEvent

import com.intellij.ide.browsers.BrowserLauncher
import com.intellij.notification.{NotificationListener, NotificationType, Notification, Notifications}
import com.intellij.openapi.actionSystem.{AnActionEvent, AnAction}
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.fileEditor.FileEditorManager
import com.translate.ideaplugin.api.WordMeaningApi
import com.translate.ideaplugin.config.LanguageConfig

/**
  * Created by yasudayousuke on 12/29/15.
  */
class TranslatePlugin extends AnAction{
  override def actionPerformed(anActionEvent: AnActionEvent): Unit = {
    val config = ServiceManager.getService(classOf[LanguageConfig])
    val editor = FileEditorManager.getInstance(anActionEvent.getProject).getSelectedTextEditor()
    val text = editor.getSelectionModel().getSelectedText().toLowerCase
    val meaning = WordMeaningApi.fetchMeaning(text, config.lang)
    meaning match {
      case Some(string) =>
        val url = "https://en.wiktionary.org/wiki/" + text
        val aTag = "<a href=\"" + url + "\">" + url + "</a>"
        Notifications.Bus.notify(new Notification("meaning", string, aTag, NotificationType.INFORMATION, new NotificationListener {
          override def hyperlinkUpdate(notification: Notification, hyperlinkEvent: HyperlinkEvent): Unit = {
            BrowserLauncher.getInstance().browse(new URI(url))
          }
        }))
      case None => Notifications.Bus.notify(new Notification("meaning", "not found", text, NotificationType.INFORMATION))
    }
  }
}
