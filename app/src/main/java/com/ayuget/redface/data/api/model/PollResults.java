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
public abstract class PollResults {
    @AutoValue
    public static abstract class Result {
        public abstract String name();
        public abstract float percent();
        public abstract int votes();

        public static Result create(String name, float percent, int votes) {
            return new AutoValue_PollResults_Result(name, percent, votes);
        }
    }
    public abstract String title();
    public abstract List<Result> results();

    public static PollResults create(String title, List<Result> results) {
        return new AutoValue_PollResults(title, results);
    }
}
