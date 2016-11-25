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

package com.ayuget.redface.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ayuget.redface.data.api.model.Topic;
import com.ayuget.redface.ui.fragment.PostsFragmentBuilder;

public class TopicPageAdapter extends FragmentStatePagerAdapter {
    private Topic topic;
    private final int initialPage;
    private int lastVisiblePage;

    public TopicPageAdapter(FragmentManager fm, Topic topic, int initialPage) {
        super(fm);
        this.topic = topic;
        this.initialPage = initialPage;
    }

    public void notifyTopicUpdated(Topic topic) {
        this.topic = topic;
        notifyDataSetChanged();
    }

    public void setLastVisiblePage(int page) {
        lastVisiblePage = page;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int i) {
        // i + 1 because we want pages number to start at 1 and not 0
        return new PostsFragmentBuilder(i + 1, initialPage, topic).build();
    }

    @Override
    public int getCount() {
        return topic.pagesCount();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String extra = "";

        if (position == lastVisiblePage && position != topic.pagesCount()) {
            int remainder = topic.pagesCount() - position;
            if (remainder > 99) {
                extra = " (99+)";
            } else {
                extra = " (" + String.valueOf(topic.pagesCount() - position) + ")";
            }
        }

        return "Page " + String.valueOf(position + 1) + extra;
    }
}
