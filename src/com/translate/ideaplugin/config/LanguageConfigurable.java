package com.translate.ideaplugin.config;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.ui.ComboBox;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by yasudayousuke on 12/29/15.
 */
public class LanguageConfigurable implements Configurable {
    final static public String DEFAULT_LANG = "English";
    final static public String[] Langs = new String[]{
            DEFAULT_LANG,
            "Abkhaz",
            "Adyghe",
            "Afrikaans",
            "Albanian",
            "Amharic",
            "Amuzgo",
            "Arabic",
            "Egyptian Arabic",
            "Hejazi Arabic",
            "Aragonese",
            "Aramaic",
            "Syriac",
            "Hebrew",
            "Archi",
            "Armenian",
            "Aromanian",
            "Assamese",
            "Asturian",
            "Avar",
            "Azeri",
            "Bashkir",
            "Basque",
            "Belarusian",
            "Bengali",
            "Breton",
            "Bulgarian",
            "Burmese",
            "Buryat",
            "Catalan",
            "Chamicuro",
            "Chechen",
            "Cherokee",
            "Chinese",
            "Mandarin",
            "Chuvash",
            "Crimean Tatar",
            "Czech",
            "Danish",
            "Dhivehi",
            "Dutch",
            "Eastern Mari",
            "Egyptian",
            "Erzya",
            "Esperanto",
            "Estonian",
            "Even",
            "Faroese",
            "Finnish",
            "French",
            "Friulian",
            "Galician",
            "Georgian",
            "German",
            "Greek",
            "Greenlandic",
            "Haitian Creole",
            "Hausa",
            "Hawaiian",
            "Hebrew",
            "Hindi",
            "Hungarian",
            "Icelandic",
            "Ido",
            "Ilocano",
            "Indonesian",
            "Ingush",
            "Interlingua",
            "Irish",
            "Italian",
            "Japanese",
            "Javanese",
            "Kabardian",
            "Kalmyk",
            "Kannada",
            "Karachay-Balkar",
            "Karelian",
            "Kashubian",
            "Kazakh",
            "Khmer",
            "Korean",
            "Kurdish",
            "Kurmanji",
            "Sorani",
            "Kyrgyz",
            "Ladin",
            "Ladino",
            "Lak",
            "Lao",
            "Latgalian",
            "Latin",
            "Latvian",
            "Laz",
            "Ligurian",
            "Lingala",
            "Lithuanian",
            "Lojban",
            "Luxembourgish",
            "Macedonian",
            "Malay",
            "Malayalam",
            "Maltese",
            "Maori",
            "Marathi",
            "Mingrelian",
            "Moksha",
            "Mongolian",
            "Nahuatl",
            "Nauruan",
            "Navajo",
            "Nepali",
            "North Frisian",
            "Föhr-Amrum",
            "Helgoland",
            "Mooring",
            "Northern Yukaghir",
            "Norwegian",
            "Okinawan",
            "Old Church Slavonic",
            "Cyrillic",
            "Glagolitic",
            "Old East Slavic",
            "Old Norse",
            "Oriya",
            "Ossetian",
            "Papiamentu",
            "Pashto",
            "Persian",
            "Polish",
            "Portuguese",
            "Punjabi",
            "Romanian",
            "Romansch",
            "Russian",
            "Rusyn",
            "Samoan",
            "Samogitian",
            "Sanskrit",
            "Santali",
            "Scots",
            "Scottish Gaelic",
            "Serbo-Croatian",
            "Cyrillic",
            "Roman",
            "Sicilian ",
            "Sindhi",
            "Sinhalese",
            "Skolt Sami",
            "Slovak",
            "Slovene",
            "Somali",
            "Sorbian",
            "Lower Sorbian",
            "Upper Sorbian",
            "Sotho",
            "Spanish",
            "Sundanese",
            "Svan",
            "Swahili",
            "Swedish",
            "Tagalog",
            "Tahitian",
            "Tajik",
            "Tamil",
            "Tatar",
            "Telugu",
            "Thai",
            "Tibetan",
            "Tigrinya",
            "Tofa",
            "Tongan",
            "Tswana",
            "Turkish",
            "Turkmen",
            "Tuvan",
            "Udmurt",
            "Ukrainian",
            "Urdu",
            "Uyghur",
            "Uzbek",
            "Venetian",
            "Vietnamese",
            "Volapük",
            "Walloon",
            "Welsh",
            "West Frisian",
            "Wolof",
            "Xhosa",
            "Yakut",
            "Yiddish",
            "Yoruba",
            "Zulu"
    };

    private String settingLang;

    private JPanel myPanel;
    private ComboBox langComboBox;

    final LanguageConfig config = ServiceManager.getService(LanguageConfig.class);

    public String getDisplayName() {
        return "Translate Language Setting";
    }

    public boolean isModified() {
        return !(config.lang.equals(settingLang));
    }

    private void setFromConfig() {
        if(config.lang == null){
            config.lang = DEFAULT_LANG;
        }
        myPanel = new JPanel();
        langComboBox = new ComboBox(Langs);
        langComboBox.addItemListener (new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    settingLang = (String) e.getItem();
                }
            }
        });
        myPanel.add(langComboBox);
    }

    public JComponent createComponent() {
        setFromConfig();
        return myPanel;
    }

    public void apply() {
        if (!isModified()) {
            return;
        }
        config.lang = settingLang;
    }

    public void disposeUIResources() {
        myPanel.removeAll();
    }

    public String getHelpTopic() {
        return "preferences.topic";
    }

    public void reset() {
        setFromConfig();
    }
}
