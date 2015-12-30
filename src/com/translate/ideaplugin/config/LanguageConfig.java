package com.translate.ideaplugin.config;

/**
 * Created by yasudayousuke on 12/29/15.
 */

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.Nullable;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import com.sun.org.apache.bcel.internal.generic.LAND;
import org.jetbrains.annotations.Nullable;

/**
 * Created by yasudayousuke on 8/13/15.
 * ref: http://lab.aratana.jp/entry/2015/02/16/184025
 */
@State(
        name = "LanguageConfig",
        storages = {
                @Storage(
                        id = "other",
                        file = "$APP_CONFIG$/idea_translate.xml")
        })
public class LanguageConfig implements PersistentStateComponent<LanguageConfig> {
    public String lang = LanguageConfigurable.DEFAULT_LANG;

    public boolean isConfigure() {
        return !lang.isEmpty();
    }

    @Nullable
    @Override
    public LanguageConfig getState() {
        return this;
    }

    @Override
    public void loadState(LanguageConfig languageConfig) {
        XmlSerializerUtil.copyBean(languageConfig, this);
    }
}
