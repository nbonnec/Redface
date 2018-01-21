package com.ayuget.redface.data.api.hfr;
/*
 * Copyright 2018 nbonnec
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

import com.ayuget.redface.data.api.model.PollType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

public class PollTypeExtractor {

    public static PollType extract(String s) {
        String POLL_ELEMENT = "div.sondage";
        Element poll = Jsoup.parse(s).select(POLL_ELEMENT).first();

        if (poll == null) {
            return PollType.NO_POLL;
        }
        String INPUT_ELEMENT = "input";
        if (poll.select(INPUT_ELEMENT).first() == null) {
            return PollType.POLL_RESULT;
        }
        return PollType.POLL_CHOICES;
    }
}
