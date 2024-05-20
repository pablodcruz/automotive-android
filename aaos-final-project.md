# Android Automotive Group Project Outline

## Objective:
Develop an Android Automotive app that enhances the in-car experience while prioritizing safety and minimizing distractions.
This project outline sets a structured framework for you all to develop Android Automotive applications that are not only functional and innovative but also safe and user-friendly for drivers. The focus on documentation and presentation ensures that the teams are not only focus on coding but also on their ability to articulate and showcase their work effectively. The project is designed to encourage learning and adaptation, pushing the boundaries of what is currently possible in automotive app development.

## Project Requirements

1. **Team Composition and Collaboration**
   - Teams of 3-4 members.
   - Daily stand-up meetings to discuss progress, challenges, and next steps.
   - Use of collaborative tools for version control and task management (e.g., Git, Jira, and/or [Github Projects](https://docs.github.com/en/issues/planning-and-tracking-with-projects/learning-about-projects/quickstart-for-projects))

2. **Architecture**
   - Adopt either Model-View-ViewModel (MVVM) or Model-View-Intent (MVI) architecture to ensure clean separation of concerns and easier maintenance/testing.
   - Document architectural decisions and flow for clarity in both development and presentation.

3. **Technology Stack**
   - Android Studio, Jetpack Compose, Java/Kotlin
   - Car Apps library, Automotive OS
   - Firebase for data management and real-time database requirements, such as storing and retrieving POI data.
   - Free APIs (Weather, city parking, etc)

4. **Documentation**
   - Maintain comprehensive documentation throughout the development process:
     - MVP Project requirements plus streatch goals.
     - Daily stand-up notes to record progress and impediments.
     - Code documentation for maintainability and clarity.
     - Final project report detailing the development process, architecture, and lessons learned.

5. **Presentation**
   - Prepare a PowerPoint presentation covering:
     - Project overview and team roles.
     - Architectural choices and their justifications.
     - Key features implemented.
     - Demonstrations of the app functionality.
     - Challenges faced and solutions implemented.
     - Future feature considerations and improvements.

## App ideas (due EOD Tuesday May 21st)

### 1. **Smart Parking Assistant** (not recommended without proper parking data api)
- **Description**: An app that helps drivers find available parking spaces in real-time. It could integrate with city parking data or private parking facilities to display available spots and pricing. 
- **Features**:
  - Real-time updates on available parking spots within a driver’s vicinity.
  - Navigation to the selected parking space.
  - Option to reserve a parking spot and pay in advance.
  - [NYC Parking Locations and Signs](https://data.cityofnewyork.us/Transportation/Parking-Regulation-Locations-and-Signs/nfid-uabd/about_data)

### 2. **Voice-Enabled Vehicle Controls**(can add to POI app)
- **Description**: An app that allows drivers to control various aspects of their vehicle through voice commands. This can include temperature control, audio settings, or even adjusting seat positions.
- **Features**:
  - Integration with the vehicle’s internal systems to allow voice control over features.
  - Customizable voice commands for different controls.
  - Feedback system that confirms actions through subtle audio cues.

### 3. **Eco Driving Coach** (log data, analyze and make conclusions)
- **Description**: An app designed to help drivers optimize their driving habits for better fuel efficiency and reduced emissions. It analyzes driving patterns and provides tips and real-time guidance.
- **Features**:
  - Tracking and analysis of driving patterns like speed, acceleration, and idle times.
  - Suggestions for improving driving habits to save fuel and reduce wear.
  - Gamification elements where drivers can earn badges or scores for eco-friendly driving.

### 4. **Maintenance Reminder and Booking Tool** (not recommended)
- **Description**: An app that keeps track of the vehicle’s maintenance schedule, notifies the driver when service is due, and allows them to book service appointments.
- **Features**:
  - Automatic reminders for oil changes, tire rotations, and other routine services based on mileage and/or time.
  - Integration with local service centers to schedule appointments.
  - Digital maintenance log that tracks past service records.

### 5. **POI - Local Exploration and Recommendations**(consume and store data)
- **Description**: This app would provide recommendations for restaurants, tourist attractions, and other points of interest based on the vehicle’s current location.
- **Features**:
  - Customized recommendations based on user preferences and past activities.
  - One-tap navigation to recommended sites.
  - User reviews and the option to book a visit or order food from the app.

### 6. **Roadside Assistance App**
- **Description**: An app that provides quick access to roadside assistance services in case of a breakdown or accident.
- **Features**:
  - One-click SOS button to summon help, sharing the vehicle’s exact GPS location.
  - Estimated time of arrival for assistance.
  - Tips and guidance on what to do in different emergency scenarios until help arrives.
  - (Optional) Automatically sends SOS based on emergency.

### 7. **Weather and Road Condition Alerts** 
- **Description**: An app that offers real-time weather updates and road condition alerts along a driver’s route.
- **Features**:
  - Consumes weather API
  - Alerts about severe weather, road closures, or traffic accidents ahead on their route.
  - Suggested alternative routes to avoid bad weather or traffic jams. 
  - Integration with vehicle sensors to provide tailored driving tips based on current weather conditions (e.g., tire pressure adjustments in cold weather).

## Final Deliverables
- **Source Code**: Complete, well-commented, and structured source code hosted on a version control platform like GitHub.
- **Technical Documentation**: Detailed documentation that includes setup instructions, architecture descriptions, API documentation, and usage guides.
- **Presentation Slides**: Slides for the final presentation that visually represent the project's scope, architecture, key features, challenges faced, and potential future enhancements.
- **Team Presentation**: A demonstration of the app in action, highlighting its key features and usability within an automotive setting.

## Evaluation Criteria
- **Functionality**: How well does the app perform the tasks it is supposed to?
- **User Interface and Experience**: Is the app easy to use in a vehicle setting? Does it enhance the driving experience without causing distractions?
- **Creativity and Innovation**: How creative are the features and solutions implemented in the app?
- **Adherence to Safety Standards**: How well does the app meet automotive safety and distraction guidelines?
- **Technical Quality**: Quality of the code, architectural design, and overall project organization.

