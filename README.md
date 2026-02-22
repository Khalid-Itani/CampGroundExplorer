# Campground Explorer

Submitted by: **Khalid Itani**

**Campground Explorer** is an Android app that allows users to browse U.S. National Park campgrounds using data from the National Park Service (NPS) API. Users can view a list of campgrounds and tap on any campground to see more detailed information.

Time spent: **X hours** spent in total

---

## Required Features

The following **required** functionality is completed:

- [x] Fetch campground data from a public API (NPS Campgrounds API)
- [x] Display campground information in a scrollable list using RecyclerView
- [x] Show campground name, description, and location
- [x] Load and display images using Glide
- [x] Navigate to a detail screen when a campground is selected

---

## Optional Features

The following **optional** features are implemented:

- [x] Detail view showing full campground information
- [x] Card-based UI for improved visual layout
- [x] Error handling and logging for API failures

---

## Video Walkthrough

Here’s a walkthrough of the implemented features:

<img src="Campgrounds.gif" width="300" />

---

## Notes

- API keys are stored securely using a local `apikey.properties` file and are not committed to version control.
- The app uses Kotlin serialization to parse JSON responses.
- RecyclerView is used for efficient list rendering.

---

## License

    Copyright 2026 Khalid Itani

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.