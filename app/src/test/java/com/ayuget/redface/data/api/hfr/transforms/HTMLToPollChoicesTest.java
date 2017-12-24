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
import com.ayuget.redface.data.api.model.PollChoices;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class HTMLToPollChoicesTest extends BaseTestCase {

    @Test
    public void test_parseHFRPollChoices_one_choice() throws IOException {
        HTMLToPollChoices htmlToPollChoices = new HTMLToPollChoices();

        PollChoices pollChoices = htmlToPollChoices.call(readAssetFile("hfr_poll_one_choice.html"));

        List<String> choices = new ArrayList<>();
        choices.add("Je suis déjà à fond dedans");
        choices.add("J'ai commencé à domotiser quelques bricoles pour voir");
        choices.add("Pas encore, mais je m'y intéresse car j'envisage de m'y mettre prochainement");
        choices.add("Pas encore, mais j'envisage de m'y mettre sans un futur lointain quand tout cela sera plus abouti");
        choices.add("Aucun intérêt");
        choices.add("Obiwan n'a pas besoin de domotique, il utilise la Force");

        assertThat(pollChoices.title()).isEqualTo("La domotique et moi");
        assertThat(pollChoices.choiceNb()).isEqualTo(1);
        assertThat(pollChoices.choices()).isEqualTo(choices);
    }

    @Test
    public void test_parseHFRPollChoices_multiple_choices() throws IOException {
        HTMLToPollChoices htmlToPollChoices = new HTMLToPollChoices();

        PollChoices pollChoices = htmlToPollChoices.call(readAssetFile("hfr_poll_multiple_choices.html"));

        List<String> choices = new ArrayList<>();
        choices.add("Le constructeur");
        choices.add("La puissance du moteur de la base");
        choices.add("Les matériaux employés");
        choices.add("La fiabilité");
        choices.add("La roue");
        choices.add("Le pédalier");
        choices.add("La compatibilité avec d'autres dispositifs");
        choices.add("Le ressenti du FFB");

        assertThat(pollChoices.title()).isEqualTo("Quels sont les critères les plus importants lors de l'acquisition d'un ensemble volant/pédalier");
        assertThat(pollChoices.choiceNb()).isEqualTo(3);
        assertThat(pollChoices.choices()).isEqualTo(choices);
    }

}
