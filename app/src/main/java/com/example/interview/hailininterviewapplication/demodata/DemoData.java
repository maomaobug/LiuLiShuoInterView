package com.example.interview.hailininterviewapplication.demodata;

import com.example.interview.hailininterviewapplication.FuriganaUnit;

import java.util.ArrayList;

/**
 * Created by Interview on 1/19/16.
 */
public abstract class DemoData {
    public static final String text_ipas_details = "[ " +
            "[{text: '天', ipa: 'おは', ipaVisible: ture}, {text: '気', ipa: 'き', ipaVisible: true}]," +
            " [{text: '予', ipa: 'よ', ipaVisible: true}, {text: '報', ipa: 'ほう', ipaVisible: true}]," +
            " [{text: 'によ', ipa: 'によ', ipaVisible: false}, {text: 'る', ipa: 'る', ipaVisible: false}]," +
            " [{text: 'と', ipa: 'と', ipaVisible: false}] " +
            "]";

    public static final ArrayList<FuriganaUnit> demoModels = new ArrayList<>();

    static {
        demoModels.add(new FuriganaUnit("天", "おは", true));
        demoModels.add(new FuriganaUnit("気", "き", true));
        demoModels.add(new FuriganaUnit(" ", "", false));

        demoModels.add(new FuriganaUnit("予", "よ", true));
        demoModels.add(new FuriganaUnit("報", "ほう", true));
        demoModels.add(new FuriganaUnit(" ", "", false));

        demoModels.add(new FuriganaUnit("によ", "によ", false));
        demoModels.add(new FuriganaUnit("る", "る", false));
        demoModels.add(new FuriganaUnit(" ", "", false));

        demoModels.add(new FuriganaUnit("と", "と", false));
    }
}
