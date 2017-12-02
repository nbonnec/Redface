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

import com.ayuget.redface.BaseTestCase;
import com.ayuget.redface.data.api.model.PollResults;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class HTMLToPollResultsTest extends BaseTestCase {
    @Test
    public void test_parseHFRPoll() throws IOException {
        HTMLToPollResults htmlToPollResults = new HTMLToPollResults();

        PollResults pollResults = htmlToPollResults.call(readAssetFile("hfr_poll_results.html"));

        List<PollResults.ChoiceResult> choiceResults = new ArrayList<>();
        choiceResults.add(PollResults.ChoiceResult.create("1." + Character.toString((char) 0x00A0) + " Oui", (float) 16.7, 2));
        choiceResults.add(PollResults.ChoiceResult.create("2." + Character.toString((char) 0x00A0) + " Non", (float) 8.3, 1));
        choiceResults.add(PollResults.ChoiceResult.create("3." + Character.toString((char) 0x00A0) + " Peut-être", (float) 16.7, 2));
        choiceResults.add(PollResults.ChoiceResult.create("4." + Character.toString((char) 0x00A0) + " Caca", (float) 50, 6));
        choiceResults.add(PollResults.ChoiceResult.create("5." + Character.toString((char) 0x00A0) + " jj", (float) 8.3, 1));

        assertThat(pollResults.title()).isEqualTo("Est-ce un sondage?");
        assertThat(pollResults.choices()).isEqualTo(choiceResults);
    }
}
