/*
 * Copyright 2015 Ayuget
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
import com.ayuget.redface.data.api.model.Post;
import com.ayuget.redface.data.api.model.Smiley;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class HTMLToSmileyListTest extends BaseTestCase {
    @Test
    public void test_parsePosts() throws IOException {
        HTMLToSmileyList htmlToSmileyList = new HTMLToSmileyList();

        List<Smiley> smileys = htmlToSmileyList.call(readAssetFile("hfr_smiley_search.html"));

        assertThat(smileys.size()).isEqualTo(53);
        assertThat(smileys.get(0).code()).isEqualTo("[:retarded_roger]");
    }
}
