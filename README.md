# Movie Recommendation System (Java Swing)

## Project Overview

This is a **Java Swing-based desktop application** that offers a personalized movie recommendation experience through an intuitive multi-page GUI. The system lets users select their preferred language and genre, explore detailed movie information with posters and trailers, rate movies, and receive smart recommendations based on their ratings — all within a seamless navigation flow powered by `CardLayout`.

## Features

- **Dynamic Multi-Page Navigation**  
  Users move smoothly through selection, movie details, rating, and recommendation pages without opening multiple windows.

- **Language & Genre Selection**  
  Choose from multiple languages (English, Tamil, Hindi, Spanish) and genres (Comedy, Sci-Fi, Drama, Action, Horror).

- **Rich Movie Info Display**  
  Each movie shows a poster image, detailed description, and clickable trailer links that open in the default browser.

- **Interactive Rating System**  
  Rate movies using a star-based UI (1 to 5 stars), enabling personalized feedback.

- **Personalized Recommendations**  
  Based on user ratings, the system suggests new movies with similar language but potentially different genres for discovery.

- **User History Management**  
  Tracks which movies a user has rated to avoid repeating suggestions.

- **Robust Input Validation & Error Handling**  
  Ensures smooth user experience with meaningful messages on invalid input or exhausted recommendations.

## Technology Stack

- **Java SE (Standard Edition)**  
- **Java Swing**: For rich desktop GUI with multi-panel layouts and interactive components.  
- **Object-Oriented Design**: MVC-style organization into model, service, and UI panels.  
- **CardLayout**: Seamless multi-view application architecture.

## Project Structure

```
src/
 ├─ model/
 │    ├─ Movie.java           # Movie data model with poster and trailer info
 │    ├─ Language.java        # Supported movie languages enum
 │    ├─ Genre.java           # Supported movie genres enum
 │    └─ UserHistory.java     # Stores user ratings history
 ├─ service/
 │    └─ RecommendationService.java  # Logic for fetching and recommending movies
 └─ ui/
      ├─ MovieRecommenderMain.java    # Main JFrame with CardLayout navigation
      ├─ SelectionPanel.java          # Language and Genre selection UI
      ├─ MovieInfoPanel.java          # Movie details with images and trailer link
      ├─ RatePanel.java               # Interactive star rating UI
      └─ RecommendationPanel.java    # Displays lists of recommended movies
```

## How It Works

1. **Start**: User selects preferred movie language and genre.  
2. **Movie Display**: App shows a matching movie with poster and description. Trailer links are clickable in the interface.  
3. **Rating**: User rates the movie using stars (1-5).  
4. **Recommendations**: App suggests next movies personalized based on previous rating (explores similar and diverse genres).  
5. **Repeat or Finish**: User can continue exploring or finish the session.
6. **Poster images and trailer URLs for richer movie display.**

## Setup & Running

1. **Download/clone** this repository.  
2. Ensure `src` folder structure is intact with proper package names (`model`, `service`, `ui`).  
3. Compile all Java files:
   ```bash
   javac src/model/*.java src/service/*.java src/ui/*.java
   ```
4. Run the application:
   ```bash
   java -cp src ui.MovieRecommenderMain
   ```



## Future Enhancements

- Persist user data to files or databases for session continuity.  
- Integrate more movies dynamically via external JSON or database.  
- Add user login for personalized profiles.  
- Enhance recommendation algorithm with machine learning techniques.  
- Introduce multiple themes including dark mode.  
- Support playback of embedded trailers inside the app.

## Author

**Abhishek S**

This README provides a comprehensive introduction to your project, covering purpose, features, tech stack, usage, and next steps. You can customize author and repository details as needed.
