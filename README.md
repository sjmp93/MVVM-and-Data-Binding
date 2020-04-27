# MVVM-and-Data-Binding
Sample project for MVVM and Data Binding in Android experimentation


We will review a few MVVM concepts using a simple Activity that shows a status text and a button.

Using Data Binding, we will bind the TextView and the Button with it correspondant property in the ViewModel class.
ViewModel properties will be based on a data model that implements all the status related stuff.

The app consists on a status text with two possible values: "Enabled" or  "Disabled"; and a button wich, when pressed, will trigger a method from the ViewModel, that will modify the text value, updating the UI through an Observer.

<img src="https://user-images.githubusercontent.com/13170751/80427696-9ada2800-88e8-11ea-8cb6-5bcf50755812.png" width="45%"></img> <img src="https://user-images.githubusercontent.com/13170751/80427697-9b72be80-88e8-11ea-9509-de63f6b3f45f.png" width="45%"></img> 

Images redimensioned using:
https://felixhayashi.github.io/ReadmeGalleryCreatorForGitHub/
