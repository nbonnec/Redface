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

package com.ayuget.redface.data.api;

import com.ayuget.redface.data.api.model.PollChoices;
import com.ayuget.redface.data.api.model.PollResults;
import com.ayuget.redface.data.api.model.Topic;
import com.ayuget.redface.data.api.model.User;

import rx.Observable;

public interface MDPolls {

    Observable<PollResults> getPollResults(User user, Topic topic);

    Observable<PollChoices> getPollChoices(User user, Topic topic);
}