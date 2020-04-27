# MVVM-and-Data-Binding
MVVM and Data Binding in Android Sample project for experimentation


We will review a few MVVM concepts using a simple Activity that shows a status text and a switch button.

Using Data Binding, we will bind the TextView and the Switch button with it correspondant property in the ViewModel class.
ViewModel properties will be based on a data model that implements all the status related stuff.

The app consists on a status text wich shows Bluetooth adapter state: "ON" or "OFF"; and a switch wich, when triggered, will call a method from the ViewModel, that will modify the text value and a binary value, updating the UI through Data Binding and updating System's Bluetooth adapter state with an Observer defined inside the View class itself.

To keep coherence with System's Bluetooth adapter state, a BroadcastReceiver is defined in the View class (wich is the only class that is Android dependant) to receive Bluetooth's adapter state chages.

<img src="https://user-images.githubusercontent.com/13170751/80427696-9ada2800-88e8-11ea-8cb6-5bcf50755812.png" width="45%"></img> <img src="https://user-images.githubusercontent.com/13170751/80427697-9b72be80-88e8-11ea-9509-de63f6b3f45f.png" width="45%"></img> 

Images redimensioned using:
https://felixhayashi.github.io/ReadmeGalleryCreatorForGitHub/
