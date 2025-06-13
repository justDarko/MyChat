# MyChat

# Description

This is an Offline-First application that allows user to set their goals, track their progress, 
see the statistics about their achievements and chat with an LLM (Large Language Model) 
to ask every kind of questions, primarily goals related.

1. What user sees on the home screen is the title of that screen and list of all goals that he set.
User has an option to delete a goal, complete it and set it incomplete. At the bottom, user has an floating action button
which allows him to add new goal.
2. Second screen is dedicated for a chat with an LLM. For that purpose, pretrained model is used from [rapidapi.com](url).
Instructions on how to use it can be found on the following link: [https://rapidapi.com/rphrp1985/api/chatgpt-42](url).
3. Third screen is called "Stats" and its purpose is to show visually the statistics of user's achieved goals.

# Technical

1. Clean architecture with data, domain and presentation layers
2. Kotlin Coroutines & Flows for asynchronous data streams
3. Dependency Injection with Hilt
4. Version catalogs for managing Gradle dependencies in more organized way
5. Room database for caching the goals and chat conversation

# Presentation

![79ca04d8-7d9a-45de-8f88-ba6614ad0acf](https://github.com/user-attachments/assets/bc635838-b898-4617-8352-46e9bfe36127)
![70e28af7-c0f1-4723-97a3-3797df2681f8](https://github.com/user-attachments/assets/348d1cfa-418d-4d21-93bb-103096b27ba1)
![a2d87029-50b8-48a0-b6f6-1e153c646858](https://github.com/user-attachments/assets/f252625d-5c57-460f-9eaf-0c2211132414)
![536dd812-99dd-4bbe-9917-dc0d7e40b914](https://github.com/user-attachments/assets/f0dfc1b8-17f3-4479-beac-cdf4cfc400c9)
