package com.ayuget.redface.data.api.model;
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

import com.google.auto.value.AutoValue;

import java.util.List;

@AutoValue
public abstract class PollChoices {

    public abstract String title();
    public abstract int choiceNb();
    public abstract List<String> choices();

    public static PollChoices create(String title, int choiceNb, List<String> choices) {
        return new AutoValue_PollChoices(title, choiceNb, choices);
    }
}
