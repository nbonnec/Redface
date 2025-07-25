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

package com.ayuget.redface.storage;

import com.ayuget.redface.data.api.model.User;
import com.jakewharton.disklrucache.DiskLruCache;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BoundedDiskCacheTest {
    DiskLruCache diskLruCache;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void setUp() throws Exception {
        diskLruCache = DiskLruCache.open(temporaryFolder.getRoot(), 0, 2, 1024*1024);
    }

    @Test
    public void testSerializeString() throws Exception {
        User dummyUser = new User("baz", "boo");

        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<String> stringAdapter = moshi.adapter(String.class);

        BoundedDiskCache<String, String> diskCache = new BoundedDiskCache<>(diskLruCache, stringAdapter);
        //diskCache.put(dummyUser, "foo", "bar");
    }
}
