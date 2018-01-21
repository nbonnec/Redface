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

package com.ayuget.redface.data.api.hfr;

import com.ayuget.redface.data.api.MDEndpoints;
import com.ayuget.redface.data.api.MDPolls;
import com.ayuget.redface.data.api.hfr.transforms.HTMLToPollChoices;
import com.ayuget.redface.data.api.hfr.transforms.HTMLToPollResults;
import com.ayuget.redface.data.api.model.PollChoices;
import com.ayuget.redface.data.api.model.PollResults;
import com.ayuget.redface.data.api.model.Topic;
import com.ayuget.redface.data.api.model.User;
import com.ayuget.redface.network.PageFetcher;

import javax.inject.Inject;

import rx.Observable;

public class HFRPolls implements MDPolls {

    @Inject
    PageFetcher pageFetcher;

    @Inject
    MDEndpoints mdEndpoints;

    @Override
    public Observable<PollResults> getPollResults(User user, Topic topic) {
        return pageFetcher.fetchSource(user, mdEndpoints.topic(topic))
                .map(new HTMLToPollResults());
    }

    @Override
    public Observable<PollChoices> getPollChoices(User user, Topic topic) {
        return pageFetcher.fetchSource(user, mdEndpoints.topic(topic))
                .map(new HTMLToPollChoices());
    }
}
