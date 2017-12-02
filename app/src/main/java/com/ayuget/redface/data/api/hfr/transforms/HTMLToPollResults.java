package com.ayuget.redface.data.api.hfr.transforms;
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

import android.support.annotation.NonNull;

import com.ayuget.redface.data.api.model.PollResults;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Func1;
import timber.log.Timber;

public class HTMLToPollResults implements Func1<String, PollResults> {
    private static final String POLL_ELEMENT = "div.sondage";
    private static final String TITLE_ELEMENT = "b.s2";
    private static final String CHOICE_ELEMENT = "div.sondageRight";
    private static final String PERCENT_ELEMENT = "div.sondageTop[style=position:absolute;left:0px]";
    private static final String VOTES_ELEMENT = "div.sondageTop:not([style])";
    @Override
    @NonNull
    public PollResults call(String s) {
        List<PollResults.ChoiceResult> choiceResults = new ArrayList<>();

        Element poll = Jsoup.parse(s).select(POLL_ELEMENT).first();

        if (poll != null) {
            Elements choices = poll.select(CHOICE_ELEMENT);
            Elements percents = poll.select(PERCENT_ELEMENT);
            Elements votes = poll.select(VOTES_ELEMENT);

            int nbChoices = Math.min(Math.min(choices.size(), percents.size()), votes.size());

            for (int i = 0; i < nbChoices; i++) {
                Element choice = choices.get(i);
                Element percent = percents.get(i);
                Element vote = votes.get(i);

                if (choice != null && percent != null && vote != null) {
                    PollResults.ChoiceResult choiceResult = PollResults.ChoiceResult.create(
                            choices.get(i).text(),
                            Float.valueOf(percents.get(i).text().replaceFirst("(\\d+\\.\\d+).*%", "$1")),
                            Integer.valueOf(votes.get(i).text().replaceFirst(".*(\\d+) votes?+", "$1")));
                    Timber.d(choiceResult.toString());
                    choiceResults.add(choiceResult);
                }
            }

            Element title = poll.select(TITLE_ELEMENT).first();
            if (title != null) {
                Timber.d(title.text());
                return PollResults.create(title.text(), choiceResults);
            }
        }

        List<PollResults.ChoiceResult> problemList = new ArrayList<>();
        problemList.add(PollResults.ChoiceResult.create("", 0, 0));

        return PollResults.create("Problem", problemList);
    }
}
