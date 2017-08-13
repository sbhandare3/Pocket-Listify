# Pre-work - *Pocket Listify*

**Pocket Listify** is an android app that allows building a todo list and basic todo items management functionality including adding new items, editing and deleting an existing item.

Submitted by: **Shreyas Sadanand Bhandare**

Time spent: **20** hours spent in total

## User Stories

The following **required** functionality is completed:

* [ ] User can **successfully add and remove items** from the todo list
* [ ] User can **tap a todo item in the list and bring up an edit screen for the todo item** and then have any changes to the text reflected in the todo list.
* [ ] User can **persist todo items** and retrieve them properly on app restart

The following **optional** features are implemented:

* [ ] Persist the todo items [into SQLite](http://guides.codepath.com/android/Persisting-Data-to-the-Device#sqlite) instead of a text file
* [ ] Improve style of the todo items in the list [using a custom adapter](http://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView)
* [ ] Add support for completion due dates for todo items (and display within listview item)
* [ ] Use a [DialogFragment](http://guides.codepath.com/android/Using-DialogFragment) instead of new Activity for editing items
* [ ] Add support for selecting the priority of each todo item (and display in listview item)
* [ ] Tweak the style improving the UI / UX, play with colors, images or backgrounds

The following **additional** features are implemented:

* [ ] App sends push notifications in every morning regarding how many tasks are due today. 
* [ ] App includes introduction screens which tells functionality and runs when app is installed first time. 

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='http://imgur.com/keiJaJQ.gif' title='Video Walkthrough' width='600' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Project Analysis

As part of your pre-work submission, please reflect on the app and answer the following questions below:

**Question 1:** "What are your reactions to the Android app development platform so far? Compare and contrast Android's approach to layouts and user interfaces in past platforms you've used."

**Answer:** I think Android developement has really improved a lot in last 4-5 years. Documentation is proper and well managed, updates are frequent, new tools like Kotlin and Realm are emerging. It really has bright future! Being said that one really has to be dynamic enough to keep updated with new changes with API and think in terms of UI/UX aspect more because Android Phone Applications are used frequently than Computer/Desktop based applications. 

Layouts and User Interface designs are really tricky in Android especially if you are doing native development. I have used bootstrap, javascript, jQuery for interactive and responsive UI for PC web applications. For Android, buidling reponsive layouts is tricky, because not a single Layout is responsive enough. It has to be combination of layouts such as ConstraintLayout, LinearLayout, TableLayout and so on. I have used ConstraintLayout mostly in my project as it's newly launched and supposed to be more responsive than RelativeLayout. 

**Question 2:** "Take a moment to reflect on the `ArrayAdapter` used in your pre-work. How would you describe an adapter in this context and what is its function in Android? Why do you think the adapter is important? Explain the purpose of the `convertView` in the `getView` method of the `ArrayAdapter`."

**Answer:** An adapter is a bridge between UI component and data that helps us to fill data in UI component like ListView. It holds the data and send the data to adapter view then view can takes the data from the adapter view and shows the data on different views like listview. Adapter is importnat because our task needs to be listed in listview and binding arraylist to listview is only possible using ArrayAdapter. 

Coming to ConvertView, ConvertView is used for performance optimization by not recreating view that was created already. in short it converts view of each row which is not used into inflated new view. 

## Notes

Challenges: I didn't have too much experience with push notifactions and took a while to get into it. 

## License

    Copyright [yyyy] [name of copyright owner]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
