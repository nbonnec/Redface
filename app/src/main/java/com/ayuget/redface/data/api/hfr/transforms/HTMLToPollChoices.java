/*
 * Copyright 2017 nbonnec
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ayuget.redface.data.api.hfr.transforms;

import com.ayuget.redface.data.api.model.PollChoices;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rx.functions.Func1;
import timber.log.Timber;

public class HTMLToPollChoices implements Func1<String, PollChoices> {
    private static final String POLL_ELEMENT = "div.sondage";
    private static final String TITLE_ELEMENT = "b.s2";
    private static final String CHOICE_ELEMENT = "label";

    private static final Pattern MULTIPLE_CHOICE_PATTERN = Pattern.compile("Sondage à (\\d) choix possibles");

    @Override
    public PollChoices call(String s) {
        List<String> choices = new ArrayList<>();

        Element poll = Jsoup.parse(s).select(POLL_ELEMENT).first();

        if (poll != null) {
            Matcher nbChoiceMatcher = MULTIPLE_CHOICE_PATTERN.matcher(poll.text());

            int choiceNb;
            if (nbChoiceMatcher.find()) {
                choiceNb = Integer.valueOf(nbChoiceMatcher.group(1));
            } else {
                choiceNb = 1;
            }

            Elements c = poll.select(CHOICE_ELEMENT);

            for (Element e: c) {
                choices.add(e.text());
            }

            Element title = poll.select(TITLE_ELEMENT).first();
            if (title != null) {
                Timber.d(title.text());
                return PollChoices.create(title.text(), choiceNb, choices);
            }
        }

        List<String> problemList = new ArrayList<>();
        problemList.add("");

        return PollChoices.create("Problem on poll!", 1, problemList);
    }
}
